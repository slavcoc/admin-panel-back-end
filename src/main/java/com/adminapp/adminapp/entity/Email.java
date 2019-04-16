package com.adminapp.adminapp.entity;

public class Email {
     private String from;
     private String subject;
     private String text;
     private String to;

    public Email(String from, String subject, String text) {
        this.from = from;
        this.subject = subject;
        this.text = text;
        this.to = "test@gmail.com";
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
