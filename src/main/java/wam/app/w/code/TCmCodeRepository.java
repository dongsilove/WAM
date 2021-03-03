package wam.app.w.code;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TCmCodeRepository extends JpaRepository<TCmCode, Integer> {

	// 목록 조회
	Page<TCmCode> findByGrpCd(String searchValue, Pageable pageable);
	Page<TCmCode> findByCdNmContaining(String searchValue, Pageable pageable);
	
	// 단건 조회
	Optional<TCmCode> findByGrpCdAndCd(String grpCd,String cd);
	
	// 공통 목록 조회
	List<TCmCode> findByGrpCd(String grpCd);

	// 삭제
	void deleteByGrpCdAndCd(String grpCd, String cd);
	
}
