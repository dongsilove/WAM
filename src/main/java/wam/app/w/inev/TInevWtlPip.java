package wam.app.w.inev;

import java.io.Serializable;
import javax.persistence.*;

import wam.app.common.CommonTbl;

import java.math.BigDecimal;


/**
 * The persistent class for the t_inev_wtl_pip database table.
 * 
 */
@Entity
@Table(name="t_inev_wtl_pip")
@NamedQuery(name="TInevWtlPip.findAll", query="SELECT t FROM TInevWtlPip t")
public class TInevWtlPip extends CommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="T_INEV_WTL_PIP_ASSTSN_GENERATOR", sequenceName="T_INEV_WTL_PIP_ASST_SN", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_INEV_WTL_PIP_ASSTSN_GENERATOR")
	@Column(name="asst_sn")
	private Integer asstSn;

	@Column(name="brkdn_co")
	private BigDecimal brkdnCo;

	@Column(name="cnnct_dn")
	private BigDecimal cnnctDn;

	@Column(name="dgn_sect_nm")
	private String dgnSectNm;

	@Column(name="eltr_mens_val")
	private BigDecimal eltrMensVal;

	@Column(name="evl_grad_cd")
	private String evlGradCd;

	@Column(name="evl_mtrpip_cd")
	private String evlMtrpipCd;

	@Column(name="evl_rst_scor")
	private BigDecimal evlRstScor;

	@Column(name="evl_sn")
	private Integer evlSn;

	@Column(name="ftr_idn")
	private String ftrIdn;

	@Column(name="hetero_mtrpip_co")
	private BigDecimal heteroMtrpipCo;

	@Column(name="install_ymd")
	private BigDecimal installYmd;

	@Column(name="lyng_dp")
	private BigDecimal lyngDp;

	@Column(name="lyng_yynu")
	private BigDecimal lyngYynu;

	@Column(name="mtrpip_cd")
	private String mtrpipCd;

	@Column(name="mxmm_prsrwtr")
	private BigDecimal mxmmPrsrwtr;

	@Column(name="pip_dmtr")
	private BigDecimal pipDmtr;

	@Column(name="pip_lt")
	private BigDecimal pipLt;

	@Column(name="qltwtr_crsvn")
	private BigDecimal qltwtrCrsvn;

	@Column(name="road_ty")
	private String roadTy;

	@Column(name="sblk_nm")
	private String sblkNm;

	@Column(name="sect_cnnct_dn")
	private BigDecimal sectCnnctDn;

	@Column(name="sect_pip_lt")
	private BigDecimal sectPipLt;

	@Column(name="soil_crsvn")
	private BigDecimal soilCrsvn;

	@Column(name="soil_knd")
	private String soilKnd;

	@Column(name="soil_sblk_nm")
	private String soilSblkNm;

	@Column(name="vale_co")
	private BigDecimal valeCo;

	@Column(name="wtrlk_cvpl_co")
	private BigDecimal wtrlkCvplCo;

	@Column(name="wtrspl_branch_co")
	private BigDecimal wtrsplBranchCo;

	public TInevWtlPip() {
	}

	public Integer getAsstSn() {
		return this.asstSn;
	}

	public void setAsstSn(Integer asstSn) {
		this.asstSn = asstSn;
	}

	public BigDecimal getBrkdnCo() {
		return this.brkdnCo;
	}

	public void setBrkdnCo(BigDecimal brkdnCo) {
		this.brkdnCo = brkdnCo;
	}

	public BigDecimal getCnnctDn() {
		return this.cnnctDn;
	}

	public void setCnnctDn(BigDecimal cnnctDn) {
		this.cnnctDn = cnnctDn;
	}

	public String getDgnSectNm() {
		return this.dgnSectNm;
	}

	public void setDgnSectNm(String dgnSectNm) {
		this.dgnSectNm = dgnSectNm;
	}

	public BigDecimal getEltrMensVal() {
		return this.eltrMensVal;
	}

	public void setEltrMensVal(BigDecimal eltrMensVal) {
		this.eltrMensVal = eltrMensVal;
	}

	public String getEvlGradCd() {
		return this.evlGradCd;
	}

	public void setEvlGradCd(String evlGradCd) {
		this.evlGradCd = evlGradCd;
	}

	public String getEvlMtrpipCd() {
		return this.evlMtrpipCd;
	}

	public void setEvlMtrpipCd(String evlMtrpipCd) {
		this.evlMtrpipCd = evlMtrpipCd;
	}

	public BigDecimal getEvlRstScor() {
		return this.evlRstScor;
	}

	public void setEvlRstScor(BigDecimal evlRstScor) {
		this.evlRstScor = evlRstScor;
	}

	public Integer getEvlSn() {
		return this.evlSn;
	}

	public void setEvlSn(Integer evlSn) {
		this.evlSn = evlSn;
	}

	public String getFtrIdn() {
		return this.ftrIdn;
	}

	public void setFtrIdn(String ftrIdn) {
		this.ftrIdn = ftrIdn;
	}

	public BigDecimal getHeteroMtrpipCo() {
		return this.heteroMtrpipCo;
	}

	public void setHeteroMtrpipCo(BigDecimal heteroMtrpipCo) {
		this.heteroMtrpipCo = heteroMtrpipCo;
	}

	public BigDecimal getInstallYmd() {
		return this.installYmd;
	}

	public void setInstallYmd(BigDecimal installYmd) {
		this.installYmd = installYmd;
	}

	public BigDecimal getLyngDp() {
		return this.lyngDp;
	}

	public void setLyngDp(BigDecimal lyngDp) {
		this.lyngDp = lyngDp;
	}

	public BigDecimal getLyngYynu() {
		return this.lyngYynu;
	}

	public void setLyngYynu(BigDecimal lyngYynu) {
		this.lyngYynu = lyngYynu;
	}

	public String getMtrpipCd() {
		return this.mtrpipCd;
	}

	public void setMtrpipCd(String mtrpipCd) {
		this.mtrpipCd = mtrpipCd;
	}

	public BigDecimal getMxmmPrsrwtr() {
		return this.mxmmPrsrwtr;
	}

	public void setMxmmPrsrwtr(BigDecimal mxmmPrsrwtr) {
		this.mxmmPrsrwtr = mxmmPrsrwtr;
	}

	public BigDecimal getPipDmtr() {
		return this.pipDmtr;
	}

	public void setPipDmtr(BigDecimal pipDmtr) {
		this.pipDmtr = pipDmtr;
	}

	public BigDecimal getPipLt() {
		return this.pipLt;
	}

	public void setPipLt(BigDecimal pipLt) {
		this.pipLt = pipLt;
	}

	public BigDecimal getQltwtrCrsvn() {
		return this.qltwtrCrsvn;
	}

	public void setQltwtrCrsvn(BigDecimal qltwtrCrsvn) {
		this.qltwtrCrsvn = qltwtrCrsvn;
	}

	public String getRoadTy() {
		return this.roadTy;
	}

	public void setRoadTy(String roadTy) {
		this.roadTy = roadTy;
	}

	public String getSblkNm() {
		return this.sblkNm;
	}

	public void setSblkNm(String sblkNm) {
		this.sblkNm = sblkNm;
	}

	public BigDecimal getSectCnnctDn() {
		return this.sectCnnctDn;
	}

	public void setSectCnnctDn(BigDecimal sectCnnctDn) {
		this.sectCnnctDn = sectCnnctDn;
	}

	public BigDecimal getSectPipLt() {
		return this.sectPipLt;
	}

	public void setSectPipLt(BigDecimal sectPipLt) {
		this.sectPipLt = sectPipLt;
	}

	public BigDecimal getSoilCrsvn() {
		return this.soilCrsvn;
	}

	public void setSoilCrsvn(BigDecimal soilCrsvn) {
		this.soilCrsvn = soilCrsvn;
	}

	public String getSoilKnd() {
		return this.soilKnd;
	}

	public void setSoilKnd(String soilKnd) {
		this.soilKnd = soilKnd;
	}

	public String getSoilSblkNm() {
		return this.soilSblkNm;
	}

	public void setSoilSblkNm(String soilSblkNm) {
		this.soilSblkNm = soilSblkNm;
	}

	public BigDecimal getValeCo() {
		return this.valeCo;
	}

	public void setValeCo(BigDecimal valeCo) {
		this.valeCo = valeCo;
	}

	public BigDecimal getWtrlkCvplCo() {
		return this.wtrlkCvplCo;
	}

	public void setWtrlkCvplCo(BigDecimal wtrlkCvplCo) {
		this.wtrlkCvplCo = wtrlkCvplCo;
	}

	public BigDecimal getWtrsplBranchCo() {
		return this.wtrsplBranchCo;
	}

	public void setWtrsplBranchCo(BigDecimal wtrsplBranchCo) {
		this.wtrsplBranchCo = wtrsplBranchCo;
	}

}