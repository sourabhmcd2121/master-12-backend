package com.master.app.pims.repositories.master;

import org.springframework.data.jpa.repository.JpaRepository;


import com.master.app.pims.entities.schemas.master.OrgRadius;

public interface OrgRadiusRepository extends JpaRepository<OrgRadius, String>{
	
}
