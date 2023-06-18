package library_management_class;
import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.sql.Connection;
import java.util.TimeZone;


public class LibraryCalendarDataFormatter{
    public ArrayList<String> makeOccupiedDatesList(ArrayList<Track> tracks){
        ArrayList<String> occupiedDates = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        BookDao bookDao = new BookDao(connection);
        Book book = bookDao.selectById(tracks.get(0).getBookId());
        connectionManager.closeConnection();
        for(Track track : tracks){
            if ((track instanceof Due) && (book.getStatus().equals("貸出中"))) {

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
            } else if (track instanceof Reservation) {
                    Reservation reservation = (Reservation)track;
                    if(reservation.getIsActive()){
                        java.util.Date start = reservation.getReservationStartDate();
                        java.util.Date end=reservation.getReservationEndDate();
                        Calendar startCal = Calendar.getInstance();
                        Calendar endCal = Calendar.getInstance();
                        startCal.setTime(start);
                        endCal.setTime(end);
                        for (java.util.Date date = startCal.getTime(); !startCal.after(endCal); startCal.add(Calendar.DATE, 1), date = startCal.getTime()) {
                            occupiedDates.add(sdf.format(date));
                        }
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
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        BookDao bookDao = new BookDao(connection);
        Book book = bookDao.selectById(tracks.get(0).getBookId());
        connectionManager.closeConnection();
        for(Track track : tracks){
            if ((track instanceof Due) && (book.getStatus().equals("貸出中"))) {

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
            } else if (track instanceof Reservation) {
                    Reservation reservation = (Reservation)track;
                    if(reservation.getIsActive()){
                        java.util.Date start = reservation.getReservationStartDate();
                        java.util.Date end=reservation.getReservationEndDate();
                        Calendar startCal = Calendar.getInstance();
                        Calendar endCal = Calendar.getInstance();
                        startCal.setTime(start);
                        endCal.setTime(end);
                        for (java.util.Date date = startCal.getTime(); !startCal.after(endCal); startCal.add(Calendar.DATE, 1), date = startCal.getTime()) {
                            disabledDateList.add(sdf.format(date));
                        }
                    }else{
                        // 次のfor文のループに入る
                        continue;
                    }
            }
        }
        return disabledDateList;
    }
    //ArrayList<track>内のDueを最新の物のみにする
    public ArrayList<Track> makeLatestDueList(ArrayList<Track> tracks){
        ArrayList<Track> latestDueList = new ArrayList<Track>();
        Track latestDue = new Track();
        latestDue.setTrackTime(new java.util.Date(0));
        for(Track track : tracks){
            if(track instanceof Due){
                if(track.getTrackTime().after(latestDue.getTrackTime())){
                    latestDue = track;
                }
            }else{
                latestDueList.add(track);
            }
        }
        latestDueList.add(latestDue);
        return latestDueList;
    }
    public ArrayList<Map<String, Object>> makeTrackDataList(ArrayList<Track> tracks){
        ArrayList<Map<String, Object>> trackDataList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        BookDao bookDao = new BookDao(connection);
        Book book = bookDao.selectById(tracks.get(0).getBookId());
        connectionManager.closeConnection();
        tracks = makeLatestDueList(tracks);
        for(Track track : tracks){
            Map<String, Object> trackData = new HashMap<>();
            if ((track instanceof Due) && (book.getStatus().equals("貸出中"))) {
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
            } else if (track instanceof Reservation) {
                Reservation reservation = (Reservation)track;
                if(reservation.getIsActive()){
                    trackData.put("start", sdf.format(reservation.getReservationStartDate()));
                    // trackData.put("end", sdf.format(reservation.getReservationEndDate()));
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(reservation.getReservationEndDate());
                    cal.add(Calendar.DATE, 1);
                    trackData.put("end", sdf.format(cal.getTime()));
                    trackData.put("title", reservation.getTrackStatus());
                    trackData.put("color", "yellow");
                    trackData.put("url", "ReservationDetail?track_id=" + reservation.getTrackId());
                    trackData.put("textColor", "black");
                    trackDataList.add(trackData);
                }else{
                    // 次のfor文のループに入る
                    continue;
                }
                
                    
            } 
            // else {
            //     trackData.put("start", sdf.format(track.getTrackTime()));
            //     trackData.put("end", sdf.format(track.getTrackTime()));
            //     trackData.put("title", track.getTrackStatus());
            //     trackData.put("color", "#808080");
            //     trackDataList.add(trackData);
            // }
        }
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
        ArrayList<String> occupiedDateList = makeOccupiedDatesList(tracks);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
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
                trackData.put("url", "ReservationBook?book_id=" + tracks.get(0).getBookId());
                trackDataList.add(trackData);

            }
            cal.add(Calendar.DATE, 1);
        }
        return trackDataList;
    }

    


}
