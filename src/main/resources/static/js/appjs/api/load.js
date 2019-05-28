var list = document.getElementsByTagName("img");

var num=0;
$(function () {
    showImg(list[0]);
})
function showImg(item) {
    var data = item.getAttribute("data");
    $("img").removeAttr("src")
    item.setAttribute("src", data);
}

var scrollFunc = function(e) {
    ee = e || window.event;

    var fullheight = document.body.offsetHeight;
    debugger;
    if (e.wheelDelta) { //IE/Opera/Chrome
        var a = e.wheelDelta;//向上为120，向下为-120
        if(a>0){//向上
            num--;
            if (num >= 0) {
            showImg(list[num]);

            }
        }
        if(a<0){//向下
            num++;
            if (num <= list.length) {
            showImg(list[num]);

            }
        }
    } else if (e.detail) { //Firefox
        var a = e.detail;//向上为-3，向下为3
        if(a<0){//向上
            document.documentElement.scrollTop -= fullheight;
        }
        if(a>0){//向下
            document.documentElement.scrollTop += fullheight;
        }
    }




}

/*注册事件*/
if (document.addEventListener) {
    document.addEventListener('DOMMouseScroll', scrollFunc, false);
} //W3C
window.onmousewheel = document.onmousewheel = scrollFunc; //IE/Opera/Chrome
