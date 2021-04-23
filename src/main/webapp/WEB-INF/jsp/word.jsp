<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
//========================================
// @brief 데이터사전 > 단어 > 목록
//========================================
// @history 
//  	2021.02.09 박이정(마인드원) 최초작성
//========================================
%> 
<!DOCTYPE html>
<html lang="ko">
<jsp:include page="/WEB-INF/jsp/layout/header.jsp"/>
<script src="/js/word.js"></script>

<body>
    <div class="bg"></div>
    <div class="wrap">
        <jsp:include page="/WEB-INF/jsp/layout/top.jsp"/>
        <jsp:include page="/WEB-INF/jsp/layout/nav.jsp"/>

        <div class="container">
            <div class="title">
            	<div style="float:left;"><h1>단어</h1></div>
            	<div class="location"><ul><li class="home"></li><li>데이터사전</li><li>단어</li></ul></div>
            </div>
            <div class="cont_divide">
            	<div class="left">
		            <div class="write"><!-- 검색 -->
		                <div class="write_inner">
							<div class="write_search">
							<form name="searchForm" id="searchForm" method="GET">
								<input type="hidden" id="page" name="page">
								<input type="hidden" id="searchtmp" name="" value=""  />
								<span> 주제 구분 : 
									<select name="themaSe" id="themaSe" onChange="_list.getList(1);" class="themaSe">
									</select>
								</span>
								<select name="searchName" id="searchName"   >
										<!-- <option value=''> -- 검색선택 -- </option> -->
										<option value="wordNm" >단어명</option>
										<option value="wordEnAbbr" >단어영문약어</option>
										<option value="wordEnNm" >단어영문명</option>
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
		                        <col style="width:15%">
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
		                            <th scope="col">단어명</th>
		                            <th scope="col">단어영문약어</th>
		                            <th scope="col">단어영문명</th>
		                            <th scope="col">동의어</th>
		                            <th scope="col">금지 단어</th>
		                            <th scope="col">주제 구분</th>
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
	                            <th><label for="wordSn">단어 일련번호</label></th>
	                            <td><input type="text" name="wordSn" id="wordSn" value="" readonly ></td>
	                        </tr>
	                        <tr title="일부단어 입력 후 행정단어 검색 가능">
	                            <th class="required"><label for="wordNm">단어명</label></th>
	                            <td><span class="tddc">* 입력시 행정단어명으로 자동검색</span><br>
	                                <input type="text" name="wordNm" id="wordNm"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="wordEnAbbr">단어영문약어</label></th>
	                            <td><input type="text" name="wordEnAbbr" id="wordEnAbbr"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="wordEnNm">단어영문명</label></th>
	                            <td><input type="text" name="wordEnNm" id="wordEnNm"  value="" style="width:100%"></td>
	                        </tr>
	                        <tr>
	                            <th><label for="wordDc">단어설명</label></th>
	                            <td><textarea name="wordDc" id="wordDc"  value="" style="width:100%;"></textarea>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="synonm">동의어</label></th>
	                            <td><input type="text" name="synonm" id="synonm"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th><label for="prhibtWordNm">금지 단어</label></th>
	                            <td><input type="text" name="prhibtWordNm" id="prhibtWordNm"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th><label for="themaSe">주제 구분</label></th>
	                            <td>
	                                <select name="themaSe" id="themaSe" required  class="themaSe"></select>
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
	                    <div id="nmList"> </div>
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