/**
 * @FileName 	ajaxUtil.js
 * @author 		ljpark
 * @Date 		2021.02.07
 * @Description ajax 호출
 */

var _ajaxUtils = {
	ajax : function(addOptions) {

		var _defaultOptions = {
			 "method" : "GET"
			, "url": ""
			, "form" : null
			, "successCallback": null // 성공시 처리함수
			, async : true
		};
		
		// 옵션확장
		var options = $.extend( {}, _defaultOptions, addOptions );
		var pars = {};
		console.log(options);
		
		if (options.form != null) {
			pars = options.form.serializeObject();
			processYmd(pars); // '-'제거, 공백제거(commUtils.js)
			if (options.method.toUpperCase() != "GET") {
				pars = JSON.stringify(pars);
			} else {
				pars["perPage"] =  _paging.paginationOptions.itemsPerPage;
			}
			console.log(pars);
		}
		
		$.ajax({
			url: options.url
			, type: options.method
			, data: pars
			, dataType: "JSON"
			, contentType: 'application/json;charset=UTF-8'
			, async : options.async
			, success: function(data) {
				options.successCallback(data);
			}
			, error: function( jqXHR, textStatus ) {
				if (jqXHR.status == 401) {
					window.location.href = "/login/page"; 
				} else if (jqXHR.status == 500 && jqXHR.responseText.indexOf("ConstraintViolationException")>-1) {
					console.log(jqXHR.responseText);
					alert("중복된 값(영문약어,단어명or용어명..)이 있습니다. 검색 후 등록하세요.");
				} else {
					alert("시스템 오류입니다. 잠시 후 다시 접속하시기 바랍니다. [ message: " + jqXHR.responseText + "\error:" + textStatus+ "]");
				}
			}
		});
	}

}