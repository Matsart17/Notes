package com.app.notes;

import com.app.categories.Category;
import com.app.categories.CategoryService;
import com.app.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class NotesService {
    private NotesRepository notesRepository;
    private CategoryService categoriesService;

    @Autowired
    public NotesService(NotesRepository notesRepository, CategoryService categoryService) {
        this.notesRepository = notesRepository;
        this.categoriesService = categoryService;
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
        Set<Category> categories = tempNote.getCategories();
        if (title != null)
            note.setTitle(title);
        if (text != null)
            note.setBody(text);
        if (categories != null) {
            categoriesService.findCategory(categories);
            note.setCategories(categories);
        }
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

    public List<Note> findNotesByCategory(String category) {
        return notesRepository.findByCategoryName(category);
    }

    public List<Note> findNoneCategoryNotes() {
        return notesRepository.findNoneCategory();
    }

    public void deleteAllNotes() {
        notesRepository.deleteAll();
    }
}
