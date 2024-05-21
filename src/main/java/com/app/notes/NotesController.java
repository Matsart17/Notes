package com.app.notes;


import com.app.exceptions.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class NotesController {
    private NotesService notesService;

    @Autowired
    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping(path = "notes")
    public List<Note> getAllNotes() {
        return notesService.getAllNotes();
    }

    @GetMapping(path = "notes/{noteId}")
    public Note getNote(@PathVariable int noteId) {
        return notesService.getNoteById(noteId);
    }

    @GetMapping(path = "notes", params = {"find_by", "value"})
    public List<Note> getNotesBy(@RequestParam String find_by, @RequestParam String value) {
        if (find_by.equals("title")) {
            return notesService.getNotesByTitle(value);
        } else if (find_by.equals("noteId")) {
            return List.of(notesService.getNoteById(Integer.parseInt(value)));
        } else throw new NotFoundException("Note not found with find_by=" + find_by + "not found");
    }


    @PostMapping(path = "notes")
    public ResponseEntity<?> createNewNote(@Valid @RequestBody Note note) {
        return notesService.saveNote(note);
    }

    @PutMapping(path = "notes/{noteId}")
    public ResponseEntity<?> updateNote(@RequestBody Note tempNote, @PathVariable int noteId) {
        Note existingNote = notesService.getNoteById(noteId);
        notesService.merge(existingNote, tempNote);
        return notesService.saveNote(existingNote);
    }

    @DeleteMapping(path = "notes/{noteId}")
    public void deleteNote(@PathVariable int noteId) {
        notesService.deleteNote(noteId);
    }

    @DeleteMapping(path = "notes")
    public void deleteAllNotes() {
        notesService.deleteAllNotes();
    }
}
