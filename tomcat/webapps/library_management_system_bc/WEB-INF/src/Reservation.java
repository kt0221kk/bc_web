package library_management_class;

import java.util.Date;

public class Reservation extends Track {

    private Date reservationStartDate;
    private Date reservationEndDate;
    private boolean isActive;

    // getters
    public Date getReservationStartDate() {
        return this.reservationStartDate;
    }

    public Date getReservationEndDate() {
        return this.reservationEndDate;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    // setters
    public void setReservationStartDate(Date reservationStartDate) {
        this.reservationStartDate = reservationStartDate;
    }

    public void setReservationEndDate(Date reservationEndDate) {
        this.reservationEndDate = reservationEndDate;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

}
