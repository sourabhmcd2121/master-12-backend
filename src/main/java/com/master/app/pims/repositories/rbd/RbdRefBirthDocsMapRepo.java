package com.master.app.pims.repositories.rbd;

import org.springframework.data.jpa.repository.JpaRepository;

import com.master.app.pims.entities.schemas.rbd.RbdMstDocsCategory;
import com.master.app.pims.entities.schemas.rbd.RbdRefBirthDocsMap;

public interface RbdRefBirthDocsMapRepo extends JpaRepository<RbdRefBirthDocsMap, String>{

}
