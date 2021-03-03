package wam.app.w.word;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TWdWordRepository extends JpaRepository<TWdWord, Integer > {
	Page<TWdWord> findByWordNmContaining(String searchWord, Pageable pageable);
	Page<TWdWord> findByWordEnAbbrContaining(String searchWord, Pageable pageable);
	Page<TWdWord> findByWordEnNmContaining(String searchWord, Pageable pageable);
	Page<TWdWord> findByThemaSeContaining(String searchWord, Pageable pageable);
	Page<TWdWord> findBySynonmContaining(String searchWord, Pageable pageable);
	Page<TWdWord> findByPrhibtWordNmContaining(String searchWord, Pageable pageable);
}
