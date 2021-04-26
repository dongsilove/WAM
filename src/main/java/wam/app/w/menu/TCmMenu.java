package wam.app.w.menu;

import java.io.Serializable;

import javax.persistence.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the t_au_dept database table.
 * 
 */
@Entity
@Getter @Setter
@Table(name="t_cm_menu")
@NamedQuery(name="TCmMenu.findAll", query="SELECT t FROM TCmMenu t")
public class TCmMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="menu_id")
	@Schema(description ="메뉴아이디" )
	private String menuId;

	@Column(name="menu_nm")
	@Schema(description ="메뉴명" )
	private String menuNm;

	@Column(name="upper_menu_id")
	@Schema(description ="상위메뉴아이디" )
	private String upperMenuId;
	
	@Column(name="menu_icon_nm")
	@Schema(description ="메뉴아이콘명" )
	private String menuIconNm;
	
	@Column(name="menu_url")
	@Schema(description ="메뉴URL" )
	private String menuUrl;
	
	@Column(name="menu_dp")
	@Schema(description = "메뉴깊이")
	private Integer menuDp;
	
	@Transient
	@Schema(description = "path")
	private String pth;
	
	@Transient
	@Schema(description = "cyc")
	private String cyc;
	
	public TCmMenu() {
	}

}