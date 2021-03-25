package wam.app.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifIFD0Directory;

import wam.app.w.file.TCmFile;



@Component("cmFileUtils")
public class CmFileUtils {
	@Value("${Globals.uploadPath}")
	private String uploadPath;

    @Value("${Globals.uploadPath}")
    private void setUploadPath(String uploadPath){
    	this.uploadPath = uploadPath;
    }
	
	public String getUploadPath() {
		return this.uploadPath;
	}
	
	/**
	 * 파일 정보를 디렉토리(uploadPath + path)에 저장.
	 * @param fileSn
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<TCmFile> saveMultiFile(String tableNm, String tableId, HttpServletRequest request,String path) throws Exception{
		MultipartHttpServletRequest mtfRequest = (MultipartHttpServletRequest)request;
    	List<MultipartFile> fileList = mtfRequest.getFiles("files[]");

    	List<String> IMAGE_EXTENTIONS = Arrays.asList(".jpeg", ".jpg", ".png", ".gif");
    	String uploadFileNm = null;
    	String uploadFileExtension = null;
    	String saveFileNm = null;
    	String savePath = uploadPath + path;
    	
        TCmFile fileVO = null;
 		List<TCmFile> fileVOList = new ArrayList<TCmFile>();
 		//int seqNum = 0;
        File file = new File(savePath);
        if(file.exists() == false){ // 저장할 디렉토리가 없다면 생성
        	file.mkdirs();
        }
        for (MultipartFile mf : fileList) {
            uploadFileNm = mf.getOriginalFilename();// 원본 파일 명
    		uploadFileExtension = uploadFileNm.substring(uploadFileNm.lastIndexOf(".")); // 확장자
    		uploadFileExtension = uploadFileExtension.toLowerCase();
    		saveFileNm = tableNm+"_"+tableId+"_" + getRandomString() + uploadFileExtension; //getRandomString();
    		long fileSize = mf.getSize(); // 파일 사이즈

            String safeFile = savePath + File.separator + saveFileNm;
            try {
            	if (IMAGE_EXTENTIONS.contains(uploadFileExtension)) { // 이미지이면 크기 줄이기
            		resizeFile(safeFile, mf); // 크기 줄여 저장
            	} else {
            		mf.transferTo(new File(safeFile)); // file 저장
            	}
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
     			//seqNum++;
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
     		
		return fileVOList;
	}

	public void resizeFile(String fileName, MultipartFile mf) {
        int newlength = 1000;	// 기준길이
        String imgFormat = fileName.substring(fileName.indexOf(".")+1);
        Image image;
        int imageWidth;
        int imageHeight;
        double ratio;
        int w = 0;
        int h = 0;
 
        try{
            // 원본 이미지 가져오기
            //image = ImageIO.read(new File(fileName)); // filepath로 Image가져오기
        	byte[] b = mf.getBytes();
        	ByteArrayInputStream bufferedStream = new ByteArrayInputStream(b); // byte[]로 Image가져오기
        	int orientation = getOrientation(bufferedStream);
        	image = ImageIO.read(bufferedStream);
 
            // 원본 이미지 사이즈 가져오기
            imageWidth = image.getWidth(null);
            imageHeight = image.getHeight(null);
 
            if (imageWidth > newlength || imageHeight > newlength) { // 기준길이 보다 길면
            	
	            if(imageWidth > imageHeight){    // 넓이기준
	 
	                ratio = (double)newlength/(double)imageWidth;
	                w = (int)(imageWidth * ratio);
	                h = (int)(imageHeight * ratio);
	 
	            } else if (imageHeight > imageWidth) { // 높이기준
	 
	                ratio = (double)newlength/(double)imageHeight;
	                w = (int)(imageWidth * ratio);
	                h = (int)(imageHeight * ratio);
	 
	            }
	            // 이미지 리사이즈
	            // Image.SCALE_DEFAULT : 기본 이미지 스케일링 알고리즘 사용
	            // Image.SCALE_FAST    : 이미지 부드러움보다 속도 우선
	            // Image.SCALE_REPLICATE : ReplicateScaleFilter 클래스로 구체화 된 이미지 크기 조절 알고리즘
	            // Image.SCALE_SMOOTH  : 속도보다 이미지 부드러움을 우선
	            // Image.SCALE_AREA_AVERAGING  : 평균 알고리즘 사용
	            Image resizeImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
	 
	            // 새 이미지  저장하기
	            BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	            Graphics g = newImage.getGraphics();
	            g.drawImage(resizeImage, 0, 0, null);
	            g.dispose();
	            ImageIO.write(newImage, imgFormat, new File(fileName));
	            
            } else {
            	mf.transferTo(new File(fileName)); // file 저장
            	return;
            }
 
 
        }catch (Exception e){
            e.printStackTrace();
        }		
	}
	
    public int getOrientation(BufferedInputStream is) throws IOException {
	    int orientation = 1;
	    try {
		    Metadata metadata = ImageMetadataReader.readMetadata(is);
		    //Directory directory = metadata.getDirectory(ExifIFD0Directory.class);
		    for (Directory directory0 : metadata.getDirectories()) {
		    	try {
		    		orientation = directory0.getInt(ExifIFD0Directory.TAG_ORIENTATION);
		    	} catch (MetadataException e) {
		    		e.printStackTrace();
		    	}
		    }
		} catch (ImageProcessingException e) {
		    e.printStackTrace();
		}
	    return orientation;
    }
    
    /**
     * 파일 다운로드
     * @param request
     * @param response
     * @throws IOException
     */
    public void download(String fileName,String fileNameOrg,String filePath
    		,HttpServletRequest request,HttpServletResponse response) throws IOException{
    	FileInputStream fileInputStream = null;
  	    ServletOutputStream servletOutputStream = null;
		try{
	  	    File file = new File(this.uploadPath + filePath,fileName);
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
	public void deleteFiles(List<TCmFile> fileVOList) throws Exception {
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
	public void deleteFileOne(String filePath, String saveFileNm) throws Exception {
		
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
	public String getRandomString(){
		return UUID.randomUUID().toString().substring(0,10).replaceAll("-", "");
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
