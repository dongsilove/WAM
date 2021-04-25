/**
 * @FileName 	nav.js
 * @author 		ljpark
 * @Date 		2021.04.25
 * @Description 메뉴 표시
 * @History
 * DATE			AUTHOR		NOTE	
 * -------------------------------------------------
 * 2021.04.25	ljpark		신규
 */
$(function() {
	
	_nav.getList();
	
});

var _nav = {
	getList : function() {

		var str = "";
		var depth2cnt = 0;
		var classOn;
		//var depth3cnt = 0;
		_ajaxUtils.ajax({"url" : "/api/menus/listConnectBy"
			,"successCallback": function(data) { console.log(data);
				$("#nav").html(""); // 왼쪽 메뉴 목록 초기화
				data.content.forEach(function(f){
					processNull(f);
					classOn = "";
					if (f.dep == 2) {
						upperMenuId = f.menuId;
						depth2cnt++;
						if (depth2cnt > 1) {
							str += '</ul></li>';
						}
						str += '<li><a href="#">' + f.menuNm + '</a>\n';
						str += '    <ul class="depth">\n';
					} else if (f.dep == 3) {
						if (f.menuId == gMenuId) classOn = 'class="on"';
						str += '<li ' + classOn + ' ><a href="' + f.menuUrl 
							+ '"><i class="' + f.menuIconNm + '"></i>' + f.menuNm + '</a></li>';
					}
					
				});
				str += '</ul></li>';
				$("#nav").append(str);
			}
		});
	} // getList()

} // _list
