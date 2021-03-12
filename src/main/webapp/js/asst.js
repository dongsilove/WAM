/**
 * @FileName 	asst.js
 * @author 		ljpark
 * @Date 		2021.02.11
 * @Description 자산
 * @History
 * DATE			AUTHOR		NOTE	
 * -------------------------------------------------
 * 2021.03.02	ljpark		신규
 */

$(function() {
	
	//공급계통 function(urls, objs ,textNm,valueNm)
	_commUtils.getSelectBox('/api/common/codes/SPSYS',$(".splsysNm"),"cdNm","cdNm"); 
	//공정
	_commUtils.getSelectBox('/api/common/codes/PRC',$(".prcNm"),"cdNm","cdNm"); 
	//공종
	_commUtils.getSelectBox('/api/common/codes/WTYPE',$(".worktypeNm"),"cdNm","cdNm"); 
	//회계대분류
	_commUtils.getSelectBox('/api/common/codes/ACLCL',$(".asstAccntLclasNm"),"cdNm","cdNm").done(function(r){
		if (mode == 'list') {
			$("#splsysNm").val(splsysNm);		//공급계통
			$("#prcNm").val(prcNm);			//공정
			$("#worktypeNm").val(worktypeNm);	//공종
			$("#asstAccntLclasNm").val(asstAccntLclasNm); //회계대분류
			$("#asstAccntLclasNm option").map(function(idx,opt){
				console.log(opt.value + ": " + opt.text);
			});
			$("#searchName").val(searchName);
			$("#searchValue").val(searchValue);
			_list.paginationInit();
			_list.getList(1);
		} else if (mode == 'edit') {
			_list.getDetail(asstSn);
		}
	}); 


	$("#detailForm").validate({ // form.submit()을 하면 호출된다. ajaxForm()보다 먼저 호출됨
		
		submitHandler : function () { //validation이 끝난 이후의 submit 직전 추가 작업할 부분
			console.log("validation 성공 이후 ");
 			/*_ajaxUtils.ajax({"url" : "/api/assts/", "method": "PUT", "form" : $("#detailForm")
				,"successCallback": function(result) {
					detailForm.reset();
					alert("저장되었습니다.");
					goList();
				}
			});*/
			//processYmd(pars); // '-'제거, 공백제거(commUtils.js)
			return true;
		}
		, rules: { //규칙 - id 값으로 
			  asstCl       : {required:true} 								
			, asstExprsnNm : {maxByteLength:200, required:true} 			
			, asstNm        : {maxByteLength:200, required:true} 			    
			, asstEnAbbr   : {maxlength:100, required:true} 	
			, asstEnNm     : {maxlength:200} 							
			, rm       : {maxByteLength:200} 							
			, dataType       : {maxlength:100} 							
			, dataLt         : {number:true} 							
			, dcmlpointLt    : {number:true} 							
			, exprsnFom      : {maxByteLength:100} 							
			, unit           : {maxByteLength:50} 							
			, permValDc      : {maxByteLength:2000} 							
		}
	});

	$('#detailForm').ajaxForm({
		url: "/api/assts",
		enctype: "multipart/form-data",
		dataType : "json",
		contentType : "application/json;charset=UTF-8",
		type: "PUT",
		beforeSubmit: function (data,form,option) {
		},
		success: function(data){
			//detailForm.reset();
			alert("저장되었습니다.");
			//_list.goList();	
			_list.getDetail(asstSn);
		},
		error:function(request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
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
		
		//$("#searchfrm")[0].reset(); //오른쪽 상세정보 리셋
		
		_ajaxUtils.ajax({"url" : "/api/assts", "form" : $("#searchForm")
			,"successCallback": function(data) { //console.log(data);
				$("#listData").html(""); // 목록 초기화
				data.content.forEach(function(f){
					processNull(f);
					
					$("#listData").append("<tr onclick=\"_list.goEdit('"+f.asstSn+"')\">"
						+"<td>" +f.asstSn+"</td><td>"+f.asstAccntLclasNm+"</td><td>"+f.asstAccntNov+"</td><td>"
						+f.asstNm+"</td><td>"+f.asstAccntSclasNm+"</td><td>"
						+f.locplcNm+"</td><td>"+f.frstAcqsYmd+"</td><td class='ralign'>"
						+f.frstAcqsPc+"</td><td>"+f.revalYmd+"</td><td class='ralign'>"+f.revalAm+"</td><td>"+f.nowUslfsvcCo+"</td>"
						+"</tr>"
					);
				});
				if (data.numberOfElements == 0) {
					$("#listData").append("<tr><td colspan=9>조회결과가 없습니다.</td></tr>");
				}
				_list.pagination.setTotalItems(data.totalElements); // 총레코드 수
				_list.pagination._paginate(page); // 조회 page
				$("#tCnt").text(_commUtils.numberWithCommas(data.totalElements));
			}
		});
	} // getList()
	,detailFormReset : function() {
		detailForm.reset();
		mode = "POST";
	}
	,goEdit(asstSn) {
		var params = $("#searchForm").serialize();
		params = encodeURIComponent(params);
		window.location = "/asst/edit?asstSn="+asstSn + "&params=" + params;
	}
	,goList() {
		let params = decodeURIComponent($("#params").val());
		window.location = "/asst/list?" + params;
	}
	,getDetail : function(asstSn) {
		
		mode="PUT"; // 수정모드
		_ajaxUtils.ajax({"url" : "/api/assts/"+asstSn
			,"successCallback": function(data) { console.log(data);
				for(key in data) {	
					_commUtils.setVal("detailForm", key, data[key] );
				}
			}
		});
		
		// 첨부파일조회
		_cmFile.list("T_AM_ASST",asstSn).done(function(list) {
			$.each(list,function(idx,data){
				var filelistHtml = '<li id="uploadFileName'+idx+'" style="margin-top:10px;"></li>';
				$('#uploadFileName'+idx).html(''); // 초기화
				$('#filelist').append(filelistHtml);
				// 파일명보여주기
				$('#uploadFileName'+idx).append('<a href="#" id="down'+idx+'" title="파일 다운로드">'+data.uploadFileNm+'</a>');
				$('#down'+idx).on('click', function(event) { // 파일명 click시 파일다운로드
					event.preventDefault();
					_cmFile.fileDownload(data.filePath, data.uploadFileNm, data.saveFileNm);
				});
				$('#uploadFileName'+idx).append('&nbsp;<a href="#" onclick="deleteOneFile('+data.fileSn
					+ ',\'' + data.filePath + '\',\'' + data.saveFileNm+'\')" '
					+'title="파일 삭제"><img src="/images/btn_edit_del.png"/></a>&nbsp;');
				var arrFileType = ['jpg','png','gif'];
				if (arrFileType.indexOf(data.fileTy.toLowerCase()) < 0) return; // 이미지 파일이 아니면 이미지 보여주기 필요없음.
				// 이미지 보여주기
				$('#filelist').append('<li><img class="uploadFileImg" src="/api/cmfiles/showImageFile/'+data.fileSn+'" /></li>');
				$('.uploadFileImg').css('max-width','100%');
			}); // $.each()
		}).fail(function() { // _cmFile.getCmFile()에서 dfd.reject()시 호출됨.
			//console.log('첨부파일 없음');
		});
	}
	,save : function() {

	}
	,deleteOne : function() {
		var pk = $("#asstSn").val();
		//console.log("삭제 호출" + pk);
		if (isEmpty(pk)) {alert('삭제할 데이터를 선택하세요.'); return;}
		if(confirm("삭제하시겠습니까? 삭제 후에는 복구가 불가능 합니다.")) {
			// 삭제
			_ajaxUtils.ajax({"url" : "/api/assts/"+pk, "method": "DELETE"
				,"successCallback": function(result) { console.log(result);
					alert("삭제되었습니다.");
					_list.getList();
					detailForm.reset();
				}
			});	
			// 첨부파일 삭제
		}
	}
} // _list

// 첨부파일 건별 삭제
function deleteOneFile(_fileSn, filePath, saveFileNm) {
	_cmFile.deleteOne(_fileSn,filePath,saveFileNm).done(function(msg) {
		alert(msg);
		_list.goEdit(asstSn) // 상세조회 다시
	});
}
