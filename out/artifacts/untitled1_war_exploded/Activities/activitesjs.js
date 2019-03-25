
$(function () {
    var move_right = '<span class="glyphicon glyphicon-minus pull-left  dual-list-move-right" title="Remove Selected"></span>';
    var move_left  = '<span class="glyphicon glyphicon-plus  pull-right dual-list-move-left " title="Add Selected"></span>';
    var jsArray = [];

    var activities_to_save = [];


    $(".dual-list.list-left .list-group").sortable({
        stop: function( event, ui ) {
            updateSelectedOptions();
        }
    });


    $('body').on('click', '.list-group .list-group-item', function () {
        $(this).toggleClass('active');
    });


    $('body').on('click', '.dual-list-move-right', function (e) {
        e.preventDefault();

        actives = $(this).parent();
        $(this).parent().find("span").remove();
        actives.remove();

        sortUnorderedList("dual-list-right");

        updateSelectedOptions();
    });


    $('body').on('click', '.dual-list-move-left', function (e) {
        e.preventDefault();

        var flag = true;
        var txt = $(this).parent().text();

        $('.list-left ul li').each(function(idx, opt) {
         if($(opt).text() === txt)
             flag = false;
        });

        if(flag)
        {
        actives = $(this).parent();

        var $clone = $(this).parent().clone();
        $clone.find("span").remove();
        $(move_right).clone().appendTo($clone);
      $clone.appendTo('.list-left ul').removeClass("active");

     }

        updateSelectedOptions();
    });


    $('.move-right, .move-left').click(function () {

        var $button = $(this), actives = '';

        if ($button.hasClass('move-left')) {

            $('.list-right ul li').each(function(idx, opt) {
                if($(opt).hasClass('active')) {
                    var flag = true;
                    var txt = $(opt).text();

                    $('.list-left ul li').each(function (idx, opt2) {

                        if ($(opt2).text() === txt)
                            flag = false;

                    });

                    if (flag) {

                        var $clone = $(opt).clone();
                        $clone.find("span").remove();
                        $(move_right).clone().appendTo($clone);
                        $clone.appendTo('.list-left ul').removeClass("active");



                    }
                    $(opt).removeClass("active");
                }

                });

        } else if ($button.hasClass('move-right')) {
            actives = $('.list-left ul li.active');
            actives.find(".dual-list-move-right").remove();
            actives.remove();

        }

        updateSelectedOptions();
    });

    $('.save-list').click(function () {
        $('.list-left ul li').each(function (idx, opt2) {
            var setsSelector = '<label for="select-choice-mini" class="select"> '+ ($(opt2).text()) + '</label>\n' +
                '<select name="select-choice-mini" id= '+ ($(opt2).text()) + '  data-mini="true" data-inline="true">\n' +
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

            $('#modal-list').append('<li> '+ setsSelector + '</li>')
        });
    });

    $('.edit-list').click(function () {
        $('#modal-list').empty();
        activities_to_save = [];
    });
    $('.save-btn').click(function () {

        var xhr = new XMLHttpRequest();
                var actvName = document.getElementById("actvName").value;
                document.getElementById("actvName").value = "";
                    $('.list-left ul li').each(function (idx, opt2) {
                        var conceptName = $('#' + ($(opt2).text())+' option:selected').val();
                       // var calories =

                        var item =  { activityName: $(opt2).text(), repeats:conceptName,calories:map[$(opt2).text()]}
                        activities_to_save.push(item);
                        $(opt2).remove();
                        actives = $(this).parent();
                        $(this).parent().find("span").remove();
                        actives.remove();
                    });

        $.ajax({
            type: 'get', // it's easier to read GET request parameters
            url: '/controller/ActivityService/save',
            dataType: 'JSON',
            data: {
                actvName: actvName,
                list: JSON.stringify(activities_to_save) // look here!
            },
            success: function(data) {
              // document.getElementById("popupAnswer").value= JSON.stringify(data);
                $('#popupAnswer').text(JSON.stringify(data));
            },
            error: function(data) {
                alert("dail");
            }
        });
        activities_to_save = [];
        $('#modal-list').empty();
        updateSelectedOptions();
    });
    function updateSelectedOptions() {

        $('#dual-list-options').find('option').remove();

        $('.list-left ul li').each(function(idx, opt) {

            $('#dual-list-options').append($("<option></option>")
                .attr("value", $(opt).data("value"))
                .text( $(opt).text())
                .prop("selected", "selected")
            );
        });
    }


    $('.dual-list .selector').click(function  f() {
        var $checkBox = $(this);
        if (!$checkBox.hasClass('selected')) {
            $checkBox.addClass('selected').closest('.well').find('ul li:not(.active)').addClass('active');
            $checkBox.children('i').removeClass('glyphicon-unchecked').addClass('glyphicon-check');
        } else {
            $checkBox.removeClass('selected').closest('.well').find('ul li.active').removeClass('active');
            $checkBox.children('i').removeClass('glyphicon-check').addClass('glyphicon-unchecked');
        }
    });





    function sortUnorderedList(ul, sortDescending) {
        $("#" + ul + " li").sort(sort_li).appendTo("#" + ul);

        function sort_li(a, b){
            return ($(b).data('value')) < ($(a).data('value')) ? 1 : -1;
        }
    }


    $("#dual-list-left li").append(move_right);
    $("#dual-list-right li").append(move_left);




});

