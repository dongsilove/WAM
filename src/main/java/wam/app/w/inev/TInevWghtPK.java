package wam.app.w.inev;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the t_inev_wght database table.
 * 
 */
@Embeddable
public class TInevWghtPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="evl_itm_cd")
	private String evlItmCd;

	@Column(name="evl_mtrpip_cd")
	private String evlMtrpipCd;

	public TInevWghtPK() {
	}
	public String getEvlItmCd() {
		return this.evlItmCd;
	}
	public void setEvlItmCd(String evlItmCd) {
		this.evlItmCd = evlItmCd;
	}
	public String getEvlMtrpipCd() {
		return this.evlMtrpipCd;
	}
	public void setEvlMtrpipCd(String evlMtrpipCd) {
		this.evlMtrpipCd = evlMtrpipCd;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TInevWghtPK)) {
			return false;
		}
		TInevWghtPK castOther = (TInevWghtPK)other;
		return 
			this.evlItmCd.equals(castOther.evlItmCd)
			&& this.evlMtrpipCd.equals(castOther.evlMtrpipCd);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.evlItmCd.hashCode();
		hash = hash * prime + this.evlMtrpipCd.hashCode();
		
		return hash;
	}
}