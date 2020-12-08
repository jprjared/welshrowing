
$(document).ready()
{
    $(".slider").on('input', function (event) {
        $(this).closest(".form-group").find('.SelectedValue').text(event.target.value);
    });
}