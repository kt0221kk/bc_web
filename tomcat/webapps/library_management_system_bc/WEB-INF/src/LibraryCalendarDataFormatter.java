package library_management_class;
import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;



public class LibraryCalendarDataFormatter{
    public ArrayList<String> makeOccupiedDatesList(ArrayList<Track> tracks){
        ArrayList<String> occupiedDates = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Track track : tracks){
            switch (track.getTrackStatus()) {
                case "貸出":
                    Due due = (Due)track;
                    java.util.Date start = due.getBorrowDate();
                    java.util.Date end= due.getReturnDueDate();
                    Calendar startCal = Calendar.getInstance();
                    Calendar endCal = Calendar.getInstance();
                    startCal.setTime(start);
                    endCal.setTime(end);
                    for (java.util.Date date = startCal.getTime(); !startCal.after(endCal); startCal.add(Calendar.DATE, 1), date = startCal.getTime()) {
                        occupiedDates.add(sdf.format(date));
                    }
                    break;
                case "予約":
                    Reservation reservation = (Reservation)track;
                    if(reservation.getIsActive()){
                        start = reservation.getReservationStartDate();
                        end=reservation.getReservationEndDate();
                        startCal = Calendar.getInstance();
                        endCal = Calendar.getInstance();
                        startCal.setTime(start);
                        endCal.setTime(end);
                        for (java.util.Date date = startCal.getTime(); !startCal.after(endCal); startCal.add(Calendar.DATE, 1), date = startCal.getTime()) {
                            occupiedDates.add(sdf.format(date));
                        }
                        break;
                    }else{
                        // 次のfor文のループに入る
                        continue;
                    }
            }
        }
        return occupiedDates;
    }
    public ArrayList<String> makeDisabledDateList(ArrayList<Track> tracks){
        ArrayList<String> disabledDateList = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        for(Track track : tracks){
            switch (track.getTrackStatus()) {
                case "貸出":
                    Due due = (Due)track;
                    java.util.Date start = due.getBorrowDate();
                    java.util.Date end= due.getReturnDueDate();
                    Calendar startCal = Calendar.getInstance();
                    Calendar endCal = Calendar.getInstance();
                    startCal.setTime(start);
                    endCal.setTime(end);
                    for (java.util.Date date = startCal.getTime(); !startCal.after(endCal); startCal.add(Calendar.DATE, 1), date = startCal.getTime()) {
                        disabledDateList.add(sdf.format(date));
                    }
                    break;
                case "予約":
                    Reservation reservation = (Reservation)track;
                    if(reservation.getIsActive()){
                        start = reservation.getReservationStartDate();
                        end=reservation.getReservationEndDate();
                        startCal = Calendar.getInstance();
                        endCal = Calendar.getInstance();
                        startCal.setTime(start);
                        endCal.setTime(end);
                        for (java.util.Date date = startCal.getTime(); !startCal.after(endCal); startCal.add(Calendar.DATE, 1), date = startCal.getTime()) {
                            disabledDateList.add(sdf.format(date));
                        }
                        break;
                    }else{
                        // 次のfor文のループに入る
                        continue;
                    }
            }
        }
        return disabledDateList;
    }
    public ArrayList<Map<String, Object>> makeTrackDataList(ArrayList<Track> tracks){
        ArrayList<Map<String, Object>> trackDataList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Track track : tracks){
            Map<String, Object> trackData = new HashMap<>();
            switch (track.getTrackStatus()) {
                case "貸出":
                    Due due = (Due)track;
                    trackData.put("start", sdf.format(due.getBorrowDate()));
                    // endは１日増やす
                    // trackData.put("end", sdf.format(due.getReturnDueDate()));
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(due.getReturnDueDate());
                    cal.add(Calendar.DATE, 1);
                    trackData.put("end", sdf.format(cal.getTime()));
                    trackData.put("title", due.getTrackStatus());
                    trackData.put("color", "red");
                    trackData.put("url", "DueDetail?track_id=" + due.getTrackId());
                    trackData.put("textColor", "white");
                    trackDataList.add(trackData);
                    break;
                case "予約":
                    Reservation reservation = (Reservation)track;
                    if(reservation.getIsActive()){
                        trackData.put("start", sdf.format(reservation.getReservationStartDate()));
                        // trackData.put("end", sdf.format(reservation.getReservationEndDate()));
                        cal = Calendar.getInstance();
                        cal.setTime(reservation.getReservationEndDate());
                        cal.add(Calendar.DATE, 1);
                        trackData.put("end", sdf.format(cal.getTime()));
                        trackData.put("title", reservation.getTrackStatus());
                        trackData.put("color", "yellow");
                        trackData.put("url", "ReservationDetail?track_id=" + reservation.getTrackId());
                        trackData.put("textColor", "black");
                        trackDataList.add(trackData);
                        break;
                    }else{
                        // 次のfor文のループに入る
                        continue;
                    }
                case "書籍登録":
                    trackData.put("start", sdf.format(track.getTrackTime()));
                    trackData.put("end", sdf.format(track.getTrackTime()));
                    trackData.put("title", track.getTrackStatus());
                    trackData.put("color", "#808080");
                    trackDataList.add(trackData);
                    break;
            }
        }
        Calendar cal = Calendar.getInstance();
        ArrayList<String> occupiedDateList = makeOccupiedDatesList(tracks);
        
        for(int i=0; i<60; i++){
            Map<String, Object> trackData = new HashMap<>();
            String date = sdf.format(cal.getTime());
            if(occupiedDateList.contains(date)){
                cal.add(Calendar.DATE, 1);
                continue;
            }else{
                if(i==0){
                    trackData.put("title", "貸出可能");
                    trackData.put("color", "green");
                    trackData.put("textColor", "white");
                }else{
                    trackData.put("title", "予約可能");
                    trackData.put("color", "#98FB98");
                    trackData.put("textColor", "black");

                }
                trackData.put("start", sdf.format(cal.getTime()));
                trackData.put("end", sdf.format(cal.getTime()));
                trackDataList.add(trackData);

            }
            cal.add(Calendar.DATE, 1);
        }
        return trackDataList;
    }
    


}
