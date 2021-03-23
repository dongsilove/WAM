<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
//========================================
// @brief 데이터사전 > 수원시설 > 목록
//========================================
// @history 
//  	2021.03.02 박이정(마인드원) 최초작성
//========================================
%>
<!DOCTYPE html>
<html lang="ko">
<jsp:include page="/WEB-INF/jsp/layout/header.jsp"/>
<script>
var mode = 'list';
var splsysLocplcNm = '${splsysLocplcNm}'; // 계통소재지명
var splsysNm = '${splsysNm}'; 		// 계통
var prcNm = '${prcNm}';				// 공정
var worktypeNm = '${worktypeNm}';	// 공종
var searchName = '${searchName}';
var searchValue = '${searchValue}';
</script>
<script src="/js/asstfclty.js"></script>

<body>
    <div class="bg"></div>
    <div class="wrap">
        <jsp:include page="/WEB-INF/jsp/layout/top.jsp"/>
        <jsp:include page="/WEB-INF/jsp/layout/menu.jsp"/>

        <div class="container">
            <div class="title">
            	<div style="float:left;"><h1>수원시설</h1></div>
            	<div class="location"><ul><li class="home"></li><li>인벤토리</li><li>수원시설</li></ul></div>
            </div>
            
            <div class="write"><!-- 검색 -->
                <div class="write_inner">
                	<div style=""></div>
					<div class="write_search">
					<form name="searchForm" id="searchForm" method="GET">
						<input type="hidden" id="page" name="page">
						<input type="hidden" id="searchtmp" name="" value=""  />
						
						<span> 계통소재지 : 
							<select name="splsysLocplcNm" id="splsysLocplcNm" onChange="_list.getList(1);" class="splsysLocplcNm">
								   <option value='파장정수장' selected="selected">파장정수장 </option>
								   <option value='광교정수장'>광교정수장 </option>
							</select>
						</span>
						<span> 계통 : 
							<select name="splsysNm" id="splsysNm" onChange="_list.getList(1);" class="splsysNm">
								   <option value=''> </option>
							</select>
						</span>
						<span> 공정 : 
							<select name="prcNm" id="prcNm" onChange="_list.getList(1);" class="prcNm">
								   <option value=''> </option>
							</select>
						</span>
						<span> 공종 : 
							<select name="worktypeNm" id="worktypeNm" onChange="_list.getList(1);" class="worktypeNm">
								   <option value=''> </option>
							</select>
						</span>
						
						<select name="searchName" id="searchName" >
								<option value='asstSn'>자산조사번호</option>
								<option value="asstNm" >자산명</option>
								<option value="locplcNm" >설치위치</option>
								<option value="fomNm" >형식 및 용량</option>
								<option value="installYmd" >설치일</option>
								<option value="prcNm" >공정명</option>
								<option value="worktypeNm" >공종명</option>
								<option value="splsysNm" >계통명</option>
								<option value="splsysLocplcNm" >계통소재지</option>
								<option value="modifyId" >수정아이디</option>
						</select>
						<input type="text" name="searchValue" id="searchValue" value="" 
							placeholder="검색할 내용을 입력해주세요"  
							onkeypress="if(event.keyCode==13) {_list.getList(1); return false;}"/>
					</form>
					</div>
					<a href="#" onclick="_list.getList(1);">조회</a>
				</div> 
				
			</div>
            <div class="container_inner task_list_wrap">
            	<div class="tot_cnt" style="">총 <span id="tCnt"></span>건</div>
                <div class="task_list">
                <table id="list_t">
                    <colgroup>
                        <col style="width:3%">
                        <col style="width:6%">
                        <col style="width:15%">
                        <col style="width:6%">
                        <col style="width:7%">
                        <col style="width:8%">
                        <col style="width:auto">
                        <col style="width:20%">
                        <col style="width:6%">
                        <col style="width:7%">
                        <col style="width:7%">
                    </colgroup>
                    <thead>
                        <tr>
                            <th scope="col">번호</th>
                            <th scope="col">계통소재지</th>
                            <th scope="col">설치위치</th>
                            <th scope="col">계통</th>
                            <th scope="col">공정</th>
                            <th scope="col">공종</th>
                            <th scope="col">자산명</th>
                            <th scope="col">형식 및 용량</th>
                            <th scope="col">설치일</th>
                            <th scope="col">제조사</th>
                            <th scope="col">수정아이디</th>
                        </tr>
                    </thead>
                    <tbody id="listData">
						
                    </tbody>
                </table>
                </div>
                <jsp:include page="/WEB-INF/jsp/layout/paging.jsp"/> 
            </div>            	
            	

        </div><!--//container-->
    </div>
</body>
<script>

</script>
</html>