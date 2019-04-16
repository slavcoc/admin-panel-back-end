package com.adminapp.adminapp.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @Column(length = 1500)
    private String content;
    private String filename;
    private String description;
    private String url;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date;




    public Posts(Long id, String title, String content, String filename, String description, String url) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.filename = filename;
        this.url = url;
        this.description = description;
        this.date = new java.util.Date();
    }

    public Posts() {
        this.date = new java.util.Date();
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFilename() {
        return filename;
    }


    public void setFilename(String filename) {
        this.filename = filename;
    }
}
