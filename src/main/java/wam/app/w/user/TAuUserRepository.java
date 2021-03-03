package wam.app.w.user;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TAuUserRepository extends JpaRepository<TAuUser, String> {

	
	Page<TAuUser> findByUserNmContaining(String searchValue, Pageable pageable);
	TAuUser findByUserIdAndPwd(String userId, String pwd); // 로그인 점검
	
}
