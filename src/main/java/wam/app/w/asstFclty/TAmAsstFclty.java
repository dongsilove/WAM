package wam.app.w.asstFclty;

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

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import wam.app.common.CommonTbl;


/**
 * The persistent class for the t_am_asst_fclty database table.
 * 
 */
@Entity
@Getter @Setter
@Table(name="t_am_asst_fclty")
@NamedQuery(name="TAmAsstFclty.findAll", query="SELECT t FROM TAmAsstFclty t")
public class TAmAsstFclty extends CommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="T_AM_ASST_FCLTY_ASSTSN_GENERATOR", sequenceName="T_AM_ASST_FCLTY_ASST_SN_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_AM_ASST_FCLTY_ASSTSN_GENERATOR")
	@Column(name="asst_sn")
	@Schema(description ="자산일련번호" )
	private Integer asstSn;

	@Column(name="asst_cnfirm_yn")
	private String asstCnfirmYn;

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

	@Column(name="index_se")
	@Schema(description ="색인구분" )
	private String indexSe;

	@Column(name="install_ymd")
	private String installYmd;

	@Column(name="lclas_nm")
	private String lclasNm;

	@Column(name="locplc_nm")
	private String locplcNm;

	@Column(name="makr_nm")
	@Schema(description ="제조사명" )
	private String makrNm;

	@Column(name="mclas_nm")
	private String mclasNm;

	@Column(name="model_nm")
	private String modelNm;

	@Column(name="model_nov")
	private String modelNov;

	@Column(name="mtrqlt_nm")
	private String mtrqltNm;

	@Column(name="now_acntbk_am")
	private BigDecimal nowAcntbkAm;

	@Column(name="now_acqs_pc")
	private BigDecimal nowAcqsPc;

	@Column(name="now_rsvmney_sm")
	private BigDecimal nowRsvmneySm;

	@Column(name="now_uslfsvc_co")
	private BigDecimal nowUslfsvcCo;

	@Column(name="prc_nm")
	private String prcNm;

	@Column(name="psitn_nm")
	private String psitnNm;

	@Column(name="reval_am")
	private BigDecimal revalAm;

	@Column(name="reval_ymd")
	private String revalYmd;

	private String rm;

	@Column(name="sclas_nm")
	private String sclasNm;

	@Column(name="splsys_locplc_nm")
	private String splsysLocplcNm;

	@Column(name="splsys_nm")
	private String splsysNm;

	@Column(name="stndrd_nm")
	private String stndrdNm;

	@Column(name="struct_nm")
	private String structNm;

	@Column(name="use_yn")
	private String useYn;

	@Column(name="worktype_nm")
	private String worktypeNm;

	@Column(name="yr_dprt_am")
	private BigDecimal yrDprtAm;

	public TAmAsstFclty() {
	}


}