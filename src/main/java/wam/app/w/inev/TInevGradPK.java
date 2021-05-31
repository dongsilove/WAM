package wam.app.w.inev;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the t_inev_grad database table.
 * 
 */
@Embeddable
public class TInevGradPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="evl_mtrpip_cd")
	private String evlMtrpipCd;

	@Column(name="evl_grad_cd")
	private String evlGradCd;

	public TInevGradPK() {
	}
	public String getEvlMtrpipCd() {
		return this.evlMtrpipCd;
	}
	public void setEvlMtrpipCd(String evlMtrpipCd) {
		this.evlMtrpipCd = evlMtrpipCd;
	}
	public String getEvlGradCd() {
		return this.evlGradCd;
	}
	public void setEvlGradCd(String evlGradCd) {
		this.evlGradCd = evlGradCd;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TInevGradPK)) {
			return false;
		}
		TInevGradPK castOther = (TInevGradPK)other;
		return 
			this.evlMtrpipCd.equals(castOther.evlMtrpipCd)
			&& this.evlGradCd.equals(castOther.evlGradCd);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.evlMtrpipCd.hashCode();
		hash = hash * prime + this.evlGradCd.hashCode();
		
		return hash;
	}
}