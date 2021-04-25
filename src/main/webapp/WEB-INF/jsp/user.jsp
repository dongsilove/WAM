<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
//========================================
// @brief 사용자정보
//========================================
// @history 
//  	2021.02.09 박이정(마인드원) 최초작성
//========================================
%>
<!DOCTYPE html>
<html lang="ko">
<jsp:include page="/WEB-INF/jsp/layout/header.jsp"/>
<script src="/js/user.js"></script>

<body>
    <div class="bg"></div>
    <div class="wrap">
        <jsp:include page="/WEB-INF/jsp/layout/top.jsp"/>
        <jsp:include page="/WEB-INF/jsp/layout/nav.jsp"/>

        <div class="container">
            <div class="title">
            	<div style="float:left;"><h1>사용자정보</h1></div>
            	<div class="location"><ul><li class="home"></li><li>사용자</li><li>사용자정보</li></ul></div>
            </div>
            <div class="cont_divide">
            	<div class="left">
		            <div class="write"><!-- 검색 -->
		                <div class="write_inner">
							<div class="write_search">
							<form name="searchForm" id="searchForm" method="GET">
								<input type="hidden" id="page" name="page">
								<input type="hidden" id="searchtmp" name="" value=""  />
								<span>
									소속부서:
									<select name="deptCd" onChange="_list.getList(1);" class="selectp deptNm"></select><!-- id="deptCd"  -->
								</span>
								<span>
									직급: 
									<select name="clsfCd" onChange="_list.getList(1);" class="selectp clsfCd"></select><!--  id="clsfCd" -->
								</span>
								<select name="searchName" id="searchName"   >
										<!-- <option value=''> -- 검색선택 -- </option> -->
										<option value="userId" >사용자아이디</option>
										<option value="userNm" >사용자명</option>
										<option value="ecnyYmd" >입사일</option>
								</select>
								<input type="text" name="searchValue" id="searchValue" value="" 
									placeholder="검색할 내용을 입력해주세요"  
									onkeypress="if(event.keyCode==13) { _list.getList(1); return false;}"/>
							</form>
							</div>
							<a href="#" onclick="_list.getList(1);">조회</a>
						</div> 
						
					</div>
		            <div class="container_inner task_list_wrap">
		                <div class="task_list">
		                <table id="list_t">
		                    <colgroup>
		                        <col style="width:15%">
		                        <col style="width:auto">
		                        <col style="width:15%">
		                        <col style="width:15%">
		                        <col style="width:15%">
		                        <col style="width:15%">
		                    </colgroup>
		                    <thead>
		                        <tr>
		                            <th scope="col">사용자아이디</th>
		                            <th scope="col">사용자명</th>
		                            <th scope="col">직급</th>
		                            <th scope="col">입사일</th>
		                            <th scope="col">소속부서</th>
		                            <th scope="col">사용자그룹</th>
		                        </tr>
		                    </thead>
		                    <tbody id="listData">
								
		                    </tbody>
		                </table>
		                </div>
		                <jsp:include page="/WEB-INF/jsp/layout/paging.jsp"/> 
		            </div>            	
            	</div>
            	<div class="right">
	                <div class="writer">
		                <div class="write_inner">
							<a href="#" onclick="detailForm.reset();">신규</a>
							<a href="#" onclick="$('#detailForm').submit();">저장</a>
							<a href="#" onclick="_list.deleteOne();">삭제</a>
						</div> 
					</div>
	                <div class="user_form">
	                <form name="detailForm" id="detailForm" onsubmit="return false;">
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
	                            <td><input type="text" name="userId" id="userId"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="userNm">사용자명</label></th>
	                            <td><input type="text" name="userNm" id="userNm"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="pwd">비밀번호</label></th>
	                            <td><input type="password" name="pwd" id="pwd"  autocomplete="off" ></td>
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
	                        <tr>
	                            <th class="required"><label for="usergrpCd">사용자그룹</label></th>
	                            <td>
	                                <select name="usergrpCd" class="usergrpNm"></select>
	                            </td>
	                        </tr>

	                        </tbody>
	                    </table>

	                </form>
	                </div>            	
            	</div>
            </div>

        </div><!--//container-->
    </div>
</body>
<script>

</script>
</html>