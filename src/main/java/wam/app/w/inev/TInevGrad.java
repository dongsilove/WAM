package wam.app.w.inev;

import java.io.Serializable;
import javax.persistence.*;

import wam.app.common.CommonTbl;

import java.math.BigDecimal;


/**
 * The persistent class for the t_inev_grad database table.
 * 
 */
@Entity
@Table(name="t_inev_grad")
@NamedQuery(name="TInevGrad.findAll", query="SELECT t FROM TInevGrad t")
public class TInevGrad extends CommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TInevGradPK id;

	@Column(name="impvt_mesr_cn")
	private String impvtMesrCn;

	@Column(name="rang_begin_val")
	private BigDecimal rangBeginVal;

	@Column(name="rang_end_val")
	private BigDecimal rangEndVal;

	public TInevGrad() {
	}

	public TInevGradPK getId() {
		return this.id;
	}

	public void setId(TInevGradPK id) {
		this.id = id;
	}

	public String getImpvtMesrCn() {
		return this.impvtMesrCn;
	}

	public void setImpvtMesrCn(String impvtMesrCn) {
		this.impvtMesrCn = impvtMesrCn;
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

}