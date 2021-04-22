package wam.app.w.userGrp;

import java.io.Serializable;

import javax.persistence.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


/**
 * 사용자 그룹
 * 
 */
@Entity
@Getter @Setter
@Table(name="t_au_user_grp")
@NamedQuery(name="TAuUserGrp.findAll", query="SELECT t FROM TAuUserGrp t")
public class TAuUserGrp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="grp_cd")
	@Schema(description ="사용자그룹코드" )
	private String grpCd;

	@Column(name="grp_nm")
	@Schema(description ="사용자그룹명" )
	private String grpNm;

	@Column(name="author_cn")
	@Schema(description ="권한내용" )
	private String authorCn;
	
	public TAuUserGrp() {
	}
	
	public String toString() {
		return "grpCd : " + this.grpCd + "\ngrpNm : " + this.grpNm;
	}



}