package library_management_class;

import java.util.Date;

public class Due extends Track {

    private Date borrowDate;
    private Date returnDueDate;
    // getters

    public Date getBorrowDate() {
        return this.borrowDate;
    }

    public Date getReturnDueDate() {
        return this.returnDueDate;
    }

    // setters

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDueDate(Date returnDueDate) {
        this.returnDueDate = returnDueDate;
    }
    public void setDue(Track track){
        this.setTrackId(track.getTrackId());
        this.setUserId(track.getUserId());
        this.setBookId(track.getBookId());
        this.setTrackStatus(track.getTrackStatus());
        this.setTrackTime(track.getTrackTime());
    }

}
