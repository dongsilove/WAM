/**
 * @FileName 	prjct.js
 * @author 		ljpark
 * @Date 		2021.02.11
 * @Description 프로젝트
 * @History
 * DATE			AUTHOR		NOTE	
 * -------------------------------------------------
 * 2021.02.11	ljpark		신규
 */
var gMenuId = 'CM_PRJCT';
$(function() {
	_datepicker.dateInit();
	_list.paginationInit();
	_list.getList(1);
	
	$("#detailForm").validate({
	
		submitHandler : function () { //validation이 끝난 이후의 submit 직전 추가 작업할 부분
			console.log($("input[name=prjctEndYmd]").val());		
 			_ajaxUtils.ajax({"url" : "/api/prjcts/", "method": "PUT", "form" : $("#detailForm")
				,"successCallback": function(result) {
					_list.getList();
					detailForm.reset();
				}
			});
		}
		, rules: { //규칙 - id 값으로 
			  prjctSn        : {number:true} 								
			, prjctNm        : {maxByteLength:200, required:true} 			    
			, prjctDc        : {maxByteLength:2000} 							
			, prjctBeginYmd   : {dateISO:true} 							
			, prjctEndYmd    : {dateISO:true} 							
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
		//console.log($("#page").val());
		
		_ajaxUtils.ajax({"url" : "/api/prjcts", "form" : $("#searchForm")
			,"successCallback": function(data) { console.log(data);
				$("#listData").html(""); // 목록 초기화
				data.content.forEach(function(f){
					processNull(f);
					$("#listData").append("<tr onclick=\"_list.getDetail('"+f.prjctSn+"')\">"
						+"<td>" +f.prjctSn+"</td><td>"
						+f.prjctNm+"</td><td>"+f.prjctDc+"</td><td>"
						+f.prjctBeginYmd+"</td><td>"+f.prjctEndYmd
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
	,detailFormReset : function() {
		detailForm.reset();
		mode = "POST";
	}
	,getDetail : function(prjctSn) {
		mode="PUT"; // 수정모드
		_ajaxUtils.ajax({"url" : "/api/prjcts/"+prjctSn
			,"successCallback": function(data) { console.log(data);
				for(key in data) {
					if (key.indexOf("Ymd")>-1)
					    data[key] = data[key].replace(/(\d{4})(\d{2})(\d{2})/, '$1-$2-$3');	
					_commUtils.setVal("detailForm", key, data[key] );
				}
			}
		});
	}
	,save : function() {

	}
	,deleteOne : function() {
		var pk = $("#prjctSn").val();
		//console.log("삭제 호출" + pk);
		if (isEmpty(pk)) {alert('삭제할 데이터를 선택하세요.'); return;}
		if(confirm("삭제하시겠습니까? 삭제 후에는 복구가 불가능 합니다."))
		{
			_ajaxUtils.ajax({"url" : "/api/prjcts/"+pk, "method": "DELETE"
				,"successCallback": function(result) { console.log(result);
					alert("삭제되었습니다.");
					_list.getList();
					detailForm.reset();
				}
			});	
		}
	}
} // _list
