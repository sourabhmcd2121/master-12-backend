//package com.master.app.pims.repositories;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.master.app.pims.entities.commonmaster.ApplicationMaster;
//
//public interface ApplicationMasterRepository extends JpaRepository<ApplicationMaster, String> {
//	//Unique ApplicationMasterCode
//	 boolean existsByApplicationMasterCodeIgnoreCaseAndApplicationMasterGuidNot(String applicationMasterCode, String applicationMasterGuid);
//
//	 //unique ApplicationMasterName
//	 boolean existsByApplicationMasterNameIgnoreCaseAndApplicationMasterGuidNot(String applicationMasterName, String applicationMasterGuid);
//
//	 
//	 //unique ApplicationMasterIp4
//	 boolean existsByApplicationMasterIp4IgnoreCaseAndApplicationMasterGuidNot(String applicationMasterIp4, String applicationMasterGuid);
//
//	//unique ApplicationMasterUrl
//	 boolean existsByApplicationMasterUrlIgnoreCaseAndApplicationMasterGuidNot(String applicationMasterUrl, String applicationMasterGuid);
//
//
//}
