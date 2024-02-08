package org.example.onlinelibrary;

public class ReservedBooks {
    private static int id;
    private int boookId;
    private int memberId;


    public static int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBoookId() {
        return boookId;
    }

    public void setBoookId(int boookId) {
        this.boookId = boookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setBookId(int bookId) {
    }
}
