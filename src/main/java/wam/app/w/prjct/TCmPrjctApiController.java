package wam.app.w.prjct;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag( name = "TCmPrjctApiController", description = "프로젝트")
@RestController 
@RequestMapping(value="/api")
public class TCmPrjctApiController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TCmPrjctRepository prjctRepository;

	@Operation(summary = "프로젝트 목록 조회", description = "검색 값으로 페이징된 프로젝트 목록 화면을 호출한다.")
	@GetMapping("/prjcts")
	public Page<TCmPrjct> list( @RequestParam Map<String,Object> param,
			@RequestParam(name = "perPage", required = true, defaultValue = "20") int perPage,
			@RequestParam(name = "page", required = true, defaultValue = "1") int page) throws Exception {
		
		Page<TCmPrjct> list;
		param.forEach((k,v)->logger.debug("key:" + k + "\tvalue:" +v));
		PageRequest pageRequest = PageRequest.of(page - 1, perPage, Sort.by(Direction.DESC, "prjctSn"));
		if(param.get("prjctNm") != null && !param.get("prjctNm").toString().equals("")) {
			list = (Page<TCmPrjct>) prjctRepository.findByPrjctNmContaining(param.get("prjctNm").toString(),pageRequest);
		} else {
			list = (Page<TCmPrjct>) prjctRepository.findAll(pageRequest);
		}
		return list;
		
	}
	
	@Operation(summary = "프로젝트 조회", description = "프로젝트 단건 조회한다.")
	@GetMapping("/prjcts/{prjctSn}")
	public Optional<TCmPrjct> get( @PathVariable Integer prjctSn) throws Exception {

		Optional<TCmPrjct> prjct = prjctRepository.findById(prjctSn);
		
		return prjct;
		
	}
	
	@Operation(summary = "프로젝트 저장", description = "프로젝트 저장한다.")
	@PutMapping("/prjcts")
	public TCmPrjct put(@RequestBody TCmPrjct tWdPrjct) throws Exception {
		
		logger.debug("프로젝트 저장 호출 : {}", tWdPrjct);
		TCmPrjct prjct;
		prjct = prjctRepository.save(tWdPrjct);
		if (prjct == null) {
			logger.debug("저장시 오류발생");
		}
		return prjct;
		
	}
	
	@Operation(summary = "프로젝트 삭제", description = "프로젝트 삭제한다.")
	@DeleteMapping("/prjcts/{prjctSn}")
	public String delete(@PathVariable Integer prjctSn) throws Exception {
		
		logger.debug("프로젝트 삭제 호출 :"+  Integer.toString(prjctSn));
		prjctRepository.deleteById(prjctSn);
		return "200";
		
	}

	@Operation(summary = "공통 프로젝트 목록 조회", description = "검색 값으로 프로젝트 목록을 반환한다.")
	@GetMapping("/common/prjcts")
	public Page<TCmPrjct> common_list( @RequestParam Map<String,Object> param,
			@RequestParam(name = "perPage", required = true, defaultValue = "20") int perPage,
			@RequestParam(name = "page", required = true, defaultValue = "1") int page) throws Exception {
		
		Page<TCmPrjct> list;
		param.forEach((k,v)->logger.debug("key:" + k + "\tvalue:" +v));
		PageRequest pageRequest = PageRequest.of(page - 1, perPage, Sort.by(Direction.DESC, "prjctSn"));
		if(param.get("prjctNm") != null && !param.get("prjctNm").toString().equals("")) {
			list = (Page<TCmPrjct>) prjctRepository.findByPrjctNmContaining(param.get("prjctNm").toString(),pageRequest);
		} else {
			list = (Page<TCmPrjct>) prjctRepository.findAll(Pageable.unpaged());
		}
		return list; 
		
	}
	
}
