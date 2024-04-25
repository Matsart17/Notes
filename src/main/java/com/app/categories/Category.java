package com.app.categories;

import jakarta.persistence.*;

@Entity
@Table(indexes =  @Index(name="categoryId", columnList = "categoryName"))
public class Category {
    @Id
    @Column(nullable = false, unique = true, name = "name")
    private String name;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
