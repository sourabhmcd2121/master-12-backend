package com.master.app.pims.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.master.DesignationAppointmentType;

public interface DesignationAppointmentTypeRepository extends JpaRepository<DesignationAppointmentType, String> {
    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM DesignationAppointmentType d WHERE TRIM(UPPER(d.designationAppointmentTypeName)) = TRIM(UPPER(:nameEn)) AND d.designationAppointmentTypeGuid <> :guid")
    boolean existsByNameExcludingGuid(@Param("nameEn") String nameEn, @Param("guid") String guid);
	// For designationAppointmentTypeCode validation
	boolean existsByDesignationAppointmentTypeCodeIgnoreCaseAndDesignationAppointmentTypeGuidNot(String designationAppointmentTypeCode, String designationAppointmentTypeGuid);

	// For designationAppointmentTypeName validation
	boolean existsByDesignationAppointmentTypeNameIgnoreCaseAndDesignationAppointmentTypeGuidNot(String designationAppointmentTypeName, String designationAppointmentTypeGuid);



}

