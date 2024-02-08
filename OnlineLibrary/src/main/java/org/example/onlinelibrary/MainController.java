package org.example.onlinelibrary;

public  class MainController {

     static  public   int userId;
    private int[] reservedbooks;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int[] getReservedbooks() {
        return reservedbooks;
    }

    public void setReservedbooks(int[] reservedbooks) {
        this.reservedbooks = reservedbooks;
    }

}
