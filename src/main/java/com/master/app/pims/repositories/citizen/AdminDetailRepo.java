package com.master.app.pims.repositories.citizen;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.master.app.pims.entities.schemas.citizenmaster.AdminDetail;

public interface AdminDetailRepo extends JpaRepository<AdminDetail, String> {
	
	
	//Optional<AdminDetail> findByAdminDetailGuid(String adminDetailGuid);

}
