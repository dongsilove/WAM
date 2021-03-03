package wam.app.w.code;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the t_cm_code database table.
 * 
 */
@Entity
public class TCmCodePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "GRP_CD")
	private String grpCd;
	
	@Id	
	@Column(name = "CD")
	private String cd;

	public TCmCodePK() {
	}
	public String getGrpCd() {
		return this.grpCd;
	}
	public void setGrpCd(String grpCd) {
		this.grpCd = grpCd;
	}
	public String getCd() {
		return this.cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cd == null) ? 0 : cd.hashCode());
		result = prime * result + ((grpCd == null) ? 0 : grpCd.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TCmCodePK other = (TCmCodePK) obj;
		if (cd == null) {
			if (other.cd != null)
				return false;
		} else if (!cd.equals(other.cd))
			return false;
		if (grpCd == null) {
			if (other.grpCd != null)
				return false;
		} else if (!grpCd.equals(other.grpCd))
			return false;
		return true;
	}

}