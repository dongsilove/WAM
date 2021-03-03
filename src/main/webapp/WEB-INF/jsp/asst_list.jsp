<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
//========================================
// @brief 데이터사전 > 자산 > 목록
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
            
            <div class="write"><!-- 검색 -->
                <div class="write_inner">
					<div class="write_search">
					<form name="searchForm" id="searchForm" method="GET">
						<input type="hidden" id="page" name="page">
						<input type="hidden" id="searchtmp" name="" value=""  />
						
						<span> 자산분류 : 
							<select name="asstCl" id="asstCl" onChange="_list.getList(1);" class="asstCl">
								   <option value=''> -- 주제구분 선택 -- </option>
							</select>
						</span>
						
						<select name="searchName" id="searchName" >
								<!-- <option value=''> -- 검색선택 -- </option> -->
								<option value="asstNm" >자산명</option>
								<option value="asstAccntNov" >자산회계번호</option>
								<option value="asstAccntSclasNm" >자산회계소분류명</option>
								<option value="locplcNm" >소재지명</option>
								<option value="psitnNm" >소속명</option>
								<option value="frstAcqsYmd" >최초취득일</option>
								<option value="revalYmd" >재평가일</option>
								<option value="nowUslfsvcCo" >현재내용년수</option>
								<option value="prcNm" >공정명</option>
								<option value="worktypeNm" >공종명</option>
								<option value="splsysNm" >공급계통명</option>
								<option value="splsysLocplcNm" >공급계통소재지</option>
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
                <div class="task_list">
                <table id="list_t">
                    <colgroup>
                        <col style="width:3%">
                        <col style="width:6%">
                        <col style="width:15%">
                        <col style="width:12%">
                        <col style="width:auto">
                        <col style="width:8%">
                        <col style="width:8%">
                        <col style="width:8%">
                        <col style="width:8%">
                        <col style="width:5%">
                    </colgroup>
                    <thead>
                        <tr>
                            <th scope="col">번호</th>
                            <th scope="col">자산회계번호</th>
                            <th scope="col">자산명</th>
                            <th scope="col">자산회계소분류명</th>
                            <th scope="col">소재지명</th>
                            <th scope="col">최초취득일</th>
                            <th scope="col">최초취득가</th>
                            <th scope="col">재평가일</th>
                            <th scope="col">재평가액</th>
                            <th scope="col">현재내용년수</th>
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
$(function() {
	
	_list.paginationInit();
	_list.getList(1);
	_commUtils.getCodes($(".asstCl"),"WD004"); // 도메인 분류 코드 조회(EX. 일시,번호,식별...)

});
</script>
</html>