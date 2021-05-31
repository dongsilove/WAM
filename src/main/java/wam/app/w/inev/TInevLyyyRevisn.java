package wam.app.w.inev;

import java.io.Serializable;
import javax.persistence.*;

import wam.app.common.CommonTbl;

import java.math.BigDecimal;


/**
 * The persistent class for the t_inev_lyyy_revisn database table.
 * 
 */
@Entity
@Table(name="t_inev_lyyy_revisn")
@NamedQuery(name="TInevLyyyRevisn.findAll", query="SELECT t FROM TInevLyyyRevisn t")
public class TInevLyyyRevisn extends CommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="T_INEV_LYYY_REVISN_LYNGYYNUSN_GENERATOR", sequenceName="T_INEV_LYYY_REVISN_LYNG_YYNU_SN", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_INEV_LYYY_REVISN_LYNGYYNUSN_GENERATOR")
	@Column(name="lyng_yynu_sn")
	private Integer lyngYynuSn;

	@Column(name="rang_begin_val")
	private BigDecimal rangBeginVal;

	@Column(name="rang_end_val")
	private BigDecimal rangEndVal;

	@Column(name="revisn_coef")
	private BigDecimal revisnCoef;

	public TInevLyyyRevisn() {
	}

	public Integer getLyngYynuSn() {
		return this.lyngYynuSn;
	}

	public void setLyngYynuSn(Integer lyngYynuSn) {
		this.lyngYynuSn = lyngYynuSn;
	}

	public BigDecimal getRangBeginVal() {
		return this.rangBeginVal;
	}

	public void setRangBeginVal(BigDecimal rangBeginVal) {
		this.rangBeginVal = rangBeginVal;
	}

	public BigDecimal getRangEndVal() {
		return this.rangEndVal;
	}

	public void setRangEndVal(BigDecimal rangEndVal) {
		this.rangEndVal = rangEndVal;
	}

	public BigDecimal getRevisnCoef() {
		return this.revisnCoef;
	}

	public void setRevisnCoef(BigDecimal revisnCoef) {
		this.revisnCoef = revisnCoef;
	}

}