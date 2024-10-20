package com.mironov.note.ui.note

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mironov.note.NoteApp
import com.mironov.note.databinding.FragmentCreateNoteBinding
import com.mironov.note.domain.entity.Note
import com.mironov.note.presentation.ViewModelFactory
import com.mironov.note.presentation.note.NoteScreenState
import com.mironov.note.presentation.note.NoteViewModel
import com.mironov.note.ui.util.collectStateFlow
import javax.inject.Inject

class NoteFragment : Fragment() {

    companion object {
        fun newInstance(isEdit: Boolean, noteId: Int) = NoteFragment().apply {
            arguments = Bundle().apply {
                putBoolean(KEY_IS_EDIT, isEdit)
                putInt(KEY_ID, noteId)
            }
        }

        private const val KEY_ID = "id"
        private const val KEY_IS_EDIT = "is edit"
    }

    private var isEdit = false
    private var noteId = Note.UNDEFINED_ID

    private var _binding: FragmentCreateNoteBinding? = null
    private val binding: FragmentCreateNoteBinding
        get() = requireNotNull(_binding)

    private val component by lazy {
        (requireActivity().application as NoteApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NoteViewModel::class]
    }

    private lateinit var backPressedCallback: OnBackPressedCallback

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
        initScreen()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        addTextWatcher()
        setupBackPressedCallback()
        observeViewModel()
    }

    private fun setupClickListeners() {
        binding.toolbar.setOnClickListener {
            viewModel.back(
                isEdit,
                binding.title.text?.trim().toString(),
                binding.description.text?.trim().toString()
            )
        }
        if (isEdit)
            binding.save.setOnClickListener {
                viewModel.editNote(
                    title = binding.title.text?.trim().toString(),
                    description = binding.description.text.toString(),
                    noteId = noteId,
                )
            }
        else
            binding.save.setOnClickListener {
                viewModel.createNote(
                    title = binding.title.text?.trim().toString(),
                    description = binding.description.text.toString()
                )
            }
    }

    private fun addTextWatcher() {
        binding.description.doOnTextChanged { text, _, _, _ ->
            binding.save.isEnabled = text?.trim().toString().isNotEmpty()
        }
    }

    private fun setupBackPressedCallback() {
        backPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.back(
                    isEdit,
                    binding.title.text?.trim().toString(),
                    binding.description.text?.trim().toString()
                )
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(backPressedCallback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        backPressedCallback.remove()
    }

    private fun parseArgs() {
        val args = requireArguments()
        noteId = args.getInt(KEY_ID, Note.UNDEFINED_ID)
        isEdit = args.getBoolean(KEY_IS_EDIT, false)
    }

    private fun initScreen() {
        if (isEdit)
            viewModel.getNote(noteId)
        else
            viewModel.getDraft()
    }

    private fun observeViewModel() {
        collectStateFlow(viewModel.state, ::applyState)
    }

    private fun applyState(state: NoteScreenState) {
        when (state) {
            is NoteScreenState.Content -> {
                binding.title.setText(state.title)
                binding.description.setText(state.description)
                binding.save.isEnabled = true
            }

            NoteScreenState.Initial -> {
                binding.save.isEnabled = false
            }
        }
    }
}