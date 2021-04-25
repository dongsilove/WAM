/**
 * @FileName 	dept.js
 * @author 		ljpark
 * @Date 		2021.02.11
 * @Description 부서
 * @History
 * DATE			AUTHOR		NOTE	
 * -------------------------------------------------
 * 2021.02.11	ljpark		신규
 */
var gMenuId = 'AU_DEPT';
var deptList; // 부서 배열(상위부서용)
$(function() {
	
	// 상위부서 selectBox setting
	_commUtils.getSelectBox('/api/depts', $(".deptNm"),'deptNm','deptCd').done(function(r){
		deptList = r;
		_list.paginationInit();
		_list.getList(1);
	});
	
	// 영문 대문자처리
	$('#deptCd').on('blur', function(){ $(this).val($(this).val().toUpperCase())});
	
	// 저장 click시 호출
	$("#detailForm").validate({
	
		submitHandler : function () { //validation이 끝난 이후의 submit 직전 추가 작업할 부분
 			_ajaxUtils.ajax({"url" : "/api/depts/", "method": "PUT", "form" : $("#detailForm")
				,"successCallback": function(result) {
					_list.getList();
					detailForm.reset();
				}
			});
		}
		, rules: { //규칙 - id 값으로 
			  deptCd       	: {maxlength:5, required:true} 								
			, deptNm     	: {maxByteLength:200, required:true} 			    
			, upperDeptCd   : {maxlength:5}			    
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
		
		_ajaxUtils.ajax({"url" : "/api/depts", "form" : $("#searchForm")
			,"successCallback": function(data) { //console.log(data);
				$("#listData").html(""); // 목록 초기화
				data.content.forEach(function(f){
					processNull(f);
					let upperDeptNm = (deptList&&f.upperDeptCd)? deptList[f.upperDeptCd] :"";
					$("#listData").append("<tr onclick=\"_list.getDetail('"+ f.deptCd +"')\">"
						+"<td>" +f.deptCd+"</td><td>"+f.deptNm
						+"</td><td>"+upperDeptNm
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
	,deleteOne : function() {
		let pk = $("#deptCd").val();
		//console.log("삭제 호출" + pk);
		if (isEmpty(pk)) {alert('삭제할 데이터를 선택하세요.'); return;}
		if(confirm("삭제하시겠습니까? 삭제 후에는 복구가 불가능 합니다."))
		{
			_ajaxUtils.ajax({"url" : "/api/depts/"+pk, "method": "DELETE"
				,"successCallback": function(result) { console.log(result);
					alert("삭제되었습니다.");
					_list.getList();
					detailForm.reset();
				}
			});	
		}
	}

} // _list
