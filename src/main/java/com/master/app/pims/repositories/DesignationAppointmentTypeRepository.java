package com.master.app.pims.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.master.DesignationAppointmentType;

public interface DesignationAppointmentTypeRepository extends JpaRepository<DesignationAppointmentType, String> {
   


}

