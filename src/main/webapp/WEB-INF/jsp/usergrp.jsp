<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
//========================================
// @brief 사용자그룹
//========================================
// @history 
//  	2021.02.09 박이정(마인드원) 최초작성
//========================================
%>
<!DOCTYPE html>
<html lang="ko">
<jsp:include page="/WEB-INF/jsp/layout/header.jsp"/>
<script src="/js/usergrp.js"></script>

<body>
    <div class="bg"></div>
    <div class="wrap">
        <jsp:include page="/WEB-INF/jsp/layout/top.jsp"/>
        <jsp:include page="/WEB-INF/jsp/layout/nav.jsp"/>

        <div class="container">
            <div class="title">
            	<div style="float:left;"><h1 id="pageTitle">사용자그룹</h1></div>
            	<div class="location"><ul><li class="home"></li><li id="titleLv1">시스템</li><li id="titleLv2">사용자그룹</li></ul></div>
            </div>
            <div class="cont_divide">
            	<div class="left">
		            <div class="write"><!-- 검색 -->
		                <div class="write_inner">
							<div class="write_search">
							<form name="searchForm" id="searchForm" method="GET">
								<input type="hidden" id="page" name="page">
								<input type="hidden" id="searchtmp" name="" value=""  />
								
								<select name="searchName" id="searchName"   >
										<!-- <option value=''> -- 검색선택 -- </option> -->
										<option value="usergrpNm" >사용자그룹명</option>
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
		                        <col style="width:20%">
		                        <col style="width:auto">
		                    </colgroup>
		                    <thead>
		                        <tr>
		                            <th scope="col">사용자그룹코드</th>
		                            <th scope="col">사용자그룹명</th>
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
	                            <th class="required"><label for="usergrpCd">사용자그룹코드</label></th>
	                            <td><input type="text" name="usergrpCd" id="usergrpCd"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="usergrpNm">사용자그룹명</label></th>
	                            <td><input type="text" name="usergrpNm" id="usergrpNm"  value="" ></td>
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