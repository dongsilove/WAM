package wam.app.w.file;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import wam.app.common.CommonTbl;


/**
 * The persistent class for the t_cm_file database table.
 * 
 */
@Entity
@Getter @Setter
@Table(name="t_cm_file")
@NamedQuery(name="TCmFile.findAll", query="SELECT t FROM TCmFile t")
public class TCmFile extends CommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="T_CM_FILE_FILESN_GENERATOR", sequenceName="T_CM_FILE_FILE_SN_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_CM_FILE_FILESN_GENERATOR")
	@Column(name="file_sn")
	@Schema(description ="파일일련번호" )
	private Integer fileSn;

	@Column(name="file_path")
	@Schema(description ="파일경로" )
	private String filePath;

	@Column(name="file_size")
	@Schema(description ="파일사이즈" )
	private long fileSize;

	@Column(name="file_ty")
	@Schema(description ="파일유형" )
	private String fileTy;

	@Column(name="save_file_nm")
	@Schema(description ="저장파일명" )
	private String saveFileNm;

	@Column(name="table_nm")
	@Schema(description ="테이블명" )
	private String tableNm;

	@Column(name="table_id")
	@Schema(description ="테이블아이디" )
	private String tableId;

	@Column(name="upload_file_nm")
	@Schema(description ="업로드파일명" )
	private String uploadFileNm;

	@Column(name="use_yn")
	@Schema(description ="사용여부" )
	private String useYn;

	public TCmFile() {
	}


}