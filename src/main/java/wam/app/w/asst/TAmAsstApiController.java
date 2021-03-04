package wam.app.w.asst;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag( name = "TAmAsstApiController", description = "자산")
@RestController 
@RequestMapping(value="/api")
public class TAmAsstApiController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TAmAsstRepository asstRepository;
	@Autowired
	TAmAsstQuerydslRepository asstQuerydslRepository;

	@Operation(summary = "자산 목록 조회", description = "검색 값으로 페이징된 자산 목록 화면을 호출한다.")
	@GetMapping("/assts")
	public Page<TAmAsst> list( @RequestParam Map<String,Object> param,
			@RequestParam(name = "perPage", required = true, defaultValue = "20") int perPage,
			@RequestParam(name = "page", required = true, defaultValue = "1") int page) throws Exception {
		Page<TAmAsst> list;
		param.forEach((k,v)->logger.debug("key:" + k + "\tvalue:" +v));
		PageRequest pageRequest = PageRequest.of(page - 1, perPage, Sort.by(Direction.DESC, "asstSn"));
		list = asstQuerydslRepository.findList(param, pageRequest);
		return list;
		
	}

	@Operation(summary = "자산 조회", description = "자산 단건 조회한다.")
	@GetMapping("/assts/{asstSn}")
	public Optional<TAmAsst> get( @PathVariable Integer asstSn) throws Exception {

		Optional<TAmAsst> asst = asstRepository.findById(asstSn);
		
		return asst;
		
	}
	
	@Operation(summary = "자산 저장", description = "자산 저장한다.")
	@PutMapping("/assts")
	public TAmAsst put(@RequestBody @Valid TAmAsst tWdTerm)  throws Exception  {
		
		log.debug("자산 저장 호출 : {}", tWdTerm);
		TAmAsst asst;
		asst = asstRepository.save(tWdTerm);
		if (asst == null) {
			log.debug("저장시 오류발생");
		}
		return asst;
		
	}
	
	@Operation(summary = "자산 삭제", description = "자산 삭제한다.")
	@DeleteMapping("/assts/{asstSn}")
	public String delete(@PathVariable Integer asstSn) throws Exception {
		
		log.debug("자산 삭제 호출 :"+  Integer.toString(asstSn));
		asstRepository.deleteById(asstSn);
		return "200";
		
	}
	
	@Operation(summary = "공통 자산 목록 조회", description = "검색 값으로 페이징된 자산 목록 화면을 호출한다.")
	@GetMapping("/common/assts")
	public Page<TAmAsst> common_list( @RequestParam Map<String,Object> param,
			@RequestParam(name = "perPage", required = true, defaultValue = "50") int perPage,
			@RequestParam(name = "page", required = true, defaultValue = "1") int page) throws Exception {
		Page<TAmAsst> list;
		param.forEach((k,v)->logger.debug("key:" + k + "\tvalue:" +v));
		PageRequest pageRequest = PageRequest.of(page - 1, perPage, Sort.by(Direction.DESC, "asstSn"));
		list = asstQuerydslRepository.findList(param, pageRequest);
		return list;
		
	}
	
	
}