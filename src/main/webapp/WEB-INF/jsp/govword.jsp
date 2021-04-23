<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
//========================================
// @brief 데이터사전 > 행정단어 > 목록
//========================================
// @history 
//  	2021.02.09 박이정(마인드원) 최초작성
//========================================
%>
<!DOCTYPE html>
<html lang="ko">
<jsp:include page="/WEB-INF/jsp/layout/header.jsp"/>
<script src="/js/govword.js"></script>
<body>
    <div class="bg"></div>
    <div class="wrap">
        <jsp:include page="/WEB-INF/jsp/layout/top.jsp"/>
        <jsp:include page="/WEB-INF/jsp/layout/nav.jsp"/>

        <div class="container">
            <div class="title">
            	<div style="float:left;"><h1>행정단어</h1></div>
            	<div class="location"><ul><li class="home"></li><li>데이터사전</li><li>행정단어</li></ul></div>
            </div>
            <div class="write"><!-- 검색 -->
                <div class="write_inner">
					<div class="write_search">
					<form name="searchForm" id="searchForm" method="GET" onsubmit="search()" >
						<input type="hidden" id="page" name="page">
						<input type="hidden" id="searchtmp" name="" value=""  />
						<span> 주제 구분 : 
							<select name="themaSe" id="themaSe" onChange="_list.getList(1)" >
							</select>
						</span>
						<select name="searchName" id="searchName" onChange=""  >
								<!-- <option value=''> -- 검색선택 -- </option> -->
								<option value="wordNm" >단어명</option>
								<option value="wordEnAbbr" >단어영문약어</option>
								<option value="wordEnNm" >단어영문명</option>
								<option value="wordSe" >구분</option>
								<option value="sttusSe" >상태구분</option>
								<option value="themaSe" >주제구분</option>
								<!-- <option value="registYmd" >등록일</option> -->
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
                	<caption>행정단어</caption>
                    <colgroup>
                        <col style="width:5%">
                        <col style="width:10%">
                        <col style="width:10%">
                        <col style="width:15%">
                        <col style="width:auto">
                        <col style="width:7%">
                        <col style="width:10%">
                        <col style="width:10%">
                        <col style="width:8%">
                    </colgroup>
                    <thead>
                        <tr>
                            <th scope="col">번호</th>
                            <th scope="col">단어명</th>
                            <th scope="col">단어영문약어</th>
                            <th scope="col">단어영문명</th>
                            <th scope="col">단어설명</th>
                            <th scope="col">구분</th>
                            <th scope="col">주제 구분</th>
                            <th scope="col">상태 구분</th>
                            <th scope="col">등록 일</th>
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
</html>