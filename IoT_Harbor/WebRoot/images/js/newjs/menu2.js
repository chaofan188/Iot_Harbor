var cut_nums = 10;
var screen_h = parseInt(document.documentElement.clientHeight)-95-parseInt(cut_nums);
$("#tree").css("height",screen_h);
var site_url = 'admin/skin/';
$("#menu_2").addClass("selected");
window.onresize=function()
{
    var widths = document.body.scrollWidth-220;
    var heights = document.documentElement.clientHeight-98;
    $("#right").height(heights).width(widths);
    $("#lefttree").height(heights+30);
    $("#admin_left").height((heights+28));
	$('.window_1').css('left', (widths + 380 - $('.window_1').width())+'px');
}
window.onresize();

String.prototype.trim = function()
{
    return this.replace(/(^\s*)|(\s*$)/g, "");
}

function click_topmenu(id)
{
	$("#tree_click").hide();
	$("#tree_bg").hide();
	$("#menu_name").html($('#menu_'+id).attr('alt'));
	$("#menu_top").html($('#menu_'+id).attr('alt'));
	$(".menu").removeClass("selected");
	$('#menu_'+id).addClass("selected");
}