package com.master.app.pims.entities.schemas.citizenmaster;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.master.app.pims.entities.schemas.master.OrgPrimary;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "menu", schema = "citizen_app")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Menu.findAll", query = "SELECT g FROM Menu g")})
public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "menu_guid", unique = true, nullable = false, length = 36)
	private String menuGuid;

	@Column(name = "menu_name_en", nullable = false)
	private String menuNameEn;

	@Column(name = "menu_name_hi")
	private String menuNameHi;

	@Column(name = "manu_name_rl")
	private String menuNameRl;

	@Column(name = "menu_url")
	private String menuUrl;

	@Column(name = "order_number")
	private Long orderNumber;

	@Column(name = "is_authorized")
	private String isAuthorized;

	@Column(name = "is_active")
	private Boolean isActive;

//	@Transient
//	private String adminDetail;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@Column(name = "created_date", nullable = false)
	private Date createdDate;

	@Column(name = "created_ip_addr", nullable = false)
	private String createdIpAddr;

	@Column(name = "created_mac_addr")
	private String createdMacAddr;

	@Column(name = "created_remarks")
	private String createdRemarks;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	private Date modifiedDate;

	@Column(name = "modified_ip_addr")
	private String modifiedIpAddr;

	@Column(name = "modified_mac_addr")
	private String modifiedMacAddr;

	@Column(name = "modified_remarks")
	private String modifiedRemarks;
	
	@Transient
    private String  secondaryMenuGuid;

	@Transient
    private String  ternaryMenuGuid;

	@Transient
    private String  fourthMenuGuid;

	@Transient
    private String primaryMenu;

	@Transient
	private String secondryMenu;

	@Transient
	private String ternaryMenu;

	@Transient
	private String fourthMenu;

	@Column(name = "primary_menu_guid") // Remove @Transient, this will map to the database column
	private String primaryMenuGuid;

	@ManyToOne
	@JoinColumn(name = "primary_menu_guid", referencedColumnName = "menu_guid", insertable = false, updatable = false)
	private Menu primaryMenumaster;

	public Menu(String menuGuid) {
		super();
		this.menuGuid = menuGuid;
	}

}
