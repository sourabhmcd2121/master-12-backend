package com.master.app.pims.repositories.rbd;

import org.springframework.data.jpa.repository.JpaRepository;

import com.master.app.pims.entities.schemas.rbd.RbdRefDocsMap;
import com.master.app.pims.entities.schemas.rbd.RbdRefRegistrationNumber;

public interface RbdRefDocsMapRepo extends JpaRepository<RbdRefDocsMap, String>{

}
