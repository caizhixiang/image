$(function () {
    $('#main_body').fullpage({
        // fixedElements: $("#fix"),
        scrollOverflow: true
    });
})

function detail(id) {
    window.location.href = "/image/api/detail/" + id;
}

var arr = [];
var val = $("#hid").val();
arr = val.split(",");

function next(category) {
    debugger;
    for (var i = 0; i < arr.length; i++) {
        if (arr[i] == category) {
            window.location.href="/image/api/index?category="+ (i  == (arr.length - 1) ? arr[0] : arr[i + 1])
        }
    }
}

function previous(category) {
    debugger;
    for (var i = 0; i < arr.length; i++) {
        if (arr[i] == category) {
            window.location.href="/image/api/index?category="+ (i == 0 ? arr[arr.length-1] : arr[i-1])

        }
    }
}