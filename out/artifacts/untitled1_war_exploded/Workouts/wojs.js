
$(function () {
    var flag = false;

    $('.detailed-list').click(function () {
        workout_id = this.id;
        var arr = map[this.id];
        var toAppend;
        arr.forEach(function (idx) {
            toAppend = '<tr><td data-th="Name">' + idx.activityName + '</td><td data-th="Sets">' + idx.repeats + '</td><td data-th="Calories">' + idx.calories*idx.repeats + '</td></tr>';
        $('#table-body').append(toAppend);
    });
    });
    $('.close-modal').click(function () {
        $("#table-body tr").remove();
    });

    $('.delete-workout').click(function () {
        var xhr = new XMLHttpRequest();
        $.ajax({
            type: 'get', // it's easier to read GET request parameters
            url: '/controller/WorkoutService/delete',
            dataType: 'JSON',
            data: {
                workout_delete: workout_id
            },
            success: function(data) {
              var elem=document.getElementById(workout_id);
              elem.remove();
              $("#table-body tr").remove();
            },
            error: function(data) {
                alert("dail");
            }
    });

});

$('.update-workout').click(function () {
    if(flag == false)
    {  var selector =  '<select name="select-choice-mini" data-mini="true" data-inline="true">\n' +
        '    <option value="0">delete</option>\n' +
        '    <option value="1">1 set</option>\n' +
        '    <option value="2">2 sets</option>\n' +
        '    <option value="3">3 sets</option>\n' +
        '    <option value="4">4 sets</option>\n' +
        '    <option value="5">5 sets</option>\n' +
        '    <option value="6">6 sets</option>\n' +
        '    <option value="7">7 sets</option>\n' +
        '    <option value="8">8 sets</option>\n' +
        '    <option value="9">9 sets</option>\n' +
        '</select>';
    var counter = 0;
    $('#table-body tr').each(function() {
       var  element = $(this).find("td").eq(1);
       element.html("");
       element.append(selector);

    });
    flag = true;
    }
    else {
        var counter = 0;
        var arr = map[workout_id];
        $('#table-body tr').each(function () {
            var e = $(this).find("td").eq(1).children();
            arr[counter].repeats = e.val();
            counter++;
        });
        map[workout_id] = arr;
        flag = false;
        $("#table-body tr").remove();
        $('#exampleModal').modal('toggle');

        var xhr = new XMLHttpRequest();
        $.ajax({
            type: 'get', //
            url: '/controller/WorkoutService/update',
            dataType: 'JSON',
            data: {
                workout_id: workout_id,
                workout_acts: JSON.stringify(arr)
            },
            success: function (data) {
                var closeBtn = document.getElementById("close-btn");
                closeBtn.click();
            },
            error: function (data) {
                alert("dail");
            }
        });

    }


    });

    });


$('.modal-content').resizable({
    //alsoResize: ".modal-dialog",
    minHeight: 300,
    minWidth: 300
});
$('.modal-dialog').draggable();

$('#myModal').on('show.bs.modal', function() {
    $(this).find('.modal-body').css({
        'max-height': '100%'
    });
});


