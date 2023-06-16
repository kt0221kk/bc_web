package library_management_class;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import library_management_class.*;
import java.util.*;
import java.text.SimpleDateFormat;

@WebServlet("/DetailBook")
public class DetailBookServlet extends HttpServlet {
    public DetailBookServlet(){
        super();
    }   

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
            HttpSession session = request.getSession(false);
            String target = request.getRequestURI();
            if (session == null){
                /* まだ認証されていない */
                session = request.getSession(true);
                session.setAttribute("target", target);
                response.sendRedirect("/library_management_system_bc/login");
                return;
            }else{
                Object loginCheck = session.getAttribute("login");
                if (loginCheck == null){
                    /* まだ認証されていない */
                    session.setAttribute("target", target);
                    response.sendRedirect("/library_management_system_bc/login");
                    return;
                }
            }
            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getConnection();
            OperateBook operateBook = new OperateBook(connection);
            BookDao bookDao = new BookDao(connection);
            int bookId = Integer.parseInt(request.getParameter("book_id"));
            Book book = bookDao.selectById(bookId);
            TrackService trackService = new TrackService();
            ArrayList<Track> trackList = trackService.getTrackListByBookId(bookId);
            // TrackDao trackDao = new TrackDao(connection);
            // ArrayList<Track> trackList = trackDao.selectByBookId(bookId);
            request.setAttribute("trackList", trackList);
            request.setAttribute("book", book);
        
            ArrayList<Map<String, Object>> trackDataList = new ArrayList<>();
            //全てのイベントを含む日付のリスト
            List<String> occupiedDates = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            for(Track track : trackList){
                Map<String, Object> trackData = new HashMap<>();
                trackData.put("title", track.getTrackStatus());
                // trackData.put("start", track.getTrackTime());
                String color = "";
                String textColor = "";
                String url = "";
                java.util.Date start=null;
                java.util.Date end=null;
                Calendar startCal = Calendar.getInstance();
                Calendar endCal = Calendar.getInstance();

                switch (track.getTrackStatus()) {
                    case "書籍登録":
                        start = track.getTrackTime();
                        end = track.getTrackTime();
                        // グレー
                        color = "#808080";
                        textColor = "white";

                        break;
                    case "貸出":
                        if(book.getStatus().equals("貸出中")){
                            color = "red";
                            Due due = (Due)track;
                            start = due.getBorrowDate();
                            System.out.println(start);
                            //一日追加
                            end= due.getReturnDueDate();
                            textColor = "white";
                            startCal.setTime(start);
                            endCal.setTime(end);
                            for (java.util.Date date = startCal.getTime(); !startCal.after(endCal); startCal.add(Calendar.DATE, 1), date = startCal.getTime()) {
                                occupiedDates.add(sdf.format(date));
                            }
                            url = "/library_management_system_bc/DetailTrack?track_id=" + track.getTrackId()+"&book_id="+bookId;
                        }else{
                            // 次のfor文のループに入る
                            continue;
                        }
                        break;
                    case "予約":
                        Reservation reservation = (Reservation)track;
                        if(reservation.getIsActive()){
                            color = "yellow";
                            start = reservation.getReservationStartDate();
                            end=reservation.getReservationEndDate();
                            textColor = "black";
                            startCal.setTime(start);
                            endCal.setTime(end);
                            for (java.util.Date date = startCal.getTime(); !startCal.after(endCal); startCal.add(Calendar.DATE, 1), date = startCal.getTime()) {
                                occupiedDates.add(sdf.format(date));
                            }
                            url = "/library_management_system_bc/DetailTrack?track_id=" + track.getTrackId()+"&book_id="+bookId;
                            break;
                        }else{
                            // 次のfor文のループに入る
                            continue;
                        }
                        
                }
                // イベントを含む日付をリストに追加
                // Calendar startCal = Calendar.getInstance();
                // startCal.setTime(start);
                // Calendar endCal = Calendar.getInstance();
                // endCal.setTime(end);
                // for (java.util.Date date = startCal.getTime(); !startCal.after(endCal); startCal.add(Calendar.DATE, 1), date = startCal.getTime()) {
                //     occupiedDates.add(sdf.format(date));
                // }

                trackData.put("start", sdf.format(start));
                trackData.put("color", color);
                trackData.put("textColor", textColor);
                trackData.put("url", url);

                endCal.add(Calendar.DATE, 1);
                String formattedEndDate = sdf.format(endCal.getTime());
                trackData.put("end",formattedEndDate);
                            
                trackDataList.add(trackData);
            }
            // 今日から30日後までの日付について、それがoccupiedDatesに含まれていない場合は新しいイベントを作成
            TimeZone tz = TimeZone.getTimeZone("Asia/Tokyo");
            TimeZone.setDefault(TimeZone.getTimeZone("Asia/Tokyo"));
            Calendar cal = Calendar.getInstance(tz);
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < 60; i++) {
                String date = sdf.format(cal.getTime());
                if (!occupiedDates.contains(date)) {
                    Map<String, Object> trackData = new HashMap<>();
                    if (i == 0) {
                        trackData.put("title", "貸出可能");
                        // trackData.put("title", date);
                        sdf = new SimpleDateFormat("yyyy-MM-dd");

                    } else {
                        trackData.put("title", "予約可能");
                    }
                    trackData.put("start", date);
                    trackData.put("end", date);
                    if (i == 0) {
                        trackData.put("color", "green");
                    } else {
                        //淡い緑色
                        trackData.put("color", "#98FB98");
                    }
                    if (i == 0) {
                        trackData.put("textColor", "white");
                    } else {
                        trackData.put("textColor", "black");
                    }
                    // trackData.put("url", "/library_management_system_bc/ReservationBook?book_id=" + bookId+"&start_date="+date);
                    trackData.put("url", "/library_management_system_bc/ReservationBook?book_id=" + bookId);
                    trackDataList.add(trackData);
                }
                cal.add(Calendar.DATE, 1);
            }

            request.setAttribute("trackDataList", trackDataList);
            RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/detail_book.jsp");
            dispatch.forward(request, response);
        }
}
 