package com.example.quicknote.ui.notesScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.quicknote.R
import com.example.quicknote.databinding.FragmentNotesBinding
import com.example.quicknote.viewmodels.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
    private val notesViewModel by viewModels<NotesViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        binding.rvNotes.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindObservers()

        binding.btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_notesFragment_to_editNoteFragment)

        }

    }
    private fun bindObservers() {
        notesViewModel.getNotes().observe(viewLifecycleOwner, Observer { it ->


            binding.rvNotes.adapter = NoteAdapter(it){note->
                val bundle  = Bundle()
                bundle.putInt("noteId",note.id!!)
                bundle.putString("noteTitle", note.title)
                bundle.putString("noteDescription",note.description!!)
                findNavController().navigate(R.id.action_notesFragment_to_editNoteFragment, bundle)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}