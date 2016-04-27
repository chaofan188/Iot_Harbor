// JavaScript Document
var currentId = 1;
var maxbgId=3;
var autoChangeTimes = 0;
var autoChangeMaxLimit = 6;//
var autoChangeSpeed=50;
var autoChange = true;

var bgProcess = false;
var backgroundTaskId = new Array();
var backgroundHelper = {
    _hide:function (jqueryObj,callback) {
        bgProcess=true;
        if(backgroundTaskId.length>=2){
            for(var i=0;i<backgroundTaskId.length;i++){
                window.clearInterval(backgroundTaskId[i]);
            }
            backgroundTaskId = new Array();
        }
        var bg_hide_interval = window.setInterval(function(){
            var opacity = jqueryObj.css("opacity");
            opacity = parseFloat(opacity)
            if(opacity<=0.1){
                jqueryObj.css({"filter":"Alpha(Opacity=0)"});
                jqueryObj.css({"opacity":0});
                window.clearInterval(bg_hide_interval);
                jqueryObj.hide();
                if(callback != null || callback!= undefined){
                    bgProcess=false;
                    callback();
                }
            }else{
                opacity = opacity - 0.05;
                var aplhaOpacity = opacity*100;
                jqueryObj.css({"filter":"Alpha(Opacity="+aplhaOpacity+")"});
                jqueryObj.css({"opacity":opacity});
            }
        },autoChangeSpeed);
        backgroundTaskId[backgroundTaskId.length]=bg_hide_interval;
    }, show:function (jqueryObj) {
        if(bgProcess){
            return;
        }
        bgProcess=true;
        var currentSelect = "#bg" + currentId;
//        debug("before:-----------"+currentId);
        currentId =jqueryObj.attr('id').replace("bg", "");//currentId ????????????????
//        debug("after:-----------"+currentId);
        backgroundHelper._hide($(currentSelect),function(){
            var bg_show_interval = window.setInterval(function(){
                $('.cbg').hide();
                jqueryObj.show();
                var opacity = jqueryObj.css("opacity");
                opacity = parseFloat(opacity);
                if(opacity>=0.9){
                    jqueryObj.css({"filter":"Alpha(Opacity=100)"});
                    jqueryObj.css({"opacity":1.0});
                    window.clearInterval(bg_show_interval);
                    bgProcess=false;
                }else{
                    opacity = opacity + 0.1;
                    var aplhaOpacity = opacity*100;
                    jqueryObj.css({"filter":"Alpha(Opacity="+aplhaOpacity+")"});
                    jqueryObj.css({"opacity":opacity});
                }
//                debug(jqueryObj.attr('id')+"            show:"+opacity);
            },autoChangeSpeed);
            backgroundTaskId[backgroundTaskId.length]=bg_show_interval;
        });
    }, showdirect:function (jqueryObj)
	{
        var currentSelect = "#bg" + currentId;
        currentId =jqueryObj.attr('id').replace("bg", "");
		$('.cbg').hide();
        $('.cbg').css({"filter":"Alpha(Opacity=0)"});
        $('.cbg').css({"opacity":0.0});
        jqueryObj.show();
        jqueryObj.css({"filter":"Alpha(Opacity=100)"});
        jqueryObj.css({"opacity":1.0});
	},
	showDefault:function () {
        var currentSelect = "#bg" + currentId;
        backgroundHelper.show($("#bg1"));
    }
}

var disabledChange=false;
function initAutoBgChange() {
        var bgChangeInterval = window.setInterval(function () {
           /* if (!autoChange) {
				window.clearInterval(bgChangeInterval);
				disabledChange=true;
				return;
			}*/
               // 
                //
            if(autoChange)
			{
            autoChangeTimes++;
            var currentBgSelect = "#bg" + currentId;
            var currentContentBarSelect = "#barcar" + currentId;
            currentId++;
            if (currentId > maxbgId) {
                currentId = 1;
            }
            currentContentId = currentId;
            var changeBgSelect = "#bg" + currentId;
            var changeContentBarSelect = "#barcar" + currentId;
			
            contentBarHelper.hide($(currentContentBarSelect));
            backgroundHelper.show($(changeBgSelect));
            contentBarHelper.show($(changeContentBarSelect));	
			}
        }, 3000)
}

function barover(changeId)
{
        if(bgProcess){
            return;
        }
		autoChange=false;
        backgroundHelper.showdirect($("#bg"+changeId));
        contentBarHelper.showdirect($("#barcar"+changeId));	
		currentId=changeId;
		
}
function barout()
{
		autoChange=true;
		//if(!disabledChange){initAutoBgChange();};
}

var contentBarHelper = {
    hide:function (jqueryObj) {
        jqueryObj.css("background","#fff");
    }, show:function (jqueryObj) {
        jqueryObj.css("background","#06c");
    }, showDefault:function () {
        $(".barcar").css("background","#fff");
        $("#barcar1").css("background","#06c");
    }, showdirect:function (jqueryObj) {
        $(".barcar").css("background","#fff");
        $(jqueryObj).css("background","#06c");
    }
}

