package wam.app.w.inev;

import java.io.Serializable;
import javax.persistence.*;

import wam.app.common.CommonTbl;

import java.math.BigDecimal;


/**
 * The persistent class for the t_inev_soil_crsvn database table.
 * 
 */
@Entity
@Table(name="t_inev_soil_crsvn")
@NamedQuery(name="TInevSoilCrsvn.findAll", query="SELECT t FROM TInevSoilCrsvn t")
public class TInevSoilCrsvn extends CommonTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="soil_sblk_nm")
	private String soilSblkNm;

	@Column(name="soil_crsvn")
	private BigDecimal soilCrsvn;

	public TInevSoilCrsvn() {
	}

	public BigDecimal getSoilCrsvn() {
		return this.soilCrsvn;
	}

	public void setSoilCrsvn(BigDecimal soilCrsvn) {
		this.soilCrsvn = soilCrsvn;
	}

	public String getSoilSblkNm() {
		return this.soilSblkNm;
	}

	public void setSoilSblkNm(String soilSblkNm) {
		this.soilSblkNm = soilSblkNm;
	}

}