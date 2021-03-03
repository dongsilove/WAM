package wam.app.w.asst;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import wam.app.common.CommonTbl;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the t_am_asst database table.
 * 
 */
@Entity
@Getter @Setter
@Table(name="t_am_asst")
@NamedQuery(name="TAmAsst.findAll", query="SELECT t FROM TAmAsst t")
public class TAmAsst extends CommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="T_AM_ASST_ASSTSN_GENERATOR", sequenceName="T_AM_ASST_ASST_SN_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_AM_ASST_ASSTSN_GENERATOR")
	@Column(name="asst_sn")
	@Schema(description ="자산일련번호" )
	private Integer asstSn;

	@Column(name="asst_accnt_nov")
	@Schema(description ="자산회계번호" )
	private String asstAccntNov;

	@Column(name="asst_accnt_sclas_nm")
	@Schema(description ="자산회계소분류명" )
	private String asstAccntSclasNm;

	@Column(name="asst_nm")
	@Schema(description ="자산명" )
	private String asstNm;

	@Schema(description ="수량" )
	private BigDecimal co;

	@Column(name="fom_nm")
	@Schema(description ="형식명" )
	private String fomNm;

	@Column(name="frst_acqs_pc")
	@Schema(description ="최초취득가" )
	private BigDecimal frstAcqsPc;

	@Column(name="frst_acqs_ymd")
	@Schema(description ="최초취득일" )
	private String frstAcqsYmd;

	@Column(name="install_ymd")
	@Schema(description ="설치일(준공일)" )
	private String installYmd;

	@Column(name="lclas_nm")
	@Schema(description ="대분류명" )
	private String lclasNm;

	@Column(name="locplc_nm")
	@Schema(description ="소재지명" )
	private String locplcNm;

	@Column(name="mclas_nm")
	@Schema(description ="중분류명" )
	private String mclasNm;

	@Column(name="model_nm")
	@Schema(description ="모델명(등기일)" )
	private String modelNm;

	@Column(name="model_nov")
	@Schema(description ="모델번호(등기번호)" )
	private String modelNov;

	@Column(name="mtrqlt_nm")
	@Schema(description ="재질명" )
	private String mtrqltNm;

	@Column(name="now_acntbk_am")
	@Schema(description ="현재장부액" )
	private BigDecimal nowAcntbkAm;

	@Column(name="now_acqs_pc")
	@Schema(description ="현재취득가" )
	private BigDecimal nowAcqsPc;

	@Column(name="now_rsvmney_sm")
	@Schema(description ="현재충당금계" )
	private BigDecimal nowRsvmneySm;

	@Column(name="now_uslfsvc_co")
	@Schema(description ="현재내용년수" )
	private BigDecimal nowUslfsvcCo;

	@Column(name="prc_nm")
	@Schema(description ="공정명" )
	private String prcNm;

	@Column(name="psitn_nm")
	@Schema(description ="소속명" )
	private String psitnNm;

	@Column(name="reval_am")
	@Schema(description ="재평가액" )
	private BigDecimal revalAm;

	@Column(name="reval_ymd")
	@Schema(description ="재평가일" )
	private String revalYmd;

	@Column(name="sclas_nm")
	@Schema(description ="소분류명" )
	private String sclasNm;

	@Column(name="stndrd_nm")
	@Schema(description ="규격명" )
	private String stndrdNm;

	@Column(name="struct_nm")
	@Schema(description ="구조명" )
	private String structNm;

	@Column(name="use_yn")
	@Schema(description ="사용여부" )
	private String useYn;

	@Column(name="worktype_nm")
	@Schema(description ="공종명" )
	private String worktypeNm;

	@Column(name="yr_dprt_am")
	@Schema(description ="연상각액" )
	private BigDecimal yrDprtAm;

	@Column(name="splsys_nm")
	@Schema(description ="공급계통명" )
	private String splsysNm;
	
	@Column(name="asst_cnfirm_yn")
	@Schema(description ="자산확인여부" )
	private String asstCnfirmYn;
	
	@Column(name="rm")
	@Schema(description ="비고" )
	private String rm;
	
	@Column(name="splsys_locplc_nm")
	@Schema(description ="계통소재지명" )
	private String splsysLocplcNm;
	
	public TAmAsst() {
	}



}