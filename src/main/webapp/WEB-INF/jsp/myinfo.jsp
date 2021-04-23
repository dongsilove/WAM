<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
//========================================
// @brief 데이터사전 > 개인정보수정 
//========================================
// @history 
//  	2021.02.09 박이정(마인드원) 최초작성
//========================================
%>
<!DOCTYPE html>
<html lang="ko">
<jsp:include page="/WEB-INF/jsp/layout/header.jsp"/>
<script src="/js/myinfo.js"></script>
<body>
    <div class="bg"></div>
    <div class="wrap">
        <jsp:include page="/WEB-INF/jsp/layout/top.jsp"/>
        <jsp:include page="/WEB-INF/jsp/layout/nav.jsp"/>

        <div class="container">
		
            <div class="title"><h1>개인정보수정</h1></div>
            <div class="container_inner user_wrap">
                <h4> :: </h4>
                <div class="user_form">
                <form name="detailForm" id="detailForm" >
					<input type="hidden" name="nmode" value="">
					<input type="hidden" name="pk" value="">
					<input type="hidden" name="page" value="">
                    <table class="edit_table">
                        <colgroup>
                            <col style="width:15%">
                            <col style="auto">
                        </colgroup>
                        <tbody>
	                        <tr>
	                            <th class="required"><label for="userId">사용자아이디</label></th>
	                            <td><input type="text" name="userId" id="userId"  value="" readonly></td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="userNm">사용자명</label></th>
	                            <td><input type="text" name="userNm" id="userNm"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="pwd">비밀번호</label></th>
	                            <td><input type="password" name="pwd" id="pwd"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="checkPwd">비밀번호 확인</label></th>
	                            <td><input type="password" name="checkPwd" id="checkPwd"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="clsfCd">직급코드</label></th>
	                            <td>
	                                <select name="clsfCd" id="clsfCd" class="clsfCd"></select>
	                            </td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="ecnyYmd">입사일</label></th>
	                            <td>
	                            	<span class="date_input">
	                            	<input class="datepicker input_calendar" type="text" name="ecnyYmd">
	                            	</span>
	                            </td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="deptCd">소속부서</label></th>
	                            <td>
	                                <select name="deptCd" id="deptCd" class="deptNm"></select>
	                            </td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="prjctSn">담당프로젝트</label></th>
	                            <td>
	                                <select name="prjctSn" class="prjctNm"></select>
	                            </td>
	                        </tr>
                        </tbody>
                    </table>
                    <div class="btn_user_form_wrap">
                        <input class="btn_user_modify" type="button" value="저장"  onclick="$('#detailForm').submit();">
                        <!-- <input class="btn_user_delete" type="button" value="삭제" > -->									
						<input class="btn_user_submit" type="button" value="취소" onClick="history.back();">
                    </div>
                
                </form>
                </div>
            </div>
        </div><!--//user_info-->
    </div><!--//wrap-->

<script>

</script>

</body>
</html>