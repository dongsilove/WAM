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
var authorMenuObj = {}; // url 있고 권한이 있는 메뉴 목록
$(function() {
	
	_nav.getList();

});

var _nav = {
	menuList : []
	, getList : function() {
		
		_ajaxUtils.ajax({"url" : "/api/menus/listConnectBy"
			,"successCallback": function(data) { //console.log(data);
				
				_nav.makeNav(data.content);
				_nav.makeTitle(gMenuId);
			}
		});
		
	} 
	, makeNav : function(mList) {
		var str = "";
		var depth2cnt = 0;
		var classOn;

		if (!mList) return;
		$("#nav").html(""); // 왼쪽 메뉴 목록 초기화
		//data.content.forEach(function(f){ // continue를 사용하기 위해 주석처리
		for( var f of mList ) { // continue를 사용하기 위해 for( of array)를 사용한다.
			//console.log(f.menuId);
			processNull(f);
			classOn = "";
			if (isEmpty(authorCn[f.menuId])) continue; // 권한이 없으면 다음메뉴로
			
			_nav.menuList.push(f);
			if (f.menuUrl) {
				authorMenuObj[f.menuId] = f.menuUrl; // url 있고 권한이 있는 메뉴 목록
			} 
			if (f.menuDp == 2) {
				upperMenuId = f.menuId;
				depth2cnt++;
				if (depth2cnt > 1) {
					str += '</ul></li>';
				}
				str += '<li><a href="#">' + f.menuNm + '</a>\n';
				str += '    <ul class="depth">\n';
			} else if (f.menuDp == 3) {
				if (f.menuId == gMenuId) classOn = 'class="on"';
				str += '<li ' + classOn + ' ><a href="' + f.menuUrl 
					+ '"><i class="' + f.menuIconNm + '"></i>' + f.menuNm + '</a></li>';
			}
		}
		str += '</ul></li>';
		$("#nav").append(str);

	
		// 현재의 메뉴가 권한 메뉴 목록에 없는 경우 (gMenuId : 현재페이지의 메뉴아이디, 각 화면의 js에 정의되어 있음)
		if (isEmpty(authorMenuObj[gMenuId])) { 
			var firstMenuUrl = authorMenuObj[Object.keys(authorMenuObj)[0]];
			console.log(firstMenuUrl);
			//location.href = firstMenuUrl; // 권한이 있는 첫메뉴로 이동
		}

	}
	
	, makeTitle : function(menuId) {
		var str = '';
		console.log("makeTitle : gMenuId : " + menuId);
		console.log(_nav.menuList);
		for( var f of _nav.menuList ) {
			if (f.menuId == menuId) {
				console.log(f);
				$("#pageTitle").text(f.menuNm);
				$("#titleLv2").text(f.menuNm);
				$("#titleLv1").text(_nav.getMenuNm(f.upperMenuId));
			}
		}
	}
	
	, getMenuNm : function(menuId) {
		for( var f of _nav.menuList ) {
			if (f.menuId == menuId)
				return f.menuNm;
		}
	}

} // _list
