package com.master.app.pims.login;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    private Long rowNumber;        
    private String roleGuid;        
    private String roleCode;        
    private String roleName;        
    private Boolean isDefaultRole; 
    @Transient
    private List<String> sectionList;
}
