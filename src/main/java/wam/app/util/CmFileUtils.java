package wam.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import wam.app.w.file.TCmFile;



@Component("cmFileUtils")
public class CmFileUtils {
	@Value("${Globals.uploadPath}")
	private static String uploadPath;

    @Value("${Globals.uploadPath}")
    private void setUploadPath(String uploadPath){
    	CmFileUtils.uploadPath = uploadPath;
    }
	
	public static String getUploadPath() {
		return uploadPath;
	}
	
	/**
	 * 파일 정보를 디렉토리(uploadPath + path)에 저장.
	 * @param fileSn
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static List<TCmFile> saveMultiFile(String tableNm, String tableId, HttpServletRequest request,String path) throws Exception{
		MultipartHttpServletRequest mtfRequest = (MultipartHttpServletRequest)request;
    	List<MultipartFile> fileList = mtfRequest.getFiles("files[]");

    	String uploadFileNm = null;
    	String uploadFileExtension = null;
    	String saveFileNm = null;
    	String savePath = uploadPath + path;
    	
        TCmFile fileVO = null;
 		List<TCmFile> fileVOList = new ArrayList<TCmFile>();
 		int seqNum = 0;
        File file = new File(savePath);
        if(file.exists() == false){
        	file.mkdirs();
        }
        for (MultipartFile mf : fileList) {
            uploadFileNm = mf.getOriginalFilename();// 원본 파일 명
    		uploadFileExtension = uploadFileNm.substring(uploadFileNm.lastIndexOf("."));
    		saveFileNm = getRandomString() + uploadFileExtension;
    		long fileSize = mf.getSize(); // 파일 사이즈

            String safeFile = savePath + File.separator + saveFileNm;
            try {
                mf.transferTo(new File(safeFile));
                fileVO = new TCmFile();
     			//fileVO.setFileSn(fileSn);
     			fileVO.setTableNm(tableNm);
     			fileVO.setTableId(tableId);
     			fileVO.setFileTy(uploadFileExtension.substring(1));
     			fileVO.setFilePath(path); // 상대경로
     			fileVO.setUploadFileNm(uploadFileNm);
     			fileVO.setSaveFileNm(saveFileNm);
     			fileVO.setFileSize(fileSize);
     			fileVO.setUseYn("Y");
     			fileVO.setRegistId("admin");
     			fileVOList.add(fileVO);
     			seqNum++;
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
     		
		return fileVOList;
	}

    /**
     * 파일 다운로드
     * @param request
     * @param response
     * @throws IOException
     */
    public static void download(String fileName,String fileNameOrg,String filePath
    		,HttpServletRequest request,HttpServletResponse response) throws IOException{
    	FileInputStream fileInputStream = null;
  	    ServletOutputStream servletOutputStream = null;
		try{
	  	    File file = new File(uploadPath + filePath,fileName);
	  	    if (!file.exists()) {
	  	    	System.out.println("file does not exist!!");
	  	    	return;
	  	    }
	    	String downName = null;
	        String browser = request.getHeader("User-Agent");
	        //파일 인코딩
	        if(browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")){//브라우저 확인 파일명 encode  
	            downName = URLEncoder.encode(fileNameOrg,"UTF-8").replaceAll("\\+", "%20");
	        }else{
	            downName = new String(fileNameOrg.getBytes("UTF-8"), "ISO-8859-1");
	        }
	        response.setHeader("Content-Disposition","attachment;filename=\"" + downName+"\"");             
	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-Transfer-Encoding", "binary;");
	        
			
	        fileInputStream = new FileInputStream(file);
	        servletOutputStream = response.getOutputStream();
	 
	        byte b [] = new byte[1024];
	        int data = 0;
	 
	        while((data=(fileInputStream.read(b, 0, b.length))) != -1){
	            servletOutputStream.write(b, 0, data);
	        }
	        servletOutputStream.flush();//출력
	    }catch (Exception e) {
	        e.printStackTrace();
	    }finally{
	        if(servletOutputStream!=null){
	            try{
	                servletOutputStream.close();
	            }catch (IOException e){
	                e.printStackTrace();
	            }
	        }
	        if(fileInputStream!=null){
	            try{
	                fileInputStream.close();
	            }catch (IOException e){
	                e.printStackTrace();
	            }
	        }
	    }
    }

