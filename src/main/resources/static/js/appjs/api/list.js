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
                        html += "<td style='padding: 20px;'>";
                        html += "<a href='/image/api/detail/+"+data[x].id+"'><img src='" + data[x].thumUrl + "' style='height: 330px;width: auto;'/> </a>"
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

