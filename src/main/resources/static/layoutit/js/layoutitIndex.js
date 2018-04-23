
$(function(){
	$.ajax({
		url : __rootPath + "/oa/info/insPortalDef/getPersonalPort.do",
		method : "POST",
		success : function(data) {
			$(".personalPort").html(data);
			
			var card_msg_let =  $(".card_msg ul li").length;
			card_msg(card_msg_let);
			
			//IE7、8样式兼容
			if(navigator.userAgent.indexOf("MSIE")>0){ 
				 if(navigator.userAgent.indexOf("MSIE 8.0")>0 && !window.innerWidth){
					 cardResponse();
				 }else if(navigator.userAgent.indexOf("MSIE 7.0")>0){
					 cardResponse();
				 }
			}
		}
	});
});

function refresh(colId) {
	$.ajax({
		url : __rootPath + "/oa/info/insColumnDef/getColHtml.do?colId="+ colId,
		method : "POST",
		success : function(data) {
			$("div[colid=" + colId + "]")[0].outerHTML = data;
		}
	});
}

/**
 * 打开处理窗口。
 * @param url
 * @param title
 * @param solId
 * @returns
 */
function openUrl(url,title){
	_OpenWindow({
		title : title,
		height : 400,
		width : 780,
		max : true,
		url :url,
		ondestroy : function(action) {
			location.href=location.href+"?time="+new Date().getTime();		
		}
	});
}



function showMore(colId, name, moreUrl) {
	moreUrl = __rootPath + moreUrl;
	mgrNewsRow(colId, name, moreUrl);
}

//打开一个新的页面显示这个栏目的more
function mgrNewsRow(colId, name, moreUrl) {
	top['index'].showTabFromPage({
		title : name,
		tabId : 'colNewsMgr_' + colId,
		url : moreUrl
	});
}

//打开设置门户页面
function setPort() {
	$.ajax({
		url : __rootPath + "/oa/info/insPortalDef/setPersonalPort.do",
		method : "POST",
		success : function(data) {
			_OpenWindow({
				title : '门户配置',
				height : 400,
				width : 780,
				max : true,
				url : __rootPath
						+ '/scripts/layoutit/layoutitIndex.jsp?portId='
						+ data,
				ondestroy : function(action) {
					location.reload();
				}
			});
		}
	})
}
$('.mini-tabs-body-top:eq(0)', parent.document).children().addClass('index_box');

function card_msg(num){
	$(".card_msg ul li").css('width','calc(100% / '+num+' - 20px)');
}


$(document).on("click","#Refresh",function(){
	$(this).parents('.widget-body').siblings().show();
});


function cardResponse(){
	var cardUl = $('#cardUl'),
		cardUlWidth = cardUl.width(),
		cardLi = cardUl.children('li'),
		rowFluid = $(".row-fluid").width(),
		cardWidth = $(".personalPort").width();
	
			
			$('.card > #cardUl > li').css( 'width', cardUlWidth / 8 - 20 );
	if(cardWidth < 1076 ){
		$(".cardBox img").css('float','none').css('display','block').css('margin','auto').css('width',70);
		$(".cardBox span").css('float','none').css('display','block').css('width','100%').css('margin','auto');
		$(".cardBox h2").css('width','100%').css('textAlign','center').css('font-size',16).css('lineHeight','16px');
		$(".cardBox h3").css('width','100%').css('textAlign','center').css('font-size',16).css('lineHeight','16px');
		$("#cardUl li").css('paddingTop',20).css('paddingButtom',20);
	} else if( cardWidth < 1920 ){
		$(".cardBox img").css('float','').css('display','').css('margin','').css('width','');
		$(".cardBox span").css('float','').css('display','').css('width','').css('margin','');
		$(".cardBox h2").css('width','').css('textAlign','').css('font-size','').css('lineHeight','');
		$(".cardBox h3").css('width','').css('textAlign','').css('font-size','').css('lineHeight','');
		$("#cardUl li").css('paddingTop','').css('paddingButtom','');
	}
	
	
	
	$(".span2").width( rowFluid / 6 - 20 );
	$(".span3").width( rowFluid / 4 - 20 );
	$(".span4").width( rowFluid / 3 - 20 );
	$(".span5").width( rowFluid / 2.4 - 20 );
	$(".span6").width( rowFluid / 2 - 20 );
	$(".span8").width( rowFluid / 1.5 - 20 );
	$(".span9").width( rowFluid * 0.75 - 20 );
	$(".span10").width( rowFluid / 12 - 20 );
}

