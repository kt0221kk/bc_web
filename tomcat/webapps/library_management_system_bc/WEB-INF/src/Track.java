package library_management_class;
import java.util.Date;

public class Track {
    private int trackId;
    private int bookId;
    private int userId;
    private String trackStatus;
    private Date trackTime;

    // Getters
    public int getTrackId() {
        return this.trackId;
    }

    public int getBookId() {
        return this.bookId;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getTrackStatus() {
        return this.trackStatus;
    }

    public Date getTrackTime() {
        return this.trackTime;
    }

    // Setters
    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTrackStatus(String trackStatus) {
        this.trackStatus = trackStatus;
    }

    public void setTrackTime(Date trackTime) {
        this.trackTime = trackTime;
    }
}
