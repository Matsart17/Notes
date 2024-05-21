package com.app.categories;

import com.app.notes.Note;
import com.app.notes.NotesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class CategoryController {
    private CategoryService categoryService;
    private NotesService notesService;

    @Autowired
    public CategoryController(CategoryService categoryService, NotesService notesService) {
        this.categoryService = categoryService;
        this.notesService = notesService;
    }

    @GetMapping(path = "categories")
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping(path = "categories/null")
    public List<Note> getNoneCategoryNotes() {
        return notesService.findNoneCategoryNotes();
    }

    @GetMapping(path = "categories/{categoryName}")
    public List<Note> findNotesByCategory(@PathVariable String categoryName) {
        System.out.println(List.of(categoryService.getCategory(categoryName)));
        return notesService.findNotesByCategory(categoryName);
    }

    @PostMapping(path = "categories")
    public ResponseEntity<?> createNewCategory(@Valid @RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @PutMapping(path = "categories/{categoryName}")
    public ResponseEntity<?> checkCategory(@RequestBody Category tempCategory, @PathVariable String categoryName) {
        Category existingCategory = categoryService.getCategory(categoryName);
        categoryService.merge(existingCategory, tempCategory);
        return categoryService.saveCategory(existingCategory);
    }

    @DeleteMapping(path = "categories/{noteId}")
    public void deleteNote(@PathVariable int noteId) {
        notesService.deleteNote(noteId);
    }


}
