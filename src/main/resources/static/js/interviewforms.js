
$(document).ready()
{
    $(".slider").on('input', function (event) {
        $(this).closest(".form-group").find('.SelectedValue').text(event.target.value);
    });
}

//Adapted from https://stackoverflow.com/questions/23671407/restrict-future-dates-in-html5-date-input

$(function(){
    var now = new Date();
    var month = now.getMonth() + 1;
    var day = now.getDate();
    var year = now.getFullYear();
    var minageyear = year - 22;
    var maxageyear = year - 14;

    if(month < 10)
        month = '0' + month.toString();
    if(day < 10)
        day = '0' + day.toString();

    var maxDate = maxageyear + '-' + month + '-' + day;
    var minDate = minageyear + '-' + month + '-' + day;

    document.querySelectorAll("#time")[0].setAttribute("max",maxDate);

    document.querySelectorAll("#time")[0].setAttribute("min",minDate);
});

var notfuture = new Date(),
    minDate = notfuture.toISOString().substring(0,10);
    minDate

$('#timefromnow').prop('max', minDate);