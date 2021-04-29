<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
//========================================
// @brief 데이터사전 > 수원시설 > 등록,수정
//========================================
// @history 
//  	2021.03.02 박이정(마인드원) 최초작성
//========================================
%>
<!DOCTYPE html>
<html lang="ko">
<jsp:include page="/WEB-INF/jsp/layout/header.jsp"/>
<script>
var asstSn = '${asstSn}';
var mode = 'edit';

var gMenuId = 'AM_ASST_FCLTY';
var asstUrl = 'asstfcltys';
var splsysLocplcCdGrp = 'SWLOC';
</script>
<script src="/js/asstcmmn.js"></script>
 
<body>
    <div class="bg"></div>
    <div class="wrap">
        <jsp:include page="/WEB-INF/jsp/layout/top.jsp"/>
        <jsp:include page="/WEB-INF/jsp/layout/nav.jsp"/>
        
        <jsp:include page="/WEB-INF/jsp/asstcmmn_edit.jsp"/>
    </div>
</body>
<script>
$(function() {
	
	//_list.getDetail('${asstSn}');

});
</script>
</html>