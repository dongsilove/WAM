package wam.app.w.asstCw;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import wam.app.util.CmFileUtils;
import wam.app.w.file.TCmFile;
import wam.app.w.file.TCmFileRepository;

@Slf4j
@Tag( name = "TAmAsstFcltyApiController", description = "자산")
@RestController 
@RequestMapping(value="/api")
public class TAmAsstCwApiController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TAmAsstCwRepository asstRepository;
	@Autowired
	TAmAsstCwQuerydslRepository asstQuerydslRepository;
	@Autowired
	TCmFileRepository fileRepository;
	@Autowired
	CmFileUtils cmFileUtils;

	@Operation(summary = "자산 목록 조회", description = "검색 값으로 페이징된 자산 목록 화면을 호출한다.")
	@GetMapping("/asstcws")
	public Page<TAmAsstCw> list( @RequestParam Map<String,Object> param,
			@RequestParam(name = "perPage", required = true, defaultValue = "20") int perPage,
			@RequestParam(name = "page", required = true, defaultValue = "1") int page) throws Exception {
		Page<TAmAsstCw> list;
		param.forEach((k,v)->logger.debug("key:" + k + "\tvalue:" +v));
		PageRequest pageRequest = PageRequest.of(page - 1, perPage, Sort.by(Direction.DESC, "asstSn"));
		list = asstQuerydslRepository.findList(param, pageRequest);
		return list;
		
	}

	@Operation(summary = "자산 조회", description = "자산 단건 조회한다.")
	@GetMapping("/asstcws/{asstSn}")
	public Optional<TAmAsstCw> get( @PathVariable Integer asstSn) throws Exception {

		Optional<TAmAsstCw> asstOpt = asstRepository.findById(asstSn);
		
		return asstOpt;
		
	}
	
	@Operation(summary = "자산 저장", description = "자산 저장한다.")
	@RequestMapping(value="/asstcws", method=RequestMethod.PUT, consumes= {"multipart/form-data"})
	public TAmAsstCw put(@Valid TAmAsstCw tAmAsstFclty, HttpServletRequest request, Errors errors)  throws Exception  {
		if (errors.hasErrors()) {
			log.debug("자산 저장 오류: " + errors.getErrorCount());
		}
		log.debug("자산 저장 호출 : {}", tAmAsstFclty);
		TAmAsstCw asst;
		asst = asstRepository.save(tAmAsstFclty);
		if (asst == null) {
			log.debug("저장시 오류발생");
		} else {
			// 파일저장 : vo로 입력받는 것이 아니라, request에서 값을 받아 저장
			String tableNm = "T_AM_ASST_CW";
			String tableId = asst.getAsstSn().toString();
			String path = tableNm;
			log.debug("tableId : " + tableId);
			List<TCmFile> fileList = cmFileUtils.saveMultiFile(tableNm,tableId,request,path); // 파일 저장
			for(TCmFile vo :fileList) {
				fileRepository.save(vo); // 파일정보 DB저장
			}
		}
			
		return asst;
		
	}
	
	
	@Operation(summary = "자산 삭제", description = "자산 삭제한다.")
	@DeleteMapping("/asstcws/{asstSn}")
	public String delete(@PathVariable Integer asstSn) throws Exception {
		log.debug("자산 삭제 호출 :"+  Integer.toString(asstSn));
		if (asstSn == null || asstSn < 1) return "201"; // 자산 일련번호 오류

		// 자산 삭제
		//asstRepository.deleteById(asstSn);
		Optional<TAmAsstCw> asstOpt = asstRepository.findById(asstSn);
		if (asstOpt.isPresent()) {
			TAmAsstCw asstFclty = asstOpt.get();
			asstFclty.setUseYn("N");
			asstRepository.save(asstFclty);
		}
		
		return "200";
		
	}
	
	@Operation(summary = "공통 자산 목록 조회", description = "검색 값으로 페이징된 자산 목록 화면을 호출한다.")
	@GetMapping("/common/asstcws")
	public Page<TAmAsstCw> common_list( @RequestParam Map<String,Object> param,
			@RequestParam(name = "perPage", required = true, defaultValue = "50") int perPage,
			@RequestParam(name = "page", required = true, defaultValue = "1") int page) throws Exception {
		Page<TAmAsstCw> list;
		param.forEach((k,v)->logger.debug("key:" + k + "\tvalue:" +v));
		PageRequest pageRequest = PageRequest.of(page - 1, perPage, Sort.by(Direction.DESC, "asstSn"));
		list = asstQuerydslRepository.findList(param, pageRequest);
		return list;
		
	}
	
	
}
