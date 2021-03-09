package wam.app.w.file;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import wam.app.util.CmFileUtils;

@Slf4j
@Tag( name = "TCmFileApiController", description = "파일")
@RestController 
@RequestMapping(value="/api")
public class TCmFileApiController {

	//private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TCmFileRepository fileRepository;


	@Operation(summary = "파일 목록 조회", description = "검색 값으로 페이징된 파일 목록 화면을 호출한다.")
	@GetMapping("/cmfiles")
	public Page<TCmFile> list( @RequestParam Map<String,Object> param,
			@RequestParam(name = "perPage", required = true, defaultValue = "20") int perPage,
			@RequestParam(name = "page", required = true, defaultValue = "1") int page) throws Exception {
		
		Page<TCmFile> list;
		param.forEach((k,v)->log.debug("key:" + k + "\tvalue:" +v));
		PageRequest pageRequest = PageRequest.of(page - 1, perPage, Sort.by(Direction.DESC, "fileSn"));
		list = fileRepository.findAll(pageRequest);
		return list;
		
	}
	
	@Operation(summary = "자료의 첨부파일 목록 조회", description = "자료의 TABLE명, TABLE 기본키에 해당하는 파일 목록을 반환한다.")
	@GetMapping("/cmfiles/{tableNm}/{tableId}")
	public Page<TCmFile> listByTableNmAndTableId( @PathVariable String tableNm, @PathVariable String tableId,
			//@RequestParam Map<String,Object> param,
			@RequestParam(name = "perPage", required = true, defaultValue = "20") int perPage,
			@RequestParam(name = "page", required = true, defaultValue = "1") int page) throws Exception {
		
		Page<TCmFile> list;
		//param.forEach((k,v)->log.debug("key:" + k + "\tvalue:" +v));
		PageRequest pageRequest = PageRequest.of(page - 1, perPage, Sort.by(Direction.ASC, "fileSn"));
		list = fileRepository.findByTableNmAndTableId(tableNm, tableId, pageRequest);
		return list;
		
	}
	
	@Operation(summary = "파일 조회", description = "파일 단건 조회한다.")
	@GetMapping("/cmfiles/{fileSn}")
	public Optional<TCmFile> get( @PathVariable Integer fileSn) throws Exception {

		Optional<TCmFile> cmfileOpt = fileRepository.findById(fileSn);
		
		return cmfileOpt;
		
	}
	
	@Operation(summary = "파일 저장", description = "파일 저장한다.")
	@PutMapping("/cmfiles")
	public TCmFile put(@RequestBody @Valid TCmFile tCmFile)  throws Exception  {
		
		log.debug("파일 저장 호출 : {}", tCmFile);
		TCmFile cmfile;
		cmfile = fileRepository.save(tCmFile);
		if (cmfile == null) {
			log.debug("저장시 오류발생");
		}
		return cmfile;
		
	}
	
	@Operation(summary = "파일 삭제", description = "파일 삭제한다.")
	@DeleteMapping("/cmfiles")
	public String delete(@RequestBody Map<String,Object> param  ) throws Exception { //, @RequestParam Map<String,Object> param
		
		//log.debug("파일 삭제 호출 delete() :"+  Integer.toString(fileSn));
		
		param.forEach((k,v)->log.debug("key:" + k + "\tvalue:" +v));
		if (param.get("filePath") == null || param.get("filePath").toString().equals("")) 
			return "201|filePath parameter 누락";
		if (param.get("saveFileNm") == null || param.get("saveFileNm").toString().equals("")) 
			return "201|saveFileNm parameter 누락";
		if (param.get("fileSn") == null || param.get("fileSn").toString().equals("")) 
			return "201|fileSn parameter 누락";
		
		Integer fileSn = Integer.parseInt(param.get("fileSn").toString());
		CmFileUtils.deleteFileOne(param.get("filePath").toString(), param.get("saveFileNm").toString());
		
		fileRepository.deleteById(fileSn);
		
		return "200";
		
	}
	
	@Operation(summary = "자료의 첨부파일 삭제", description = "자료의 TABLE명, TABLE 기본키에 해당하는 파일들을 삭제한다.")
	@DeleteMapping("/cmfiles/{tableNm}/{tableId}")
	public String deleteByTableNmAndTableId(@PathVariable String tableNm, @PathVariable String tableId) throws Exception {
		
		log.debug("파일 삭제 호출 deleteByTableNmAndTableId() :"+ tableNm + "," + tableId);
		
		int page = 1, perPage = 20;
		PageRequest pageRequest = PageRequest.of(page - 1, perPage);
		Page<TCmFile> list = fileRepository.findByTableNmAndTableId(tableNm, tableId, pageRequest);
		CmFileUtils.deleteFiles(list.getContent());
		
		fileRepository.deleteByTableNmAndTableId(tableNm,tableId);
		
		return "200";
		
	}

	/**
	 * fileSn(pk)으로 파일 이미지 byte
	 * @param fileSn
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value= "/cmfiles/showImageFile/{fileSn}", method = RequestMethod.GET,  
			produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] showImageFileFileSn(@PathVariable Integer fileSn) throws Exception {
		
		byte[] bfile = null;
		TCmFile resultFile = null;
		
		try {
			Optional<TCmFile> cmfileOpt = fileRepository.findById(fileSn);
			if (!cmfileOpt.isPresent()) return null;
			resultFile =  cmfileOpt.get();
			
			String strName = new String(resultFile.getSaveFileNm().getBytes("UTF-8"), "ISO-8859-1");
			String fullPath = CmFileUtils.getUploadPath() + resultFile.getFilePath() + File.separator + strName;
			//System.out.println("fullPath : " + fullPath);
			File file = null;
			//파일 열 때 경로상에 없을 경우 예외처리
			file = new File(fullPath);
			if (file.exists()) {
				System.out.println("=====file 찾았습니다.====");	
				bfile = Files.readAllBytes(Paths.get(fullPath));
			} else {
				System.out.println("=====경로 상의 파일을 찾을 수 없습니다.====");	
				ClassPathResource resource = new ClassPathResource("noImage.jpg"); 
				File noImageFile = resource.getFile();
				bfile = FileCopyUtils.copyToByteArray(noImageFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return bfile;
	}
	
	/**
	 * 파일 다운로드
	 * @param savePath 상대경로, saveFileNm 저장된 파일명, realFileNm 원본파일명
	 * @return
	 */
	@RequestMapping(value= "/cmfiles/fileDownload", method = RequestMethod.GET)
	public void fileDownload( @RequestParam Map<String, String> param
			,HttpServletResponse response, HttpServletRequest request ) throws Exception {
  		try {
  			param.forEach((k,v)->log.debug("key: "+k+"\tvalue: "+v));
  			String filePath = param.get("filePath");
  			String uploadFileNm = param.get("uploadFileNm");
  	  		String saveFileNm = param.get("saveFileNm");
  	  		CmFileUtils.download( saveFileNm, uploadFileNm, filePath, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
