package wam.app.w.asstFclty;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TAmAsstFcltyRepository extends JpaRepository<TAmAsstFclty, Integer > {
	
	Page<TAmAsstFclty> findByAsstNmContaining(String searchValue,  Pageable pageable); 			// 자산명
	
}