	/**
	 * FileVO의 정보로 해당 파일을 삭제.
	 * @param fileVOList 삭제 {@link TCmFile} 리스트 정보.
	 * @throws Exception
	 */
	public static void deleteFiles(List<TCmFile> fileVOList) throws Exception {
		File targetFile = null;

		for(TCmFile fileVO : fileVOList) {
			targetFile = new File( uploadPath + fileVO.getFilePath(), fileVO.getUploadFileNm());
			
			if(!targetFile.exists()) continue;
			
			targetFile.delete();
		}
	}
	/**
	 * 경로 정보로 해당 파일을 삭제.
	 * @param filePath 파일중간경로, saveFileNm 저장파일명
	 * @throws Exception
	 */
	public static void deleteFileOne(String filePath, String saveFileNm) throws Exception {
		
		if (filePath == null || filePath.equals("")) return;
		if (saveFileNm == null || saveFileNm.equals("")) return;
		
		File targetFile = null;
		targetFile = new File( uploadPath + filePath, saveFileNm);
		if(!targetFile.exists()) return;
		targetFile.delete();
	}


	/**
	 * String 형식의 랜덤 숫자를 리턴한다.
	 *
	 * @return String 형식의 랜덤 숫자를 리턴한다.
	 * @see
	 */
	public static String getRandomString(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
    //public static String getExcelPath() {
	//	return uploadPath + "excel" + File.separator;
	//}
	//
    //
	//public static String createPrimaryKey() throws Exception{
	//	String pk = "CFM" + "_" + StringUtil.getTimeStamp();
	//	return pk;
	//}
	///**
	// * 파일 정보를 한줄씩 리스트에 저장하여 리턴.
	// * @param name
	// * @return
	// * @throws IOException
	// */
	//public static List<String> lineRead(File f) throws IOException{
	//	List<String> retStr = new ArrayList<String>();
	//	BufferedReader buffReader = new BufferedReader(new InputStreamReader(new FileInputStream(f), "euc-kr"));
	//	String s;
	//	while ((s = buffReader.readLine()) != null) {
	//		retStr.add(s);
	//	}
	//	buffReader.close();
	//	return retStr;  
	//}
	//
	///**
	// * 파일 정보를 저장.
	// * @param fileSn
	// * @param request
	// * @return
	// * @throws Exception
	// */
	//public static List<TCmFile> saveFile(String fileSn,HttpServletRequest request,String path) throws Exception{
	//	List<TCmFile> fileVOList = new ArrayList<TCmFile>();
	//	FileOutputStream output = null;
	//	try {
	//		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
	//    	Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
	//    	
	//    	MultipartFile multipartFile = null;
	//    	String uploadFileNm = null;
	//    	String uploadFileExtension = null;
	//    	String saveFileNm = null;
	//    	String savePath = uploadPath;
	//    	
	//    	if(path!=null&&!path.equals("")){
	//    		savePath = path;
	//    	}
	//        TCmFile fileVO = null;
	// 		
	// 		int seqNum = 0;
	//        File file = new File(savePath);
	//        if(file.exists() == false){
	//        	file.mkdirs();
	//        }
	//        while(iterator.hasNext()){
	//        	multipartFile = multipartHttpServletRequest.getFile(iterator.next());
	//        	if(multipartFile.isEmpty() == false){
	//        		uploadFileNm = multipartFile.getOriginalFilename();
	//        		uploadFileExtension = uploadFileNm.substring(uploadFileNm.lastIndexOf("."));
	//        		saveFileNm = StringUtil.getRandomString() + uploadFileExtension;
	//        				
	//        		//file = new File(uploadPath + saveFileNm);
	//        		//multipartFile.transferTo(file);
	//        		byte[] fileData = multipartFile.getBytes();
	//				output = new FileOutputStream(savePath + saveFileNm);
	//				output.write(fileData);
	//     			
	//     			fileVO = new TCmFile();
	//     			fileVO.setFileSn(fileSn);
	//     			fileVO.setTableSn(seqNum+"");
	//     			fileVO.setFilePath(savePath);
	//     			fileVO.setUploadFileNm(saveFileNm);
	//     			fileVO.setSaveFileNm(uploadFileNm);
	//     			fileVO.setFileSize(multipartFile.getSize()+"");
	//     			fileVO.setUseYn("Y");
	//     			fileVO.setRegistId("admin");
	//     			fileVOList.add(fileVO);
	//     			seqNum++;
	//        	}
	//        }
	//	}catch (Exception e) {
	//		e.printStackTrace();
	//	}finally {
	//		output.close();
	//	}
	//	return fileVOList;
	//}
	//
	//
	///**
	// * 파일 정보를 저장.
	// * @param fileSn
	// * @param request
	// * @return
	// * @throws Exception
	// */
	//public static List<TCmFile> saveFile(String fileSn,String uploadFileNm,File file) throws Exception{
	//	List<TCmFile> fileVOList = new ArrayList<TCmFile>();
    //	String uploadFileExtension = null;
    //	String saveFileNm = null;
    //	String savePath = uploadPath;
    //    TCmFile fileVO = null;
    //    FileOutputStream output = null;
	//	try {
    // 		int seqNum = 0;
    //        if(file.exists() == true){
    //    		uploadFileExtension = uploadFileNm.substring(uploadFileNm.lastIndexOf("."));
    //    		saveFileNm = StringUtil.getRandomString() + uploadFileExtension;
    //    				
    //    		byte[] fileData = Files.readAllBytes(file.toPath());
    //			output = new FileOutputStream(savePath + saveFileNm);
    //			output.write(fileData);
    // 			
    // 			fileVO = new TCmFile();
    // 			fileVO.setFileSn(fileSn);
    // 			fileVO.setTableSn(seqNum+"");
    // 			fileVO.setFilePath(savePath);
    // 			fileVO.setUploadFileNm(saveFileNm);
    // 			fileVO.setSaveFileNm(uploadFileNm);
    // 			fileVO.setFileSize(file.length()+"");
    // 			fileVO.setUseYn("Y");
    // 			fileVO.setRegistId("admin");
    // 			fileVOList.add(fileVO);
    //        }
	//	} catch (Exception e) {
	//		e.printStackTrace();
	//	}finally {
	//		output.close();
	//	}
	//	return fileVOList;
	//}
	

	///**
	// * 파일을 삭제.
	// * @param targetFile
	// * @throws Exception
	// */
	//public static void deleteFile(File targetFile) throws Exception {
	//	if(targetFile.exists()) {targetFile.delete();};
	//}
	//
	///**
	// * 파일 정보를 맵 리스트로 반환.
	// * @param map
	// * @param request
	// * @return
	// * @throws Exception
	// */
	//public static List<Map<String, Object>> parseUpdateFileInfo(Map<String, Object> map, HttpServletRequest request) throws Exception{
	//	MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
    //	Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
    //	
    //	MultipartFile multipartFile = null;
    //	String originalFileName = null;
    //	String originalFileExtension = null;
    //	String storedFileName = null;
    //	
    //	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    //    Map<String, Object> listMap = null; 
    //    
    //    String boardIdx = (String)map.get("IDX");
    //    String requestName = null;
    //    String idx = null;
    //    
    //    
    //    while(iterator.hasNext()){
    //    	multipartFile = multipartHttpServletRequest.getFile(iterator.next());
    //    	if(multipartFile.isEmpty() == false){
    //    		originalFileName = multipartFile.getOriginalFilename();
    //    		originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
    //    		storedFileName = StringUtil.getRandomString() + originalFileExtension;
    //    		
    //    		//multipartFile.transferTo(new File(filePath + storedFileName));
    //    		
    //    		listMap = new HashMap<String,Object>();
    //    		listMap.put("IS_NEW", "Y");
    //    		listMap.put("BOARD_IDX", boardIdx);
    //    		listMap.put("ORIGINAL_FILE_NAME", originalFileName);
    //    		listMap.put("STORED_FILE_NAME", storedFileName);
    //    		listMap.put("FILE_SIZE", multipartFile.getSize());
    //    		list.add(listMap);
    //    	}
    //    	else{
    //    		requestName = multipartFile.getName();
    //        	idx = "IDX_"+requestName.substring(requestName.indexOf("_")+1);
    //        	if(map.containsKey(idx) == true && map.get(idx) != null){
    //        		listMap = new HashMap<String,Object>();
    //        		listMap.put("IS_NEW", "N");
    //        		listMap.put("FILE_IDX", map.get(idx));
    //        		list.add(listMap);
    //        	}
    //    	}
    //    }
	//	return list;
	//}
	//
    ///**
    // * 파일 복사본을 생성한다.
    // * @param orgFile
    // * @param backup
    // */
    //public static void copy(File orgFile, File backup) {
	//	BufferedReader buffReader = null;
	//	BufferedWriter bufferWriter = null;
	//	
	//	try{
	//		buffReader = new BufferedReader(new InputStreamReader(new FileInputStream(orgFile), "euc-kr"));
	//		bufferWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(backup),"euc-kr"));
	//		String s = null;
	//		while((s=buffReader.readLine())!=null){
	//			bufferWriter.write(s);
	//			bufferWriter.newLine();
	//		}
	//	}catch(Exception e){
	//		
	//		e.printStackTrace();
	//	
	//	}finally{
	//		if(buffReader != null) try{buffReader.close();}catch(IOException e){}
	//		if(bufferWriter != null) try{bufferWriter.close();}catch(IOException e){}
	//	}
    //
    //}
    //
    //
	//
	///**
	// * 문자열을 파일로 생성.
	// * @param file
	// * @param text
	// * @param charset
	// * @throws IOException
	// */
	//public static File writeToFile(File file, String text, String charset) throws IOException {
    //
	//    if (file == null) {
	//        throw new IllegalArgumentException("Illegal argument, file cannot be null.");
	//    }
	//    if (text == null) {
	//        throw new IllegalArgumentException("Illegal argument, text cannot be null.");
	//    }
	//    try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getPath()), charset))) {
	//        writer.write(text);
	//    }
	//    return file;
	//}
    //
    ///**
    // * 파일 디코딩.
    // * @param file
    // * @param encoding
    // * @throws IOException
    // */
    //public static void decodingFile(File file, String encoding) throws IOException {
    //	if(encoding==null) {encoding = "UTF-8";}
    //	Charset charset = Charset.forName(encoding);
    //	FileInputStream fis = new FileInputStream(file);
    //	ByteArrayOutputStream fbs = new ByteArrayOutputStream();
    //	
    //	byte[] buffer = new byte[4096];
    //	int n = 0;
    //	while((n = fis.read(buffer, 0, buffer.length)) > 0) {
    //		fbs.write(buffer, 0, n);
    //	}
    //	CharBuffer charBuffer = charset.decode(ByteBuffer.wrap(fbs.toByteArray()));
    //	BufferedWriter bw = new BufferedWriter(new FileWriter(file));
    //	bw.append(charBuffer);
    //	bw.close();
    //	fis.close();
    //}
    //
    ///**
    // * 파일 인코딩 정보 읽기.
    // * @param file
    // * @return
    // * @throws IOException
    // */
    //public static String readEncoding(File file) throws IOException {
    //	byte[] buf = new byte[4096];
    //	java.io.FileInputStream fis = new java.io.FileInputStream(file);
    //	UniversalDetector detector = new UniversalDetector(null);
    //	int nread;
    //	while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
    //		detector.handleData(buf, 0, nread);
    //	}
    //	detector.dataEnd();
    //	String encoding = detector.getDetectedCharset();
    //	detector.reset();
    //	buf = null;
    //	fis.close();
    //	return encoding == null?"UTF-8":encoding;
    //}

}
