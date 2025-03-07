package com.master.app.pims.login;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepo extends JpaRepository<UserDetail, String>{
	Optional<UserDetail> findByLoginId(String loginId);
	
}
