package com.app.notes;

import com.app.categories.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Set;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String body;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(name = "categories",
            joinColumns = @JoinColumn(name = "noteId"),
            inverseJoinColumns = @JoinColumn(name = "CategoryName"))
    private Set<Category> categories;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(nullable = false, updatable = false, columnDefinition = "timestamp")
    private String creationTime;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "timestamp")
    private String updateTime;

    @JsonProperty("noteId")
    public Integer getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setBody(String body) {
        this.body = body;
    }


    public String getTitle() {
        return title;
    }


    public String getBody() {
        return body;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title=" + title +
                ", body='" + body + '\'' +
                ", creationTime='" + creationTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }

}
