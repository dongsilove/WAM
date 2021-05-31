package wam.app.w.inev;

import java.io.Serializable;
import javax.persistence.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import wam.app.common.CommonTbl;

import java.math.BigDecimal;


/**
 * The persistent class for the t_inev_cnd database table.
 * 
 */
@Entity
@Table(name="t_inev_cnd")
@NamedQuery(name="TInevCnd.findAll", query="SELECT t FROM TInevCnd t")
@Tag(name="TInevCnd", description="평가조건")
public class TInevCnd extends CommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="T_INEV_CND_CNDSN_GENERATOR", sequenceName="T_INEV_CND_CND_SN", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_INEV_CND_CNDSN_GENERATOR")
	@Column(name="cnd_sn")
	private Integer cndSn;

	@Column(name="cnd_val")
	private BigDecimal cndVal;

	@Column(name="evl_expe_cn")
	private String evlExpeCn;

	@Column(name="evl_itm_cd")
	private String evlItmCd;

	@Column(name="evl_itm_nm")
	private String evlItmNm;

	@Column(name="evl_itm_se")
	private String evlItmSe;

	@Column(name="evl_mtrpip_cd")
	private String evlMtrpipCd;

	@Column(name="evl_objtv")
	private String evlObjtv;

	@Column(name="rang_begin_val")
	private BigDecimal rangBeginVal;

	@Column(name="rang_end_incs_yn")
	private String rangEndIncsYn;

	@Column(name="rang_end_val")
	private BigDecimal rangEndVal;

	public TInevCnd() {
	}

	public Integer getCndSn() {
		return this.cndSn;
	}

	public void setCndSn(Integer cndSn) {
		this.cndSn = cndSn;
	}

	public BigDecimal getCndVal() {
		return this.cndVal;
	}

	public void setCndVal(BigDecimal cndVal) {
		this.cndVal = cndVal;
	}

	public String getEvlExpeCn() {
		return this.evlExpeCn;
	}

	public void setEvlExpeCn(String evlExpeCn) {
		this.evlExpeCn = evlExpeCn;
	}

	public String getEvlItmCd() {
		return this.evlItmCd;
	}

	public void setEvlItmCd(String evlItmCd) {
		this.evlItmCd = evlItmCd;
	}

	public String getEvlItmNm() {
		return this.evlItmNm;
	}

	public void setEvlItmNm(String evlItmNm) {
		this.evlItmNm = evlItmNm;
	}

	public String getEvlItmSe() {
		return this.evlItmSe;
	}

	public void setEvlItmSe(String evlItmSe) {
		this.evlItmSe = evlItmSe;
	}

	public String getEvlMtrpipCd() {
		return this.evlMtrpipCd;
	}

	public void setEvlMtrpipCd(String evlMtrpipCd) {
		this.evlMtrpipCd = evlMtrpipCd;
	}

	public String getEvlObjtv() {
		return this.evlObjtv;
	}

	public void setEvlObjtv(String evlObjtv) {
		this.evlObjtv = evlObjtv;
	}

	public BigDecimal getRangBeginVal() {
		return this.rangBeginVal;
	}

	public void setRangBeginVal(BigDecimal rangBeginVal) {
		this.rangBeginVal = rangBeginVal;
	}

	public String getRangEndIncsYn() {
		return this.rangEndIncsYn;
	}

	public void setRangEndIncsYn(String rangEndIncsYn) {
		this.rangEndIncsYn = rangEndIncsYn;
	}

	public BigDecimal getRangEndVal() {
		return this.rangEndVal;
	}

	public void setRangEndVal(BigDecimal rangEndVal) {
		this.rangEndVal = rangEndVal;
	}

}