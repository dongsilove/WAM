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
            <div class="cont_divide">
            	<div class="left">
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
		                        <col style="width:15%">
		                        <col style="width:15%">
		                        <col style="width:auto">
		                        <col style="width:12%">
		                        <col style="width:10%">
		                        <col style="width:10%">
		                        <col style="width:10%">
		                        <col style="width:10%">
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
	                            <th><label for="asstSn">자산일련번호</label></th>
	                            <td><input type="text" name="asstSn" id="asstSn" value="" readonly ></td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="asstNm">자산명</label></th>
	                            <td><input type="text" name="asstNm" id="asstNm"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="asstAccntNov">자산회계번호</label></th>
	                            <td><input type="text" name="asstAccntNov" id="asstAccntNov"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="asstAccntSclasNm">자산회계소분류명</label></th>
	                            <td><input type="text" name="asstAccntSclasNm" id="asstAccntSclasNm"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="locplcNm">소재지명</label></th>
	                            <td><input type="text" name="locplcNm" id="locplcNm"  value=""  style="width:100%;"></td>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="psitnNm">소속명</label></th>
	                            <td><input type="text" name="psitnNm" id="psitnNm"  value=""  style="width:100%;"></td>
	                        </tr>
	                        <tr>
	                            <th><label for="useYn">사용여부(불용여부)</label></th>
	                            <td><textarea name="useYn" id="useYn"  value="" ></textarea>
	                        </tr>
	                        <tr>
	                            <th class="required"><label for="frstAcqsYmd">최초취득일</label></th>
	                            <td><input type="text" name="frstAcqsYmd" id="frstAcqsYmd"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th><label for="frstAcqsPc">최초취득가</label></th>
	                            <td><input type="text" name="frstAcqsPc" id="frstAcqsPc"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th><label for="revalYmd">재평가일</label></th>
	                            <td><input type="text" name="revalYmd" id="revalYmd"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th><label for="revalAm">재평가액</label></th>
	                            <td><input type="text" name="revalAm" id="revalAm"  value="" style="width:100%"></td>
	                        </tr>
	                        <tr>
	                            <th><label for="nowAcqsPc">현재취득가</label></th>
	                            <td><input type="text" name="nowAcqsPc" id="nowAcqsPc"  value="" ></td>
	                        </tr>
	                        <tr>
	                            <th><label for="nowUslfsvcCo">현재내용년수</label></th>
	                            <td><input type="text" name="nowUslfsvcCo" id="nowUslfsvcCo"  value="" style="width:100%"></td>
	                        </tr>
	                        <tr>
	                            <th><label for="nowRsvmneySm">현재충당금계</label></th>
	                            <td><input type="text" name="nowRsvmneySm" id="nowRsvmneySm"  value="" style="width:100%"></td>
	                        </tr>
	                        <tr>
	                            <th><label for="nowAcntbkAm">현재장부액</label></th>
	                            <td><input type="text" name="nowAcntbkAm" id="nowAcntbkAm"  value="" style="width:100%"></td>
	                        </tr>
	                        <tr>
	                            <th><label for="yrDprtAm">연상각액</label></th>
	                            <td><input type="text" name="yrDprtAm" id="yrDprtAm"  value="" style="width:100%"></td>
	                        </tr>
	                        <tr>
	                            <th><label for="co">수량</label></th>
	                            <td><input type="text" name="co" id="co"  value="" style="width:100%"></td>
	                        </tr>
	                        <tr>
	                            <th><label for="fomNm">형식명</label></th>
	                            <td><input type="text" name="fomNm" id="fomNm"  value="" style="width:100%"></td>
	                        </tr>
	                        <tr>
	                            <th><label for="stndrdNm">규격명</label></th>
	                            <td><input type="text" name="stndrdNm" id="stndrdNm"  value="" style="width:100%"></td>
	                        </tr>
	                        <tr>
	                            <th><label for="installYmd">설치일</label></th>
	                            <td><input type="text" name="installYmd" id="installYmd"  value="" style="width:100%"></td>
	                        </tr>
	                        <tr>
	                            <th><label for="modelNm">모델명</label></th>
	                            <td><input type="text" name="modelNm" id="modelNm"  value="" style="width:100%"></td>
	                        </tr>
	                        <tr>
	                            <th><label for="modelNov">모델번호</label></th>
	                            <td><input type="text" name="modelNov" id="modelNov"  value="" style="width:100%"></td>
	                        </tr>
	                        <tr>
	                            <th><label for="structNm">구조명</label></th>
	                            <td><input type="text" name="structNm" id="structNm"  value="" style="width:100%"></td>
	                        </tr>
	                        <tr>
	                            <th><label for="mtrqltNm">재질명</label></th>
	                            <td><input type="text" name="mtrqltNm" id="mtrqltNm"  value="" style="width:100%"></td>
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