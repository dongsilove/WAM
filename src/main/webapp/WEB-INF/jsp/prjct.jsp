<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
//========================================
// @brief 데이터사전 > 프로젝트 > 목록
//========================================
// @history 
//  	2021.02.09 박이정(마인드원) 최초작성
//========================================
%>
<!DOCTYPE html>
<html lang="ko">
<jsp:include page="/WEB-INF/jsp/layout/header.jsp"/>
<script src="/js/prjct.js"></script>

<body>
    <div class="bg"></div>
    <div class="wrap">
        <jsp:include page="/WEB-INF/jsp/layout/top.jsp"/>
        <jsp:include page="/WEB-INF/jsp/layout/nav.jsp"/>

        <div class="container">
            <div class="title">
            	<div style="float:left;"><h1>프로젝트</h1></div>
            	<div class="location"><ul><li class="home"></li><li>데이터사전</li><li>프로젝트</li></ul></div>
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
										<option value="prjctNm" >프로젝트명</option>
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
		                        <col style="width:18%">
		                        <col style="width:auto">
		                        <col style="width:20%">
		                        <col style="width:20%">
		                    </colgroup>
		                    <thead>
		                        <tr>
		                            <th scope="col">번호</th>
		                            <th scope="col">프로젝트명</th>
		                            <th scope="col">프로젝트설명</th>
		                            <th scope="col">프로젝트 시작 일</th>
		                            <th scope="col">프로젝트 종료 일</th>
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
	                            <th><label for="prjctSn">프로젝트 일련번호</label></th>
	                            <td><input type="text" name="prjctSn" id="prjctSn" value="" readonly ></td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="prjctNm">프로젝트명</label></th>
	                            <td><input type="text" name="prjctNm" id="prjctNm"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th><label for="prjctDc">프로젝트설명</label></th>
	                            <td><textarea name="prjctDc" id="prjctDc"  value="" style="width:100%;"></textarea>
	                        </tr>
	                        <tr>
	                            <th><label for="prjctBeginYmd">프로젝트 시작 일</label></th>
	                            <td>
	                            	<span class="date_input">
	                            	<input class="datepicker input_calendar" type="text" name="prjctBeginYmd">
	                            	</span>
	                            </td>
	                        </tr>
	                        <tr>
	                            <th><label for="prjctEndYmd">프로젝트 종료 일</label></th>
	                            <td>
	                            	<span class="date_input">
	                            	<input class="datepicker input_calendar" type="text" name="prjctEndYmd">
	                            	</span>
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
	                            <th><label for="registId">수정아이디</label></th>
	                            <td><input type="text" name="registId" id="registId"  value="" readonly></td>
	                        </tr>
	                        <tr>
	                            <th><label for="registDt">수정일시</label></th>
	                            <td><input type="text" name="registDt" id="registDt"  value="" readonly></td>
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

</html>