/**
 * @FileName 	author.js
 * @author 		ljpark
 * @Date 		2021.02.11
 * @Description 권한
 * @History
 * DATE			AUTHOR		NOTE	
 * -------------------------------------------------
 * 2021.02.11	ljpark		신규
 */
var gMenuId = 'AUTHOR';
var auth;
var ugrpCd;
// 검색
var getMenuAuthList = function() {
	if ( isEmpty($("#usergrpNm").val()) ) {
		alert("사용자그룹을 선택해 주세요."); return;
	}
	_list.getList(1); // 메뉴목록 & 권한정보 입히기
}

//메뉴 목록에 권한 정보 입히기
var getAuth = function() {
	//console.log(auth);
	for(key in auth) {
		//console.log(key + " : " +auth[key]);
		if(auth[key].indexOf('M') > -1) $("input:checkbox[name='" + key + "']:checkbox[value='M']").prop("checked", true);
		if(auth[key].indexOf('C') > -1) $("input:checkbox[name='" + key + "']:checkbox[value='C']").prop("checked", true);
		if(auth[key].indexOf('U') > -1) $("input:checkbox[name='" + key + "']:checkbox[value='U']").prop("checked", true);
		if(auth[key].indexOf('D') > -1) $("input:checkbox[name='" + key + "']:checkbox[value='D']").prop("checked", true);
		if(auth[key].indexOf('R') > -1) $("input:checkbox[name='" + key + "']:checkbox[value='R']").prop("checked", true);
		if(auth[key].indexOf('E') > -1) $("input:checkbox[name='" + key + "']:checkbox[value='E']").prop("checked", true);
	}
}
// 사용자그룹선택시 호출
var chgUsergrp = function(usergrpCd) {
	//console.log(_usrgrp.usrgrps);
	(_usrgrp.usrgrps).forEach(function(row){
		//console.log(row.ugrpCde);
		if (row.usergrpCd == usergrpCd) { // select 선택값과 사용자그룹목록키가 같다면
			$("#usergrpCd").val(row.usergrpCd);
			$("#usergrpNm").val(row.usergrpNm);
			//console.log(row);
			auth = JSON.parse(row.authorCn);
		}
	});
	
}
//사용자그룹목록
var _usrgrp = {
	usrgrps : []
	, list :	function() {

		_ajaxUtils.ajax({"url" : "/api/usergrps", "form" : $("#searchForm")
			,"successCallback": function(data) { //console.log(result);
				var objs = $(".ugrpCd"); 
				_usrgrp.usrgrps = data.content; //console.log(_usrgrp.usrgrps);
				var dataT = data.content;
				$.each(objs, function(index, item) {
					//console.log(item);
					$(item).find("option").remove();
					$(item).append("<option value=''>선택</option>");
					for (var i=0; i<dataT.length; i++) {
						var option = "<option value='"+dataT[i].usergrpCd+"'>"+dataT[i].usergrpNm+"</option>";
						$(item).append(option);
					}
				});
			}
		});
	}
}

var checkAll = function(opt) {
	if ($("input:checkbox[id='"+opt+"all']").is(":checked") == true)
		$("input:checkbox[value='"+opt+"']").prop("checked", true);
	else
		$("input:checkbox[value='"+opt+"']").prop("checked", false);
}

