package com.mironov.note.ui.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.mironov.note.NoteApp
import com.mironov.note.databinding.FragmentNoteListBinding
import com.mironov.note.domain.entity.Note
import com.mironov.note.presentation.ViewModelFactory
import com.mironov.note.presentation.list.NoteListState
import com.mironov.note.presentation.list.NoteListViewModel
import com.mironov.note.ui.util.collectStateFlow
import javax.inject.Inject

class NoteListFragment : Fragment() {

    companion object {
        fun newInstance() = NoteListFragment()
    }

    private var _binding: FragmentNoteListBinding? = null
    private val binding: FragmentNoteListBinding
        get() = requireNotNull(_binding)

    private val component by lazy {
        (requireActivity().application as NoteApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider.create(this, viewModelFactory)[NoteListViewModel::class]
    }

    private val noteAdapter by lazy {
        NoteAdapter(::openNote)
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        collectStateFlow(viewModel.state, ::applyState)
    }

    private fun setupClickListeners() {
        binding.add.setOnClickListener {
            viewModel.createNote()
        }
    }

    private fun applyState(state: NoteListState) {
        when (state) {
            is NoteListState.Content -> applyContentState(state.content)

            NoteListState.Initial -> Unit
        }
    }

    private fun applyContentState(notes: List<Note>) {
        if (notes.isEmpty()) {
            binding.noNotes.isVisible = true
            binding.noteList.isVisible = false
        } else {
            binding.noteList.adapter = noteAdapter
            binding.noNotes.isVisible = false
            binding.noteList.isVisible = true
            noteAdapter.submitList(notes)
            setupSwipeListener(binding.noteList)
        }
    }

    private fun setupSwipeListener(recyclerView: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = noteAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteNote(item.id)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun openNote(noteId: Int) {
        viewModel.editNote(noteId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.noteList.adapter = null
        _binding = null
    }
}