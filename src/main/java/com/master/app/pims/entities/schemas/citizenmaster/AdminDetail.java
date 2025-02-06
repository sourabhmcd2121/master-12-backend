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
@Table(name = "admin_detail", schema = "citizen_app")
public class AdminDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "admin_detail_guid", unique = true, nullable = false, length = 36)
	private String adminDetailGuid;

	@Transient
	private String userImage1Base64;

	// @JsonIgnore
	@Column(name = "user_image")
	private byte[] userImage1;

	@Column(name = "user_name", nullable = false)
	private String userName;

	@Column(name = "department_name", nullable = false)
	private String departmentName;

	@Column(name = "encrypted_pwd")
	private String encryptedPwd;

	@Column(name = "ip_address", nullable = false)
	private String ipAddress;

	@Column(name = "active_from_date", nullable = false)
	private Date activeFromDate;

	@Column(name = "active_till", nullable = false)
	private Date activeTill;

	@Column(name = "is_active", nullable = false, length = 1)
	private Boolean isActive;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@Column(name = "created_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
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
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Column(name = "modified_ip_addr")
	private String modifiedIpAddr;

	@Column(name = "modified_mac_addr")
	private String modifiedMacAddr;

	@Column(name = "modified_remarks")
	private String modifiedRemarks;

	public AdminDetail(String adminDetailGuid) {
		super();
		this.adminDetailGuid = adminDetailGuid;
	}
}
