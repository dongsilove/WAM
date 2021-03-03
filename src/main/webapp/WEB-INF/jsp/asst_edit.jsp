<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
//========================================
// @brief 데이터사전 > 자산 > 등록,수정
//========================================
// @history 
//  	2021.03.02 박이정(마인드원) 최초작성
//========================================
%>
<!DOCTYPE html>
<html lang="ko">
<jsp:include page="/WEB-INF/jsp/layout/header.jsp"/>
<script src="/js/asst.js"></script>
 
<body>
    <div class="bg"></div>
    <div class="wrap">
        <jsp:include page="/WEB-INF/jsp/layout/top.jsp"/>
        <jsp:include page="/WEB-INF/jsp/layout/menu.jsp"/>

        <div class="container">
            <div class="title">
            	<div style="float:left;"><h1>자산</h1></div>
            	<div class="location"><ul><li class="home"></li><li>인벤토리</li><li>자산</li></ul></div>
            </div>
                <div class="writer task_list_wrap">
	                <div class="write_inner">
						<a href="#" onclick="detailForm.reset();">신규</a>
						<a href="#" onclick="$('#detailForm').submit();">저장</a>
						<!-- <a href="#" onclick="_list.deleteOne();">삭제</a> -->
					</div> 
				</div>
                <div class="user_form task_list_wrap">
                <form name="detailForm" id="detailForm" onsubmit="return false;">
					<input type="hidden" name="nmode" value="">
					<input type="hidden" name="pk" value="">
					<input type="hidden" name="page" value="">
                    <div class="ul_table">
                        <ul>
                            <li><label for="asstSn">자산일련번호</label></li>
                            <li><input type="text" name="asstSn" id="asstSn" value="" readonly ></li>
                        </ul>
                        <ul>
                            <li class="required"><label for="asstNm">자산명</label></li>
                            <li><input type="text" name="asstNm" id="asstNm" style="width:100%;"></li>
                        </ul>
                        <ul>
                            <li class="required"><label for="asstAccntNov">자산회계번호</label></li>
                            <li><input type="text" name="asstAccntNov" id="asstAccntNov"  value="" ></li>
                        </ul>
                        <ul>
                            <li class="required"><label for="asstAccntSclasNm">자산회계소분류명</label></li>
                            <li><input type="text" name="asstAccntSclasNm" id="asstAccntSclasNm"  value="" ></li>
                        </ul>
                        <ul>
                            <li class="required"><label for="locplcNm">소재지명</label></li>
                            <li><input type="text" name="locplcNm" id="locplcNm"  value=""  style="width:100%;"></li>
                        </ul>
                        <ul>
                            <li class="required"><label for="psitnNm">소속명</label></li>
                            <li><input type="text" name="psitnNm" id="psitnNm"  value=""  style="width:100%;"></li>
                        </ul>
                        <ul>
                            <li><label for="useYn">사용여부(불용여부)</label></li>
                            <li><input type="text" name="useYn" id="useYn"  value="" ></li>
                        </ul>
                        <ul>
                            <li class="required"><label for="frstAcqsYmd">최초취득일</label></li>
                            <li><input type="text" name="frstAcqsYmd" id="frstAcqsYmd"  value="" ></li>
                        </ul>
                        <ul>
                            <li><label for="frstAcqsPc">최초취득가</label></li>
                            <li><input type="text" name="frstAcqsPc" id="frstAcqsPc"  value="" ></li>
                        </ul>
                        <ul>
                            <li><label for="revalYmd">재평가일</label></li>
                            <li><input type="text" name="revalYmd" id="revalYmd"  value="" ></li>
                        </ul>
                        <ul>
                            <li><label for="revalAm">재평가액</label></li>
                            <li><input type="text" name="revalAm" id="revalAm"></li>
                        </ul>
                        <ul>
                            <li><label for="nowAcqsPc">현재취득가</label></li>
                            <li><input type="text" name="nowAcqsPc" id="nowAcqsPc"  value="" ></li>
                        </ul>
                        <ul>
                            <li><label for="nowUslfsvcCo">현재내용년수</label></li>
                            <li><input type="text" name="nowUslfsvcCo" id="nowUslfsvcCo"></li>
                        </ul>
                        <ul>
                            <li><label for="nowRsvmneySm">현재충당금계</label></li>
                            <li><input type="text" name="nowRsvmneySm" id="nowRsvmneySm"></li>
                        </ul>
                        <ul>
                            <li><label for="nowAcntbkAm">현재장부액</label></li>
                            <li><input type="text" name="nowAcntbkAm" id="nowAcntbkAm"></li>
                        </ul>
                        <ul>
                            <li><label for="yrDprtAm">연상각액</label></li>
                            <li><input type="text" name="yrDprtAm" id="yrDprtAm"></li>
                        </ul>
                        <ul>
                            <li><label for="co">수량(면적)</label></li>
                            <li><input type="text" name="co" id="co"></li>
                        </ul>
                        <ul>
                            <li><label for="fomNm">형식명</label></li>
                            <li><input type="text" name="fomNm" id="fomNm"  value="" style="width:100%"></li>
                        </ul>
                        <ul>
                            <li><label for="stndrdNm">규격명</label></li>
                            <li><input type="text" name="stndrdNm" id="stndrdNm"  value="" style="width:100%"></li>
                        </ul>
                        <ul>
                            <li><label for="installYmd">설치일(등기일)</label></li>
                            <li><input type="text" name="installYmd" id="installYmd"></li>
                        </ul>
                        <ul>
                            <li><label for="modelNm">모델명</label></li>
                            <li><input type="text" name="modelNm" id="modelNm"  value="" style="width:100%"></li>
                        </ul>
                        <ul>
                            <li><label for="modelNov">모델번호(등기번호)</label></li>
                            <li><input type="text" name="modelNov" id="modelNov"  value="" style="width:100%"></li>
                        </ul>
                        <ul>
                            <li><label for="suluctNm">구조명</label></li>
                            <li><input type="text" name="suluctNm" id="suluctNm"  value="" style="width:100%"></li>
                        </ul>
                        <ul>
                            <li><label for="mulqltNm">재질명</label></li>
                            <li><input type="text" name="mulqltNm" id="mulqltNm"  value="" style="width:100%"></li>
                        </ul>
                        <ul>
                            <li><label for="registId">등록아이디</label></li>
                            <li><input type="text" name="registId" id="registId"  value="" readonly></li>
                        </ul>
                        <ul>
                            <li><label for="registDt">등록일시</label></li>
                            <li><input type="text" name="registDt" id="registDt"  value="" readonly></li>
                        </ul>
                        <ul>
                            <li><label for="modifyId">수정아이디</label></li>
                            <li><input type="text" name="modifyId" id="modifyId"  value="" readonly></li>
                        </ul>
                        <ul>
                            <li><label for="modifyDt">수정일시</label></li>
                            <li><input type="text" name="modifyDt" id="modifyDt"  value="" readonly></li>
                        </ul>
                    </div>
                    <!-- <div class="btn_user_form_wrap">
                        <input class="btn_user_modify" type="button" value="저장" >
                        <input class="btn_user_delete" type="button" value="삭제" >									
						<input class="btn_user_submit" type="button" value="취소" onClick="history.back();">
                    </div> -->
                
                </form>
                </div>            	
            	

        </div><!--//container-->
    </div>
