package com.master.app.pims.entities.schemas.property;
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
@Table(name = "mst_owner_category", schema="property")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "OwnerCategory.findAll", query = "SELECT g FROM OwnerCategory g")})
public class OwnerCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "owner_category_guid")
    private String ownerCategoryGuid;
    
    @Column(name = "owner_category_code")
    private String ownerCategoryCode;
    
    @Column(name = "owner_category_name_en")
    private String ownerCategoryNameEn;
    
    @Column(name = "owner_category_name_hi")
    private String ownerCategoryNameHi;
    
    @Column(name = "owner_category_name_rl")
    private String ownerCategoryNameRl;
    
    @Column(name = "owner_category_description")
    private String ownerCategoryDesc;
    
    @Column(name = "is_active")
    private Boolean isActive;
    
    @Column(name = "created_by")
    private String createdByGuid;
    
    @Basic(optional = false)
    @Column(name = "created_date", insertable=false,updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
    @Column(name = "created_ip_addr")
    private String createdIpAddr;
    
    @Column(name = "created_mac_addr")
    private String createdMacAddr;
    
    @Column(name = "created_remarks")
    private String createdRemarks;
    
    @Column(name = "modified_by")
    private String modifiedByGuid;
    
    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @Column(name = "modified_ip_addr")
    private String modifiedIpAddr;
    
    @Column(name = "modified_mac_addr")
    private String modifiedMacAddr;
    
    @Column(name = "modified_remarks")
    private String modifedRemarks;

	public OwnerCategory(String ownerCategoryGuid) {
		super();
		this.ownerCategoryGuid = ownerCategoryGuid;
	}

	
}
