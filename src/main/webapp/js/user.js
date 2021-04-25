/**
 * @FileName 	user.js
 * @author 		ljpark
 * @Date 		2021.02.11
 * @Description 사용자
 * @History
 * DATE			AUTHOR		NOTE	
 * -------------------------------------------------
 * 2021.02.11	ljpark		신규
 */
var gMenuId = 'AU_USER';

var clsfList; // 직급코드 배열
var prjctList; // 프로젝트 배열
var deptList; // 부서 배열
var usergrpList; // 부서 배열
var mode = "";
$(function() {
	_datepicker.dateInit();
	// 직급코드 selectBox setting
	_commUtils.getCodes( $(".clsfCd"),'AU001').done(function(r){
		clsfList = r;
	}); 
	// 프로젝트 selectBox setting
	_commUtils.getSelectBox('/api/prjcts', $(".prjctNm"),'prjctNm','prjctSn').done(function(r){
		prjctList = r;
	}); 
	// 부서 selectBox setting
	_commUtils.getSelectBox('/api/depts', $(".deptNm"),'deptNm','deptCd').done(function(r){
		deptList = r;
	}); 
	// 사용자그룹 selectBox setting
	_commUtils.getSelectBox('/api/usergrps', $(".usergrpNm"),'usergrpNm','usergrpCd').done(function(r){
		usergrpList = r;
		console.log(usergrpList);
		_list.paginationInit();
		_list.getList(1);
	}); 
	
	// 저장 click시 호출
	$("#detailForm").validate({
	
		submitHandler : function () { //validation이 끝난 이후의 submit 직전 추가 작업할 부분
 			_ajaxUtils.ajax({"url" : "/api/users/", "method": "PUT", "form" : $("#detailForm")
				,"successCallback": function(result) {
					_list.getList();
					detailForm.reset();
				}
			});
		}
		, rules: { //규칙 - id 값으로 
			  userId     : {maxlength:50, required:true} 								
			, userNm     : {maxByteLength:200, required:true} 			    
			, pwd        : {minlength:4, required:true} 			    
			, clsfCd     : {maxlength:5, required:true} 			    
			, deptCd     : {maxlength:5, required:true} 			    
			, ecnyYmd    : {dateISO:true} 			    
			, usergrpCd  : {maxlength:5, required:true} 			    
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
		$("#searchtmp").attr("value",$("#searchValue").val());
		$("#page").val(page);
		
		_ajaxUtils.ajax({"url" : "/api/users", "form" : $("#searchForm")
			,"successCallback": function(data) { console.log(data);
				$("#listData").html(""); // 목록 초기화
				data.content.forEach(function(f){
					processNull(f); // null처리, 날짜형식 처리 (_commUtils.js)
					
					let clsfNm = (clsfList&&f.clsfCd)? clsfList[f.clsfCd] :""; // 직급코드
					let usergrpNm = (usergrpList&&f.usergrpCd)? usergrpList[f.usergrpCd]:""; // 사용자그룹
					$("#listData").append("<tr onclick=\"_list.getDetail('"+ f.userId +"')\">"
						+"<td>" +f.userId+"</td><td>"+f.userNm
						+"</td><td>"+clsfNm+"</td><td>"+f.ecnyYmd
						+"</td><td>"+deptList[f.deptCd]
						+"</td><td>"+ usergrpNm
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
	,getDetail : function(userId) {
		mode="PUT"; // 수정모드
		_ajaxUtils.ajax({"url" : "/api/users/"+userId
			,"successCallback": function(data) { console.log(data);
				for(key in data) {	
					if (key.indexOf("Ymd")>-1)
					    data[key] = data[key].replace(/(\d{4})(\d{2})(\d{2})/, '$1-$2-$3');	
					_commUtils.setVal("detailForm", key, data[key] );
				}
			}
		});
	}
	,deleteOne : function() {
		let pk = $("#userId").val();
		//console.log("삭제 호출" + pk);
		if (isEmpty(pk)) {alert('삭제할 데이터를 선택하세요.'); return;}
		if(confirm("삭제하시겠습니까? 삭제 후에는 복구가 불가능 합니다."))
		{
			_ajaxUtils.ajax({"url" : "/api/users/"+pk, "method": "DELETE"
				,"successCallback": function(result) { console.log(result);
					alert("삭제되었습니다.");
					_list.getList();
					detailForm.reset();
				}
			});	
		}
	}

} // _list