</body>
<script>
$(function() {
	
	_list.getDetail('${asstSn}');
	_commUtils.getCodes($(".asstCl"),"WD004"); // 도메인 분류 코드 조회(EX. 일시,번호,식별...)

	$("#detailForm").validate({
		
		submitHandler : function () { //validation이 끝난 이후의 submit 직전 추가 작업할 부분
			console.log("validation 성공 이후 ");
 			_ajaxUtils.ajax({"url" : "/api/assts/", "method": "PUT", "form" : $("#detailForm")
				,"successCallback": function(result) {
					_list.getList();
					detailForm.reset();
				}
			});
		}
		, rules: { //규칙 - id 값으로 
			  asstCl       : {required:true} 								
			, asstExprsnNm : {maxByteLength:200, required:true} 			
			, asstNm        : {maxByteLength:200, required:true} 			    
			, asstEnAbbr   : {maxlength:100, required:true} 	
			, asstEnNm     : {maxlength:200} 							
			, asstDc       : {maxByteLength:2000} 							
			, dataType       : {maxlength:100} 							
			, dataLt         : {number:true} 							
			, dcmlpointLt    : {number:true} 							
			, exprsnFom      : {maxByteLength:100} 							
			, unit           : {maxByteLength:50} 							
			, permValDc      : {maxByteLength:2000} 							
		}
	});
	
});
</script>
</html>