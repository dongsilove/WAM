package wam.app.w.cdGrp;

import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

@Tag( name = "TCmCdGrpApiController", description = "코드")
@RestController
@RequestMapping(value="/api")
public class TCmCdGrpApiController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TCmCdGrpRepository cdgrpRepository;

	@Operation(summary = "코드그룹 목록 조회", description = "검색 값으로 페이징된 코드그룹 목록 화면을 호출한다.")
	@GetMapping("/cdgrps")
	public Page<TCmCdGrp> list( @RequestParam Map<String,Object> param,
			@RequestParam(name = "perPage", required = true, defaultValue = "20") int perPage,
			@RequestParam(name = "page", required = true, defaultValue = "1") int page) throws Exception {
		Page<TCmCdGrp> list;
		param.forEach((k,v)->logger.debug("key:" + k + "\tvalue:" +v));
		PageRequest pageRequest = PageRequest.of(page - 1, perPage, Sort.by(Direction.ASC, "grpCd"));
		if(param.get("grpCdNm") != null && !param.get("grpCdNm").toString().equals("")) {
			list = (Page<TCmCdGrp>) cdgrpRepository.findByGrpCdNmContaining(param.get("grpCdNm").toString(),pageRequest);
		} else {
			list = (Page<TCmCdGrp>) cdgrpRepository.findAll(PageRequest.of(page - 1, perPage));
		}
		return list;
	}
	
	@Operation(summary = "코드그룹 조회", description = "단건 조회한다.")
	@GetMapping("/cdgrps/{grpCd}")
	public Optional<TCmCdGrp> get( @PathVariable String grpCd ) throws Exception {

		Optional<TCmCdGrp> cdgrp = cdgrpRepository.findById(grpCd);
		
		return cdgrp;
		
	}
	
	@Operation(summary = "코드그룹 저장", description = "코드그룹 저장한다.")
	@PutMapping("/cdgrps")
	public TCmCdGrp put(@RequestBody TCmCdGrp tCmCdGrp) throws Exception {
		
		logger.debug("코드그룹 저장 호출 : {}", tCmCdGrp);
		TCmCdGrp cdgrp;
		cdgrp = cdgrpRepository.save(tCmCdGrp);
		if (cdgrp == null) {
			logger.debug("저장시 오류발생");
		}
		return cdgrp;
		
	}
	
	@Operation(summary = "코드그룹 삭제", description = "코드그룹 삭제한다.")
	@DeleteMapping("/cdgrps/{grpCd}")
	public String delete(@PathVariable String grpCd) throws Exception {
		
		logger.debug("코드그룹 삭제 호출 : grpCd-"+  grpCd);
		cdgrpRepository.deleteById(grpCd);
		return "200";
		
	}
	

}
