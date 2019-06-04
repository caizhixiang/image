//插件名字：power-slider（多功能滚动插件）
//插件作者：蔡宝坚
//作者博客：http://caibaojian.com
//插件网站：http://caibaojian.com/power-slider
//使用协议：在保留头部版权的情况下，个人和商业均可免费使用
//使用范例：http://caibajian.com/demo/power-slider
;(function ($) {
    $.fn.powerSlider = function (options) {
        return this.each(function () {
            var defualts = {
                handle: "top",
                //滚动方式
                prevNext: true,
                //显示上下的导航
                Nav: true,
                //显示数字
                myTitle: false,
                //当为图片时且每屏为一张时才显示标题
                speed: 500,
                //动画速度
                delayTime: 500,
                //动画间隔
                clickMode: "scroll",
                //数字导航滑过方式
                sliderNum: 1
            };
            //handle 为图片滚动方式
            var opts = $.extend({}, defualts, options),
                obj = $(this),
                index = 0,
                sliderBox = $(".sliderbox", obj),
                sliderLi = sliderBox.find("li"),
                sliderLiWidth = $(".sliderbox li:eq(0)").outerWidth(),
                liNum = sliderLi.length,
                len = (sliderLi.length) / (opts.sliderNum),
                len = Math.ceil(len),
                sliderNav = $(" .slidernav", obj),
                sliderText = $(" .slidertext", obj),
                prev = $(" .prev", obj),
                next = $(" .next", obj),
                sliderTimer, navHtml = '',
                textHtml = '';
            //在动画还没有开始之前预定义的内容
            for (var i = 0; i < len; i++) {
                navHtml += '<li><a href="javascript:void(0);">' + (i + 1) + '</a></li>';
            }
            //当动画方式为渐隐渐现时必须定义css的样式，否则动画过程会出现空白的视觉
            if (opts.handle == 'fadeTo') {
                if (opts.sliderNum <= 1) {
                    sliderLi.css({
                        "position": "absolute",
                        "left": "0",
                        "top": "0"
                    });
                } else {
                    sliderLi.each(function (i) {
                        $(this).css({
                            "position": "absolute",
                            "left": (i % opts.sliderNum) * (sliderLi.width()),
                            "top": "0"
                        })
                    })
                }
                var nextLen = parseInt(opts.sliderNum - 1);
                sliderBox.find("li:gt(" + nextLen + ")").hide();
                //必须加这句css，否则渐隐渐显会出现一段空白的
            }
            //如果有导航数字的，js则在这里添加上去
            if (opts.Nav) {
                sliderNav.append(navHtml);
            }
            //当定义一屏的动画数目大于1时，内容为向左浮动
            if (opts.sliderNum > 1) {
                sliderLi.css("float", "left");

            }
            // obj.css({"width":(opts.sliderNum)*sliderLiWidth}); 由于内容多种多样性，且css列表也需要个性化，所有不加入这个固定宽度，请使用CSS自定义整体的宽度
            var slidertitle = sliderText.find("li"),
                sliderA = sliderNav.find("li");
            //当有文字标题时预先显示第一张，其余css已经设置隐藏
            slidertitle.eq(0).show();
            //默认第一张添加了一个类名current
            sliderA.eq(0).addClass("current"), sliderLi.eq(0).addClass("current");
            //结束动画开始之前的加载
            //滚动的主要函数，i的初始值为第一个，也就是0，index是随着i而变化的
            function showImg(i, index) {
                var sliderHeight = obj.height(),
                    sliderWidth = obj.width();
                slidertitle.hide().eq(index).show();
                sliderA.removeClass("current").eq(index).addClass("current"), sliderLi.removeClass("current").eq(index).addClass("current");
                if (opts.handle == 'top') {
                    showImg1(list[index]);
                    sliderBox.filter(":not(':animated')").animate({
                        "top": -sliderHeight * index
                    }, opts.speed);
                } else if (opts.handle == 'left') {
                    sliderLi.css("float", "left");
                    sliderBox.css("width", len * sliderWidth).filter(":not(':animated')").animate({
                        "left": -sliderWidth * index
                    }, opts.speed);
                } else if (opts.handle == 'hide') {
                    var j = index + 1;
                    sliderLi.hide().slice(index * (opts.sliderNum), j * (opts.sliderNum)).show();
                } else if (opts.handle == 'fadeTo') {
                    sliderLi.slice(i * (opts.sliderNum), (i + 1) * (opts.sliderNum)).fadeOut(opts.speed);
                    sliderLi.slice(index * (opts.sliderNum), (index + 1) * (opts.sliderNum)).filter(":not(':animated')").fadeIn(opts.speed);
                } else if (opts.handle == 'slideTo') {
                    sliderLi.css("z-index", '1').filter(":not(':animated')").slideUp().slice(index * (opts.sliderNum), (index + 1) * (opts.sliderNum)).css("z-index", "2").slideDown(opts.speed);
                }
            }

            sliderA.bind(opts.clickMode, function () {
                index = $(this).index();
                var i = sliderA.index($(".slidernav .current:eq(0)"));
                if (index != i) {
                    showImg(i, index);
                }
            });
            if (len <= 1) {
                prev.hide();
                next.hide();
            } else {
                if (opts.prevNext) {
                    //prev
                    prev.click(function () {
                        var i = index;
                        index -= 1;
                        if (index == -1) {
                            index = len - 1
                        }
                        ;
                        showImg(i, index);
                    });
                    // //next
                    next.click(function () {
                        auto();
                    });
                }
            }

            //auto fn
            function auto() {
                if (index < len) {
                    var i = index;
                    index = index + 1;
                    showImg(i, index);

                }
            }

            //set time
            var settime;

            /*obj.hover(function(){
                clearInterval(settime);
            },function(){
                settime = setInterval(function(){
                    auto();
                },opts.delayTime);
            }).trigger("mouseleave");
*/


            function autoRoll() {
                $(document).on("mousewheel DOMMouseScroll", function (e) {
                    var delta = (e.originalEvent.wheelDelta && (e.originalEvent.wheelDelta > 0 ? 1 : -1)) || // chrome & ie
                        (e.originalEvent.detail && (e.originalEvent.detail > 0 ? -1 : 1)); // firefox
                    $(document).off("mousewheel DOMMouseScroll");
                    if (delta > 0) {
                        // 向上滚
                        if (index > 0) {
                            var i = index;
                            index -= 1;

                            showImg(i, index);

                        }
                    } else if (delta < 0) {
                        // 向下滚
                        if (index < len - 1) {
                            var i = index;
                            index = index + 1;
                            showImg(i, index);

                        }
                    }
                    //使用setTimeout方法产生一个延时效果，是的每次滑动鼠标滑轮，只执行一次事件方法
                    setTimeout(autoRoll, 300);

                });
            }

            autoRoll();


        });
    };
})(jQuery);
