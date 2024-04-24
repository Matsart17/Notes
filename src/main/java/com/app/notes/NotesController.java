package com.app.notes;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/notes")
@RestController
public class NotesController {
    private final NotesService notesService;

    @Autowired
    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return notesService.getAllNotes();
    }

    @GetMapping(path = "{noteId}")
    public Note getNote(@PathVariable int id) {
        return notesService.getNoteById(id);
    }

    @GetMapping(params = {"find_by", "value"})
    public List<Note> getNotesBy(@RequestParam String find_by, @RequestParam String value) {
        if (find_by.equals("title")) {
            return notesService.getNotesByTitle(value);
        } else if (find_by.equals("notId")) {
            return List.of(notesService.getNoteById(Integer.valueOf(value)));
        } else throw null;
    }

    @PostMapping
    public ResponseEntity<?> createNewNote(@Valid @RequestBody Note tempnote) {
        Note note = new Note();
        notesService.merge(note, tempnote);
        return notesService.saveNote(note);
    }
    @PutMapping(path = "{noteId}")
    public ResponseEntity<?> updateNote(@RequestBody Note tempNote, @PathVariable int noteId) {
        Note existingNote = notesService.getNoteById(noteId);
        notesService.merge(existingNote, tempNote);
        return notesService.saveNote(existingNote);
    }

    @DeleteMapping(path = "{noteId}")
    public void deleteNote(@PathVariable int noteId) {
        notesService.deleteNote(noteId);
    }

    @DeleteMapping
    public void deleteAllNotes() {
        notesService.deleteAllNotes();
    }


}
