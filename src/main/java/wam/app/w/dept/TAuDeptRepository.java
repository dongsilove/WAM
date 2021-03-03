package wam.app.w.dept;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TAuDeptRepository extends JpaRepository<TAuDept, String> {

	
	Page<TAuDept> findByDeptNmContaining(String searchValue, Pageable pageable);
	
	
}
