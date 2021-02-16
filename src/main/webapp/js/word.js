/**
 * @FileName 	words.js
 * @author 		ljpark
 * @Date 		2021.02.11
 * @Description 도메인
 * @History
 * DATE			AUTHOR		NOTE	
 * -------------------------------------------------
 * 2021.02.11	ljpark		신규
 */

$(function() {
	
	_list.paginationInit();
	_list.getList(1);
	_commUtils.getCodes($("#wordCl"),"WD004"); // 도메인 분류 코드 조회(EX. 일시,번호,식별...)
	
	$("#detailForm").validate({
	
		submitHandler : function () { //validation이 끝난 이후의 submit 직전 추가 작업할 부분
			console.log("validation 성공 이후 ");
 			_ajaxUtils.ajax({"url" : "/words/", "method": "PUT", "form" : $("#detailForm")
				,"successCallback": function(result) {
					_list.getList();
					detailForm.reset();
				}
			});
		}
		, rules: { //규칙 - id 값으로 
			  wordCl       : {required:true} 								
			, wordExprsnNm : {maxByteLength:200, required:true} 			
			, domanNm        : {maxByteLength:200, required:true} 			    
			, wordEnAbbr   : {maxByteLength:100, required:true} 	
			, wordEnNm     : {maxByteLength:200} 							
			, wordDc       : {maxByteLength:2000} 							
			, dataType       : {maxByteLength:100} 							
			, dataLt         : {number:true} 							
			, dcmlpointLt    : {number:true} 							
			, exprsnFom      : {maxByteLength:100} 							
			, unit           : {maxByteLength:50} 							
			, permValDc      : {maxByteLength:2000} 							
		}
	});
	
});

var _list = {
	pagination : {}
	,paginationInit : function() {
		console.log( _paging.paginationOptions);
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
		
		//$("#searchfrm")[0].reset(); //오른쪽 상세정보 리셋
		
		_ajaxUtils.ajax({"url" : "/words", "form" : $("#searchForm")
			,"successCallback": function(data) { //console.log(data);
				$("#listData").html(""); // 목록 초기화
				data.content.forEach(function(f){
					processNull(f);
					$("#listData").append("<tr onclick=\"_list.getDetail('"+f.wordSn+"')\">"
						+"<td>" +f.wordSn+"</td><td>"+f.wordNm+"</td><td>"
						+f.wordEnAbbr+"</td><td>"+f.wordEnNm+"</td><td>"
						+f.synonm+"</td><td>"+f.prhibtWord+"</td><td>"+f.themaSe+"</td><td>"+f.registDt+"</td>"
						+"</tr>"
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
	,getDetail : function(wordSn) {
		mode="PUT"; // 수정모드
		_ajaxUtils.ajax({"url" : "/words/"+wordSn
			,"successCallback": function(data) { console.log(data);
				for(key in data) {	
					_commUtils.setVal("detailForm", key, data[key] );
				}
			}
		});
	}
	,save : function() {

	}
	,deleteOne : function() {
		var pk = $("#wordSn").val();
		//console.log("삭제 호출" + pk);
		if (isEmpty(pk)) {alert('삭제할 데이터를 선택하세요.'); return;}
		if(confirm("삭제하시겠습니까? 삭제 후에는 복구가 불가능 합니다."))
		{
			_ajaxUtils.ajax({"url" : "/words/"+pk, "method": "DELETE"
				,"successCallback": function(result) { console.log(result);
					alert("삭제되었습니다.");
					_list.getList();
					detailForm.reset();
				}
			});	
		}
	}
} // _list
