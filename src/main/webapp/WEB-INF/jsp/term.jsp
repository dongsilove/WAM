<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
//========================================
// @brief 데이터사전 > 용어 > 목록
//========================================
// @history 
//  	2021.02.09 박이정(마인드원) 최초작성
//========================================
%>
<!DOCTYPE html>
<html lang="ko">
<jsp:include page="/WEB-INF/jsp/layout/header.jsp"/>
<script src="/js/term.js"></script>

<body>
    <div class="bg"></div>
    <div class="wrap">
        <jsp:include page="/WEB-INF/jsp/layout/top.jsp"/>
        <jsp:include page="/WEB-INF/jsp/layout/nav.jsp"/>

        <div class="container">
            <div class="title">
            	<div style="float:left;"><h1>용어</h1></div>
            	<div class="location"><ul><li class="home"></li><li>데이터사전</li><li>용어</li></ul></div>
            </div>
            <div class="cont_divide">
            	<div class="left">
		            <div class="write"><!-- 검색 -->
		                <div class="write_inner">
							<div class="write_search">
							<form name="searchForm" id="searchForm" method="GET">
								<input type="hidden" id="page" name="page">
								<input type="hidden" id="searchtmp" name="" value=""  />
								<span> 프로젝트 : 
									<select name="prjctSn" onChange="_list.getList(1);" class="prjctNm">
									</select>
								</span>
								<span> 도메인 : 
									<select name="domainSn" onChange="_list.getList(1);" class="domainSn">
									</select>
								</span>
								<select name="searchName" id="searchName"   >
										<!-- <option value=''> -- 검색선택 -- </option> -->
										<option value="termNm" >용어명</option>
										<option value="termEnAbbr" >용어영문약어</option>
										<option value="termEnNm" >용어영문명</option>
										<option value="dataFom" >데이터형식</option>
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
		                        <col style="width:5%">
		                        <col style="width:15%">
		                        <col style="width:12%">
		                        <col style="width:15%">
		                        <col style="width:auto">
		                        <col style="width:10%">
		                        <col style="width:10%">
		                        <col style="width:8%">
		                        <col style="width:8%">
		                        <col style="width:8%">
		                    </colgroup>
		                    <thead>
		                        <tr>
		                            <th scope="col">번호</th>
		                            <th scope="col">용어구분명</th>
		                            <th scope="col">용어명</th>
		                            <th scope="col">용어영문약어</th>
		                            <th scope="col">용어영문명</th>
		                            <th scope="col">도메인명</th>
		                            <th scope="col">데이터형식</th>
		                            <th scope="col">프로젝트</th>
		                            <th scope="col">등록일시</th>
		                            <th scope="col">수정일시</th>
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
							<a href="#" onclick="_list.resetForm();">신규</a>
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
	                            <th><label for="termSn">용어 일련번호</label></th>
	                            <td><input type="text" name="termSn" id="termSn" value="" readonly ></td>
	                        </tr>
	                        <tr title="단어를 space로 구분하여 입력하면 기등록되어 있는 단어를 조회합니다.">
	                            <th class="required"><label for="termSeNm">용어구분명</label></th>
	                            <td><span class="tddc">* 단어를 space로 구분하여 입력하면 단어 자동검색</span>
	                                <input type="text" name="termSeNm" id="termSeNm"  value="" >
	                            </td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="termNm">용어명</label></th>
	                            <td><input type="text" name="termNm" id="termNm"  value="" readonly></td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="termEnAbbr">용어영문약어</label></th>
	                            <td><input type="text" name="termEnAbbr" id="termEnAbbr"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="termEnNm">용어영문명</label></th>
	                            <td><input type="text" name="termEnNm" id="termEnNm"  value="" style="width:100%"></td>
	                        </tr>
	                        <tr>
	                            <th><label for="termDc">용어설명</label></th>
	                            <td><textarea name="termDc" id="termDc"  value="" style="width:100%;"></textarea>
	                        </tr>
	                        <tr title="도메인을 입력하면 기등록되어 있는 도메인표현명으로 도메인을 조회합니다.">
	                            <th class="required"><label for="domainNm">도메인명</label></th>
	                            <td><span class="tddc">* 입력시 도메인표현명으로 자동검색</span><br>
	                            	<input type="text" name="domainNm" id="domainNm"  value="" autocomplete="off"> 
	                            	<input type="hidden" name="domainSn" id="domainSn"  value="">
	                            </td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="dataFom">데이터 형식</label></th>
	                            <td><input type="text" name="dataFom" id="dataFom"  value="" style="width:100%"></td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="prjctSn">프로젝트</label></th>
	                            <td>
	                                <select name="prjctSn" id="prjctSn" required class="prjctNm"></select>
	                            </td>
	                        </tr>
	                        <tr>
	                            <th><label for="registId">등록아이디</label></th>
	                            <td><input type="text" name="registId" id="registId"  value="" readonly></td>
	                        </tr>
	                        <tr>
	                            <th><label for="registDt">등록일시</label></th>
	                            <td><input type="text" name="registDt" id="registDt"  value="" readonly></td>
	                        </tr>
	                        <tr>
	                            <th><label for="modifyId">수정아이디</label></th>
	                            <td><input type="text" name="modifyId" id="modifyId"  value="" readonly></td>
	                        </tr>
	                        <tr>
	                            <th><label for="modifyDt">수정일시</label></th>
	                            <td><input type="text" name="modifyDt" id="modifyDt"  value="" readonly></td>
	                        </tr>
	                        </tbody>
	                    </table>
	                    <div id="nmList">
	                    </div>
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