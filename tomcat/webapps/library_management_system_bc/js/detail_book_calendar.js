console.log(trackList); // Output: Array of book objects

document.addEventListener('DOMContentLoaded', function() {
var calendarEl = document.getElementById('calendar');
var calendar = new FullCalendar.Calendar(calendarEl, {
    initialView: 'dayGridMonth',
    
});
calendar.batchRendering(function() {
    for(const track of trackList){
        calendar.addEvent(track);
    }
    // calendar.addEvent({ title: '貸出中', start: '2023-06-04' , end: '2023-06-10' , color: 'red' });
    // calendar.addEvent({ title: '予約済み', start: '2023-06-13' , end: '2023-06-13' , color: 'yellow',textColor: 'black' ,url: '/library_management_system_bc/AccessLibraryData' });
    // calendar.addEvent({ title: '予約可能', start: '2023-06-10' , end: '2023-06-12' , color: 'green',textColor: 'white' ,url: '/library_management_system_bc/AccessLibraryData' });
  });
calendar.render();
});
