package wam.app.w.asst;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TAmAsstRepository extends JpaRepository<TAmAsst, Integer > {
	Page<TAmAsst> findByAsstNmContaining(String searchValue,  Pageable pageable); 			// 자산명
	Page<TAmAsst> findByAsstAccntNovContaining(String searchValue,  Pageable pageable);		// 회계자산번호
	Page<TAmAsst> findByAsstAccntSclasNmContaining(String searchValue,  Pageable pageable);	// 자산회계소분류명
	Page<TAmAsst> findByLocplcNmContaining(String searchValue,  Pageable pageable);			// 소재지명
	Page<TAmAsst> findByPsitnNmContaining(String searchValue,  Pageable pageable);			// 소속명
	Page<TAmAsst> findByFrstAcqsYmdContaining(String searchValue,  Pageable pageable);		// 최초취득일
	Page<TAmAsst> findByRevalYmdContaining(String searchValue,  Pageable pageable);			// 재평가일
	Page<TAmAsst> findByNowUslfsvcCo(String searchValue,  Pageable pageable);				// 현재내용년수
	Page<TAmAsst> findByPrcNmContaining(String searchValue,  Pageable pageable);			// 공정명
	Page<TAmAsst> findByWorktypeNmContaining(String searchValue,  Pageable pageable);		// 공종명
	Page<TAmAsst> findBySplsysNmContaining(String searchValue,  Pageable pageable);			// 공급계통명
	Page<TAmAsst> findBySplsysLocplcNmContaining(String searchValue,  Pageable pageable);	// 공급계통소재지
}
