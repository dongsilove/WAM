<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>마인드원</title>
    <link rel="shortcut icon" href="/images/favicon_mindone.ico">
    <link href="/asset/fontawesome/css/fontawesome-all.css" rel="stylesheet">
    <script src="/js/lib/jquery/jquery-3.4.1.min.js"></script>
    <script src="/js/util/init2.js"></script>

    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/template.css">
    <link rel="stylesheet" type="text/css" href="/js/lib/tui-pagination/dist/tui-pagination.css" />
    <link rel="stylesheet" type="text/css" href="/js/lib/jquery/jquery-ui-1.12.1.css" /><!-- datepicker, 움직이는 div 사용을 위해 추가 -->
    
	<script src="/js/lib/jquery/jquery-ui-1.12.1.js"></script>
	<script src="/js/lib/jquery/jquery.serializeObject.min.js"></script><!-- form.serializeObject()를 위해 -->
	<script src="/js/lib/jquery/jquery.validate.min.js"></script>
	<script src="/js/lib/tui-code-snippet/dist/tui-code-snippet.js"></script>
	<script src="/js/lib/tui-pagination/dist/tui-pagination.js"></script><!-- tui-code-snippet.js 필요 -->
	<script src="/js/lib/jquery/moment.js"></script>
	<script src="/js/lib/jquery/jquery.form-3.51.0.js"></script><!-- 첨부파일 업로드: $(form).ajaxForm()  -->
	<!-- <script src="/js/lib/jquery/jquery-fileDownload.js"></script> --><!-- 첨부파일 다운로드 : $.fileDownload() -->

    <script src="/js/util/validations.js"></script>
    <script src="/js/util/localStorage.js"></script>
    <script src="/js/util/paging.js"></script>
    <script src="/js/util/commUtils.js"></script>
    <script src="/js/util/ajaxUtils.js"></script>
    <script src="/js/util/datepickerUtils.js"></script>
    <script src="/js/util/cmFile.js"></script><!-- 첨부파일 -->

<script>
	var loginId = '${loginInfo.userId}';
	var loginDeptNm = '${loginDeptNm}';
	var authorCn = JSON.parse('${loginInfo.TAuUserGrp.authorCn}');
</script>
</head>
