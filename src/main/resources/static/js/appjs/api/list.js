$(function () {
    $.ajax({
        url: "list",
        type: "post",
        success: function (data) {
            var html = "";
            if (data) {
                for (var x in data) {
                    debugger;
                    if (x%2 == 0) {
                        html += "<tr>";

                    }
                        html += "<td>";
                        html += "<a href='/image/api/detail/+"+data[x].id+"'><img src='" + data[x].thumUrl + "'/> </a>"
                        html += "</td>";
                    if (x%2 != 0) {

                        html += "</tr>";
                    }
                }
            }
            $("#table").append(html);
        }
    });
})

