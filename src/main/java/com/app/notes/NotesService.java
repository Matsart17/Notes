package com.app.notes;

import com.app.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Component
public class NotesService {
    private NotesRepository notesRepository;

    @Autowired
    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public List<Note> getAllNotes() {
        return notesRepository.findAll();
    }

    public List<Note> getNotesByTitle(String title) {
        return notesRepository.findByTitle(title);
    }

    public Note getNoteById(int noteId) {
        Optional<Note> note = notesRepository.findById(noteId);
        return note.orElseThrow(() -> new NotFoundException("Note with id " + noteId + " not exists"));
    }

    public void merge(Note note, Note tempNote) {
        String title = tempNote.getTitle();
        String text = tempNote.getBody();
        if (title != null)
            note.setTitle(title);
        if (text != null)
            note.setBody(text);
    }

    public ResponseEntity<?> saveNote(Note note) {
        notesRepository.save(note);
        return ResponseEntity.status(201).body(note);
    }

    public void deleteNote(int noteId) {
        Note note = notesRepository
                .findById(noteId)
                .orElseThrow(() -> new IllegalArgumentException("Note not found with id " + noteId));
        notesRepository.delete(note);
    }

    public void deleteAllNotes() {
        notesRepository.deleteAll();
    }
}
