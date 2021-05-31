package wam.app.w.inev;

import java.io.Serializable;
import javax.persistence.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import wam.app.common.CommonTbl;

import java.math.BigDecimal;


/**
 * The persistent class for the t_am_wtl_pip database table.
 * 
 */
@Entity
@Table(name="t_am_wtl_pip")
@NamedQuery(name="TAmWtlPip.findAll", query="SELECT t FROM TAmWtlPip t")
@Tag(name="TAmWtlPip", description="자산 관로")
public class TAmWtlPip extends CommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="T_AM_WTL_PIP_ASSTSN_GENERATOR", sequenceName="T_AM_WTL_PIP_ASST_SN", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_AM_WTL_PIP_ASSTSN_GENERATOR")
	@Column(name="asst_sn")
	private Integer asstSn;

	@Column(name="eltr_mens_val")
	private BigDecimal eltrMensVal;

	@Column(name="ftr_idn")
	private String ftrIdn;

	@Column(name="install_ymd")
	private BigDecimal installYmd;

	@Column(name="lblk_nm")
	private String lblkNm;

	@Column(name="lwet_dp")
	private BigDecimal lwetDp;

	@Column(name="mblk_nm")
	private String mblkNm;

	@Column(name="mtrpip_cd")
	private String mtrpipCd;

	@Column(name="pip_dmtr")
	private BigDecimal pipDmtr;

	@Column(name="pip_outdmtr")
	private BigDecimal pipOutdmtr;
	
	@Column(name="pip_lt")
	private BigDecimal pipLt;

	@Column(name="pip_thkns")
	private BigDecimal pipThkns;

	@Column(name="road_ty")
	private String roadTy;

	@Column(name="sblk_nm")
	private String sblkNm;

	@Column(name="soil_knd")
	private String soilKnd;

	@Column(name="top_dp")
	private BigDecimal topDp;

	public TAmWtlPip() {
	}

	public Integer getAsstSn() {
		return this.asstSn;
	}

	public void setAsstSn(Integer asstSn) {
		this.asstSn = asstSn;
	}

	public BigDecimal getEltrMensVal() {
		return this.eltrMensVal;
	}

	public void setEltrMensVal(BigDecimal eltrMensVal) {
		this.eltrMensVal = eltrMensVal;
	}

	public String getFtrIdn() {
		return this.ftrIdn;
	}

	public void setFtrIdn(String ftrIdn) {
		this.ftrIdn = ftrIdn;
	}

	public BigDecimal getInstallYmd() {
		return this.installYmd;
	}

	public void setInstallYmd(BigDecimal installYmd) {
		this.installYmd = installYmd;
	}

	public String getLblkNm() {
		return this.lblkNm;
	}

	public void setLblkNm(String lblkNm) {
		this.lblkNm = lblkNm;
	}

	public BigDecimal getLwetDp() {
		return this.lwetDp;
	}

	public void setLwetDp(BigDecimal lwetDp) {
		this.lwetDp = lwetDp;
	}

	public String getMblkNm() {
		return this.mblkNm;
	}

	public void setMblkNm(String mblkNm) {
		this.mblkNm = mblkNm;
	}

	public String getMtrpipCd() {
		return this.mtrpipCd;
	}

	public void setMtrpipCd(String mtrpipCd) {
		this.mtrpipCd = mtrpipCd;
	}

	public BigDecimal getPipDmtr() {
		return this.pipDmtr;
	}

	public void setPipDmtr(BigDecimal pipDmtr) {
		this.pipDmtr = pipDmtr;
	}

	public BigDecimal getPipOutdmtr() {
		return pipOutdmtr;
	}

	public void setPipOutdmtr(BigDecimal pipOutdmtr) {
		this.pipOutdmtr = pipOutdmtr;
	}

	public BigDecimal getPipLt() {
		return this.pipLt;
	}

	public void setPipLt(BigDecimal pipLt) {
		this.pipLt = pipLt;
	}

	public BigDecimal getPipThkns() {
		return this.pipThkns;
	}

	public void setPipThkns(BigDecimal pipThkns) {
		this.pipThkns = pipThkns;
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

	public String getSoilKnd() {
		return this.soilKnd;
	}

	public void setSoilKnd(String soilKnd) {
		this.soilKnd = soilKnd;
	}

	public BigDecimal getTopDp() {
		return this.topDp;
	}

	public void setTopDp(BigDecimal topDp) {
		this.topDp = topDp;
	}

}