package com.master.app.pims.login;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepo extends CrudRepository<UserRole, Long>{
    @Query(value = "SELECT row_number,role_guid,role_code,role_name,is_default_role  FROM adm.user_roles(:loginId, :userId, :userGuid, :app)", nativeQuery = true)
	List<UserRole> findAllRoleByLoginId(String loginId,Long userId,String userGuid,String app);
    
    @Query(value = "SELECT trim(section_code)  FROM adm.section_role_map WHERE role_guid = :roleGuid", nativeQuery = true)
	List<String>findAllSectionListByRoleGuid(String roleGuid);
}
