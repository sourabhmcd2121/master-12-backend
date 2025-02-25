package com.master.app.pims.entities.schemas.citizenmaster;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "logo_dept_name", schema = "citizen_app")
public class LogoDeptName implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "logo_dept_name_guid", unique = true, nullable = false, length = 36)
	private String logoDeptNameGuid;
	
	@Transient
	private String userImage1Base64;
	
	@Column(name = "logo_image_binary", nullable=false)
	private byte[] logoImageBinary1;
	
	
	@Column(name = "dept_name_en", nullable = false)
	private String deptNameEn;
	
	@Column(name = "dept_name_hi", nullable=false)
	private String deptNameHi;
	
	@Column(name = "dept_name_rl")
	private String deptNameRl;
	
	@Column(name = "created_by", nullable=false)
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
	
//	@Column(name = "is_active")
//	private Boolean isActive;
	
	

	public LogoDeptName(String logoDeptNameGuid) {
		super();
		this.logoDeptNameGuid = logoDeptNameGuid;
	}

	
}
