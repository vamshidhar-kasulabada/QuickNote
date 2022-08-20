package com.example.quicknote.ui.editnotescreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.quicknote.R
import com.example.quicknote.databinding.FragmentEditNoteBinding
import com.example.quicknote.models.Note
import com.example.quicknote.viewmodels.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditNoteFragment : Fragment() {

    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!
    private val notesViewModel by viewModels<NotesViewModel>()
    private var note: Note? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()

    }

    private fun setData() {

        val noteId = arguments?.getInt("noteId")
        val noteTitle = arguments?.getString("noteTitle")
        val noteDescription = arguments?.getString("noteDescription")

        val addEditTextValues = listOf("Add Note", "Edit Note")

        note = if (noteId != null) {
            Note(noteId, noteTitle!!, noteDescription)
        } else {
            null
        }

        if (note == null) {
            binding.addEditText.text = addEditTextValues[0]
            binding.btnDelete.visibility = View.GONE
            handleSubmitButtonOfAddNote()


        } else {
            binding.addEditText.text = addEditTextValues[1]
            handleSubmitButtonOfEditNote()
            handleDeleteButtonOfEditNote()
        }

    }

    private fun handleSubmitButtonOfAddNote() {

        binding.btnSubmit.setOnClickListener {
            if (binding.etTitle.text.isEmpty()) {
                Toast.makeText(requireContext(), "Title Cannot be Empty", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val txtTitle = binding.etTitle.text.toString()
                val txtDescription = binding.etDescription.text.toString()
                notesViewModel.saveNote(Note(title = txtTitle, description = txtDescription))
                findNavController().popBackStack()

            }
        }
    }

    private fun handleSubmitButtonOfEditNote() {

        binding.etTitle.setText(note?.title)
        binding.etDescription.setText(note?.description)

        binding.btnSubmit.setOnClickListener {
            val id = note?.id
            val etUpdatedTitle = binding.etTitle.text.toString()
            val etUpdatedDescription = binding.etDescription.text.toString()
            notesViewModel.saveNote(Note(id, etUpdatedTitle, etUpdatedDescription))
            //Room onConflictStrategy is replace

            findNavController().popBackStack()
        }
    }

    private fun handleDeleteButtonOfEditNote() {
        binding.btnDelete.setOnClickListener {
            notesViewModel.deleteNote(note!!)
            findNavController().popBackStack()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}