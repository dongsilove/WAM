/**
 * @FileName 	cmFile.js
 * @author 		ljpark
 * @Date 		2021.03.04
 * @Description 파일 관리
 */

var _cmFile = {
	list : function(_tableNm,_tableId){ 
		var dfd = $.Deferred();
		//var param = {tableNm : _tableNm, tableId:_tableId};
		$.ajax({
	    	url: "/api/cmfiles/" + _tableNm + "/" + _tableId, 
	    	type : "GET",
	    	//data: param,
	    	dataType : "json",
	    	contentType : "application/json;charset=UTF-8",
	    	success: function (data) {
	    		//console.log(data);
	    		if(!data.content) { 
	    			return dfd.reject();
	    		} else {
	    			return dfd.resolve(data.content);
	    		}
	        }
			,error : function(request, error) {
				return dfd.reject(new Error("message: " + request.responseText + ", error:" + error));
			}
		});
		return dfd.promise();
	},
	getCmFile : function(_tableNm,_tableId){ 
		var dfd = $.Deferred();
		var param = {tableNm : _tableNm, tableId:_tableId};
		$.ajax({
	    	url: "/api/cmfiles/"+_tableNm + "/" + _tableId, 
	    	type : "GET",
	    	//data: param,
	    	dataType : "json",
	    	contentType : "application/json;charset=UTF-8",
	    	success: function (data) {
	    		//console.log(data);
	    		if(!result.data) { 
	    			return dfd.reject();
	    		} else {
	    			return dfd.resolve(data);
	    		}
	        }
			,error : function(request, error) {
				return dfd.reject(new Error("message: " + request.responseText + ", error:" + error));
			}
		});
		return dfd.promise();
	},
	fileDownload : function (filePath,uploadFileNm,saveFileNm) {
		if(filePath!=null&&uploadFileNm!=null&&saveFileNm!=null){
			location.href = "/api/cmfiles/fileDownload?filePath="+filePath+"&uploadFileNm="+uploadFileNm+"&saveFileNm="+saveFileNm;
		}
	},
	//개별 다운로드 
	fileDownloadOne : function(_url) {
		$.fileDownload( _url,{
			httpMethod: "GET",
			successCallback: function (url) {
			},
			failCallback: function(responseHtml, url) {
				alert('파일 다운로드 실패!\n관리자에게 문의 주세요.');
			}
		});
	},
	delete : function(_tableNm,_tableId){ 
		var dfd = $.Deferred();
		var param = {tableNm : _tableNm, tableId:_tableId};
		$.ajax({
	    	url: "/api/cmfiles/delete", 
	    	type : "DELETE",
	    	data: JSON.stringify(param),
	    	dataType : "json",
	    	contentType : "application/json;charset=UTF-8",
	    	success: function (result) {
	    		//console.log(result);
	    		if(result.msg=='실패하였습니다.') { 
	    			return dfd.reject();
	    		} else {
	    			return dfd.resolve(result.msg);
	    		}
	        }
			,error : function(request, error) {
				return dfd.reject(new Error("message: " + request.responseText + ", error:" + error));
			}
		});
		return dfd.promise();
	},
	deleteOne : function(_fileSn,_filePath,_saveFileNm){ 
		var dfd = $.Deferred();
		var param = {fileSn : _fileSn, filePath : _filePath, saveFileNm : _saveFileNm};
		console.log(param);
		$.ajax({
	    	//url: "/api/cmfiles/"+_fileSn, 
	    	url: "/api/cmfiles", 
	    	type : "DELETE",
	    	data: JSON.stringify(param),
	    	dataType : "json",
	    	contentType : "application/json;charset=UTF-8",
	    	success: function (data) {
	    		console.log(data);
	    		if(data!='200') { 
					let rsltArr = data.split('|');
					alert(rsltArr(1));
	    			return dfd.reject();
	    		} else {
	    			return dfd.resolve("삭제하였습니다.");
	    		}
	        }
			,error : function(request, error) {
				return dfd.reject(new Error("message: " + request.responseText + ", error:" + error));
			}
		});
		return dfd.promise();
	}
	
}



function fileUploadForm(){
	$("#fileDiv").empty();
	$("#fileDiv").append('<input type="file" name="files[]" id="file_input" multiple="multiple" data-jfiler-limit="3"/>' );
	$('#file_input').filer({
		showThumbs: true,
		addMore: false,
		allowDuplicates: false,
		captions: {
            button: "파일 선택",
            feedback: "",
            feedback2: "개의 파일이 선택되었습니다.",
            drop: "Drop file here to Upload",
            removeConfirmation: "삭제하시겠습니까?",
            errors: {
                filesLimit: "{{fi-limit}}개 파일까지 업로드 가능합니다.",
                filesType: "Only Images are allowed to be uploaded.",
                filesSize: "{{fi-name}} is too large! Please upload file up to {{fi-fileMaxSize}} MB.",
                filesSizeAll: "Files you've choosed are too large! Please upload files up to {{fi-maxSize}} MB.",
                folderUpload: "You are not allowed to upload folders."
            }
        }
	});
}

function fileDownloadForm(file) {
	var layout = "";
	layout +='<a class="pop_btn" onclick="fileDownload(\''+file.filePath+'\',\''
		+file.uploadFileNm+'\',\''+file.saveFileNm+'\')">'+file.saveFileNm+'</a>';
	return layout;
}



function getfileSize(x) {
	if(x==0){
		return x;
	}
	var s = ['bytes', 'kB', 'MB', 'GB', 'TB', 'PB'];
	var e = Math.floor(Math.log(x) / Math.log(1024));
	return (x / Math.pow(1024, e)).toFixed(2) + " " + s[e];
};