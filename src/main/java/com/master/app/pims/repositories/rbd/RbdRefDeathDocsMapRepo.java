package com.master.app.pims.repositories.rbd;

import org.springframework.data.jpa.repository.JpaRepository;

import com.master.app.pims.entities.schemas.rbd.RbdRefBirthDocsMap;
import com.master.app.pims.entities.schemas.rbd.RbdRefDeathDocsMap;

public interface RbdRefDeathDocsMapRepo extends JpaRepository<RbdRefDeathDocsMap,String>{
	                                                          
}
