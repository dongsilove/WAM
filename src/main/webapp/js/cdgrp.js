/**
 * @FileName 	cdgrp.js
 * @author 		ljpark
 * @Date 		2021.02.11
 * @Description 코드그룹
 * @History
 * DATE			AUTHOR		NOTE	
 * -------------------------------------------------
 * 2021.02.11	ljpark		신규
 */

$(function() {
	
	_list.paginationInit();
	_list.getList(1);
	
	// 영문 대문자처리
	$('#grpCd').on('blur', function(){ $(this).val($(this).val().toUpperCase())});
	$('#tableNm').on('blur', function(){ $(this).val($(this).val().toUpperCase())});
	$('#columnNm').on('blur', function(){ $(this).val($(this).val().toUpperCase())});
	
	// 저장 click시 호출
	$("#detailForm").validate({
	
		submitHandler : function () { //validation이 끝난 이후의 submit 직전 추가 작업할 부분
 			_ajaxUtils.ajax({"url" : "/api/cdgrps/", "method": "PUT", "form" : $("#detailForm")
				,"successCallback": function(result) {
					_list.getList();
					detailForm.reset();
				}
			});
		}
		, rules: { //규칙 - id 값으로 
			  grpCd       : {maxlength:5, required:true} 								
			, grpCdNm     : {maxByteLength:200, required:true} 			    
			, tableNm     : {maxByteLength:200, required:true} 			    
			, columnNm    : {maxByteLength:200, required:true} 			    
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
		$("#searchtmp").attr("name",$("#searchName option:selected").val());
		$("#searchtmp").attr("value",$("#searchValue").val().toUpperCase());
		$("#page").val(page);
		
		_ajaxUtils.ajax({"url" : "/api/cdgrps", "form" : $("#searchForm")
			,"successCallback": function(data) { //console.log(data);
				$("#listData").html(""); // 목록 초기화
				data.content.forEach(function(f){
					processNull(f);
					$("#listData").append("<tr onclick=\"_list.getDetail('"+ f.grpCd +"')\">"
						+"<td>" +f.grpCd+"</td><td>"+f.grpCdNm
						+"</td><td>"+f.tableNm+"</td><td>"+f.columnNm
						+"</td></tr>"
					);
				});
				if (data.numberOfElements == 0) {
					$("#listData").append("<tr><td colspan=9>조회결과가 없습니다.</td></tr>");
				}
				_list.pagination.setTotalItems(data.totalElements); // 총레코드 수
				_list.pagination._paginate(page); // 조회 page
			}
		});
	} // getList()
	,getDetail : function(grpCd) {
		mode="PUT"; // 수정모드
		_ajaxUtils.ajax({"url" : "/api/cdgrps/"+grpCd
			,"successCallback": function(data) { console.log(data);
				for(key in data) {	
					_commUtils.setVal("detailForm", key, data[key] );
				}
			}
		});
	}
	,deleteOne : function() {
		let pk = $("#grpCd").val();
		//console.log("삭제 호출" + pk);
		if (isEmpty(pk)) {alert('삭제할 데이터를 선택하세요.'); return;}
		if(confirm("삭제하시겠습니까? 삭제 후에는 복구가 불가능 합니다."))
		{
			_ajaxUtils.ajax({"url" : "/api/cdgrps/"+pk, "method": "DELETE"
				,"successCallback": function(result) { console.log(result);
					alert("삭제되었습니다.");
					_list.getList();
					detailForm.reset();
				}
			});	
		}
	}
} // _list