$(function() {
	
	// 상위부서 selectBox setting
	/*_commUtils.getSelectBox('/api/usergrps', $(".usergrpNm"),'usergrpNm','usergrpCd').done(function(r){
		usergrpList = r;
		_list.paginationInit();
		_list.getList(1);
	});*/
	_usrgrp.list(); // 사용자그룹 조회
	_list.getList(1); // 메뉴 조회
	
	// 저장 click시 호출
	$("#detailForm").validate({
	
		submitHandler : function () { //validation이 끝난 이후의 submit 직전 추가 작업할 부분
			if ( isEmpty($("#usergrpNm").val()) ) {
				alert("사용자그룹을 선택해 주세요."); return;
			}
	 		var arr = $("#detailForm").serializeArray(); //console.log(arr); // [{"name":"SWLPIPE", "value":"M"},{"name":"SWLPIPE", "value":"C"}]
			var obj = {}; 
			if(arr){ 
				jQuery.each(arr, function() { 
					if (obj[this.name]) { 
						obj[this.name] += this.value;  // obj = {"SWLPIPE":"MCUDRE"} 형태로 만들기
					}
					else {
						obj[this.name] = this.value; 
					}
				}); 
			} 	
			ugrpCd = $("#usergrpCd").val();
	        $("#usergrpCd").val($("#usergrpCd").val());
	        $("#authorCn").val(JSON.stringify(obj));
			_ajaxUtils.ajax({"url" : "/api/usergrps/", "method": "PUT", "form" : $("#authForm")
				,"successCallback": function(result) {
					detailForm.reset();
					_usrgrp.list();
					_list.getList();
				}
			});
		}
		, rules: { //규칙 - id 값으로 
			  usergrpCd       	: {maxlength:5, required:true} 								
			, usergrpNm     	: {maxByteLength:200, required:true} 			    
			, authorCn   : {maxlength:1000}			    
		}
	});
	
});


var _list = {
	pagination : {}
	,paginationInit : function() {
		var pagination = new tui.Pagination('paging', _paging.paginationOptions); // _paging :paging.js에 정의되어 있음.
		pagination.on('beforeMove', function(evt) { _list.getList(evt.page); });
		this.pagination = pagination;
	}
	,getList : function(page) {
		if (isEmpty(page)) page = 1;
		/*$("#searchtmp").attr("name",$("#searchName option:selected").val());
		$("#searchtmp").attr("value",$("#searchValue").val().toUpperCase());
		$("#page").val(page);*/
		
		_ajaxUtils.ajax({"url" : "/api/menus/listConnectBy", "form" : $("#searchForm")
			,"successCallback": function(data) { console.log(data);
				$("#listData").html(""); // 목록 초기화
				data.content.forEach(function(f, idx) { //console.log(i);
					var space = "&nbsp;&nbsp;"
					for(var i=0;i<f.depthNum;i++) space += space;
					var rowdata = '<tr>'
						+'<td  class="ta_l">'+space +f.menuId+'</td><td>'+f.menuNm+'</td><td>'+f.upperMenuId+'</td>';
						rowdata += '<td><input type="checkbox" name="'+f.menuId+'" value="M"><label>메뉴사용</label></td>'  
						+'<td><input type="checkbox" name="'+f.menuId+'" value="C"><label>등록</label></td>'  
						+'<td><input type="checkbox" name="'+f.menuId+'" value="U"><label>수정</label></td>'  
						+'<td><input type="checkbox" name="'+f.menuId+'" value="D"><label>삭제</label></td>'  
						+'<td><input type="checkbox" name="'+f.menuId+'" value="R"><label>조회</label></td>'  
						+'<td><input type="checkbox" name="'+f.menuId+'" value="E"><label>엑셀다운</label></td>';
					rowdata +='</tr>';
					$('#listData').append(rowdata);
				});
				
				if ($("#ugrpCd").val()) getAuth(); // 사용자그룹을 선택했다면 권한정보까지 보여준다.
				
				/*if (data.content.length == 0) {
					$("#listData").append("<tr><td colspan=9>조회결과가 없습니다.</td></tr>");
				}*/
			}
		});
	} // getList()
	,getDetail : function(deptCd) {
		mode="PUT"; // 수정모드
		_ajaxUtils.ajax({"url" : "/api/depts/"+deptCd
			,"successCallback": function(data) { console.log(data);
				for(key in data) {	
					_commUtils.setVal("detailForm", key, data[key] );
				}
			}
		});
	}

} // _list
