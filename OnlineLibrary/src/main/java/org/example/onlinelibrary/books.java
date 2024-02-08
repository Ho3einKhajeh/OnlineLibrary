package org.example.onlinelibrary;

public class books {
    private int id;
    private String subject;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsReservd() {
        return IsReservd;
    }

    public void setIsReservd(String isAvailable) {
        IsReservd = isAvailable;
    }

    private String author;
    private String IsReservd;
}
