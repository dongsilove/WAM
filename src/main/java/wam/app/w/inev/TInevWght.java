package wam.app.w.inev;

import java.io.Serializable;
import javax.persistence.*;

import wam.app.common.CommonTbl;

import java.math.BigDecimal;


/**
 * The persistent class for the t_inev_wght database table.
 * 
 */
@Entity
@Table(name="t_inev_wght")
@NamedQuery(name="TInevWght.findAll", query="SELECT t FROM TInevWght t")
public class TInevWght extends CommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TInevWghtPK id;

	@Column(name="evl_itm_nm")
	private String evlItmNm;

	@Column(name="evl_itm_se")
	private String evlItmSe;

	@Column(name="wght_val")
	private BigDecimal wghtVal;

	public TInevWght() {
	}

	public TInevWghtPK getId() {
		return this.id;
	}

	public void setId(TInevWghtPK id) {
		this.id = id;
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

	public BigDecimal getWghtVal() {
		return this.wghtVal;
	}

	public void setWghtVal(BigDecimal wghtVal) {
		this.wghtVal = wghtVal;
	}

}