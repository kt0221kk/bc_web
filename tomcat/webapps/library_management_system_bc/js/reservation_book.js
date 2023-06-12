var calendarEl = document.getElementById('calendar');
var calendar = new FullCalendar.Calendar(calendarEl, {
    initialView: 'dayGridMonth',
    
});
    $('.input-daterange').datepicker({
        todayBtn: "linked",
        multidateSeparator: ",",
        orientation: "right bottom",
        // daysOfWeekHighlighted: "1,5",
        datesDisabled:disabledDateList,
        toggleActive: true,
        endDate: '+2m',
        startDate: '+0d',
    }).on('changeDate', function (e) {
        var selectedDate = e.date;
        var startDate = $('.input-daterange').data('datepicker').dates[0];
        var endDate = $('.input-daterange').data('datepicker').dates[1];
        const startDate2 = document.getElementById('start_date').value;

        const today = moment().format('YYYY-MM-DD');

        // 開始日が今日であれば "貸出"、それ以降であれば "予約" とする
        if (moment(startDate2).isSameOrBefore(today)) {
            document.getElementById('book_status').value = '貸出';
        } else if (moment(startDate2).isAfter(today)) {
            document.getElementById('book_status').value = '予約';
        }
        if (startDate && endDate) {
            // Convert selected dates to Date objects
            var startDateObj = new Date(startDate);
            var endDateObj = new Date(endDate);
            for(const disabledDate of occupiedDates){
                var invalidDate = new Date(disabledDate);
                // Check if invalidDate is within selected range
                if ((invalidDate >= startDateObj && invalidDate <= endDateObj)) {
                    setTimeout(function() {
                        $('.input-daterange').datepicker('clearDates');
                        console.log(invalidDate);
                        alert('選択した範囲に無効な日付が含まれています。再度選択を行ってください');
                        calendar.removeAllEvents();
                
                        // Add new event
                        for(const track of trackList){
                            calendar.addEvent(track);
                        }
                    }, 1);
                    return;

                }
            }   
        }
        // startDateとendDateの値がnullの場合は、
        if (startDate != null && endDate != null) {
            // Remove all events
            calendar.removeAllEvents();
    
            // Add new event
            for(const track of trackList){
                calendar.addEvent(track);
            }
            calendar.addEvent({ title: '予約予定', start: startDate, end: endDate, color: 'gray' });
            // console.log({ title: '予約予定', start: startDate , end:endDate, color: 'gray' });
            
            
        }

    });

document.addEventListener('DOMContentLoaded', function() {
    
    calendar.batchRendering(function() {
        for(const track of trackList){
            calendar.addEvent(track);
        }
        });
    calendar.render();
});
        