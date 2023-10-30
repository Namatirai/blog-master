package com.blog.blog;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
public class Blog {
@Id
@SequenceGenerator(
        name = "blog_id_sequence",
        sequenceName = "blog_id_sequence"
)
@GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "blog_id_sequence"
)
    private Integer id;
    private String author;
    private String title;
    private String content;

    public Blog() {
    }

    //getters and setters


    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blog blog = (Blog) o;
        return id == blog.id && Objects.equals(author, blog.author) && Objects.equals(title, blog.title) && Objects.equals(content, blog.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, content);
    }
}
