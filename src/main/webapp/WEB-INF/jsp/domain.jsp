<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
//========================================
// @brief 데이터사전 > 도메인 > 목록
//========================================
// @history 
//  	2021.02.09 박이정(마인드원) 최초작성
//========================================
%>
<!DOCTYPE html>
<html lang="ko">
<jsp:include page="/WEB-INF/jsp/layout/header.jsp"/>
<script src="/js/domain.js"></script>

<body>
    <div class="bg"></div>
    <div class="wrap">
        <jsp:include page="/WEB-INF/jsp/layout/top.jsp"/>
        <jsp:include page="/WEB-INF/jsp/layout/nav.jsp"/>

        <div class="container">
            <div class="title">
            	<div style="float:left;"><h1>도메인</h1></div>
            	<div class="location"><ul><li class="home"></li><li>데이터사전</li><li>도메인</li></ul></div>
            </div>
            <div class="cont_divide">
            	<div class="left">
		            <div class="write"><!-- 검색 -->
		                <div class="write_inner">
							<div class="write_search">
							<form name="searchForm" id="searchForm" method="GET">
								<input type="hidden" id="page" name="page">
								<input type="hidden" id="searchtmp" name="" value=""  />
								
								<span> 도메인분류 : 
									<select name="domainCl" id="domainCl" onChange="_list.getList(1);" class="domainCl">
										   <option value=''> -- 주제구분 선택 -- </option>
									</select>
								</span>
								
								<select name="searchName" id="searchName" >
										<!-- <option value=''> -- 검색선택 -- </option> -->
										<option value="domainNm" >도메인명</option>
										<option value="domainExprsnNm" >도메인표현명</option>
										<option value="domainEnAbbr" >도메인영문약어</option>
										<option value="domainEnNm" >도메인영문명</option>
										<option value="dataType" >데이터타입</option>
										<option value="dataLt" >데이터길이</option>
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
		                    <colgroup>
		                        <col style="width:5%">
		                        <col style="width:8%">
		                        <col style="width:10%">
		                        <col style="width:10%">
		                        <col style="width:12%">
		                        <col style="width:auto">
		                        <col style="width:10%">
		                        <col style="width:10%">
		                        <col style="width:10%">
		                        <col style="width:8%">
		                    </colgroup>
		                    <thead>
		                        <tr>
		                            <th scope="col">번호</th>
		                            <th scope="col">도메인분류</th>
		                            <th scope="col">도메인표현명</th>
		                            <th scope="col">도메인명</th>
		                            <th scope="col">도메인영문약어</th>
		                            <th scope="col">도메인영문명</th>
		                            <th scope="col">데이터타입</th>
		                            <th scope="col">데이터길이</th>
		                            <th scope="col">소수점길이</th>
		                            <th scope="col">등록 일</th>
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
	                            <th><label for="domainSn">도메인 일련번호</label></th>
	                            <td><input type="text" name="domainSn" id="domainSn" value="" readonly ></td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="domainCl">도메인 분류</label></th>
	                            <td>
	                                <select name="domainCl" id="domainCl" required class="domainCl"></select>
	                            </td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="domainExprsnNm">도메인표현명</label></th>
	                            <td><input type="text" name="domainExprsnNm" id="domainExprsnNm"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="domainNm">도메인명</label></th>
	                            <td><input type="text" name="domainNm" id="domainNm"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="domainEnAbbr">도메인영문약어</label></th>
	                            <td><input type="text" name="domainEnAbbr" id="domainEnAbbr"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="domainEnNm">도메인영문명</label></th>
	                            <td><input type="text" name="domainEnNm" id="domainEnNm"  value="" style="width:100%"></td>
	                        </tr>
	                        <tr>
	                            <th><label for="domainDc">도메인설명</label></th>
	                            <td><textarea name="domainDc" id="domainDc"  value="" style="width:100%;"></textarea>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="dataType">데이터타입</label></th>
	                            <td><input type="text" name="dataType" id="dataType"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th><label for="dataLt">데이터길이</label></th>
	                            <td><input type="text" name="dataLt" id="dataLt"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th><label for="dcmlpointLt">소수점길이</label></th>
	                            <td><input type="text" name="dcmlpointLt" id="dcmlpointLt"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th><label for="exprsnFom">표현형식</label></th>
	                            <td><input type="text" name="exprsnFom" id="exprsnFom"  value="" style="width:100%"></td>
	                        </tr>
	                        <tr>
	                            <th><label for="unit">단위</label></th>
	                            <td><input type="text" name="unit" id="unit"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th><label for="permValDc">허용값설명</label></th>
	                            <td><input type="text" name="permValDc" id="permValDc"  value="" style="width:100%"></td>
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
	                    <!-- <div class="btn_user_form_wrap">
	                        <input class="btn_user_modify" type="button" value="저장" >
	                        <input class="btn_user_delete" type="button" value="삭제" >									
							<input class="btn_user_submit" type="button" value="취소" onClick="history.back();">
	                    </div> -->
	                
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