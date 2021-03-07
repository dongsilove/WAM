package wam.app.w.file;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TCmFileRepository extends JpaRepository<TCmFile, Integer > {
	
	Page<TCmFile> findByTableNmAndTableId(String TableNm, String TableId,  Pageable pageable); 
	void deleteByTableNmAndTableId(String tableNm, String tableId);
}
