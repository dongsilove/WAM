<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
//========================================
// @brief 권한
//========================================
// @history 
//  	2021.02.09 박이정(마인드원) 최초작성
//========================================
%>
<!DOCTYPE html>
<html lang="ko">
<jsp:include page="/WEB-INF/jsp/layout/header.jsp"/>
<script src="/js/author.js"></script>

<body>
    <div class="bg"></div>
    <div class="wrap">
        <jsp:include page="/WEB-INF/jsp/layout/top.jsp"/>
        <jsp:include page="/WEB-INF/jsp/layout/nav.jsp"/>

        <div class="container">
            <div class="title">
            	<div style="float:left;"><h1>부서</h1></div>
            	<div class="location"><ul><li class="home"></li><li>사용자</li><li>부서정보</li></ul></div>
            </div>
            <div class="cont_divide">
		            <div class="write"><!-- 검색 -->
		                <div class="write_inner">
							<div class="write_search">
							<form name="searchForm" id="searchForm" method="GET">
								<input type="hidden" id="page" name="page">
								<input type="hidden" id="searchtmp" name="" value=""  />
								
								<span> 사용자그룹 : 
									<select name="usergrpNm" id="usergrpNm" onChange="_list.getList(1);" class="usergrpNm">
										   <option value=''> </option>
									</select>
								</span>
								
								<select name="searchName" id="searchName"   >
										<!-- <option value=''> -- 검색선택 -- </option> -->
										<option value="deptNm" >부서명</option>
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
				                <col style="width:15%">
				                <col style="width:10%">
				                <col style="width:auto">
				            </colgroup>
				            <thead>
				                <tr>
				                    <th scope="col" rowspan="2">메뉴코드</th>
				                    <th scope="col" rowspan="2">메뉴명</th>
				                    <th scope="col" rowspan="2">상위메뉴코드</th>
				                    <th scope="col" colspan="6">권한설정</th>
				                </tr>
				                <tr>
				                    <th scope="col"><input type="checkbox" id="Mall" onclick="checkAll('M')"><label> 메뉴사용</label></th>
				                    <th scope="col"><input type="checkbox" id="Call" onclick="checkAll('C')"><label> 등록</label></th>
				                    <th scope="col"><input type="checkbox" id="Uall"  onclick="checkAll('U')"><label> 수정</label></th>
				                    <th scope="col"><input type="checkbox" id="Dall"  onclick="checkAll('D')"><label> 삭제</label></th>
				                    <th scope="col"><input type="checkbox" id="Rall"  onclick="checkAll('R')"><label> 조회</label></th>
				                    <th scope="col"><input type="checkbox" id="Eall"  onclick="checkAll('E')"><label> 엑셀다운</label></th>
				                </tr>
				            </thead>
				            <tbody id="listData">
								<tr><td colspan=9>조건을 선택하시고 검색 버튼을 누르세요.</td></tr>
				            </tbody>
				        </table>
		                </div>
		            </div>            	

            </div>

        </div><!--//container-->
    </div>
</body>
<script>

</script>
</html>