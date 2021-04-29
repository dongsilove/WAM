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
var gPage = ('${page}')? '${page}'*1 : 1;

var gMenuId = 'AM_ASST_CW';
var asstUrl = 'asstcws';
var splsysLocplcCdGrp = 'CWLOC'; // 공급계통소재지 지역별 그룹코드
</script>
<script src="/js/asstcmmn.js"></script>

<body>
    <div class="bg"></div>
    <div class="wrap">
        <jsp:include page="/WEB-INF/jsp/layout/top.jsp"/>
        <jsp:include page="/WEB-INF/jsp/layout/nav.jsp"/>

        <jsp:include page="/WEB-INF/jsp/asstcmmn_list.jsp"/>
    </div>
</body>
<script>

</script>
</html>