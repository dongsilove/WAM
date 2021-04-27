package wam.app.w.asstCw;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TAmAsstCwRepository extends JpaRepository<TAmAsstCw, Integer > {
	
	Page<TAmAsstCw> findByAsstNmContaining(String searchValue,  Pageable pageable); 			// 자산명
	
}
