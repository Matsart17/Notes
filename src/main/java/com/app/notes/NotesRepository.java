package com.app.notes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface NotesRepository extends JpaRepository<Note, Integer> {
    @Query("SELECT n FROM Note n JOIN n.categories c WHERE c.name = :categoryName")
    List<Note> findByCategoryName(@Param("categoryName") String CategoryName);

    @Query("SELECT n FROM Note n LEFT JOIN n.categories c WHERE c IS NULL")
    List<Note> findNoneCategory();

    public List<Note> findByTitle(String title);
}
