package com.master.app.pims.login;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.master.app.pims.controller.master.rbd.RbdMasterController;
import com.master.app.pims.helper.HttpSessionHelper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:4200"}) 
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailRepo userRepository;
	
	@Autowired
	private UserRoleRepo userRoleRepo;
	
	private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);


	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
	                                                   HttpServletRequest request) throws Exception {

	    logger.info("Authentication attempt for username: {}", authenticationRequest.getUsername());

	    UserDetail user = userRepository.findByLoginId(authenticationRequest.getUsername())
	            .orElseThrow(() -> {
	                logger.warn("User not found with username: {}", authenticationRequest.getUsername());
	                return new UsernameNotFoundException("User not found with username: " + authenticationRequest.getUsername());
	            });

	    if (!userDetailsService.validatePassword(authenticationRequest.getPassword(), user.getPwd(), user.getSalt())) {
	        logger.warn("Invalid password for username: {}", authenticationRequest.getUsername());
	        throw new BadCredentialsException("Incorrect username or password");
	    }

	    logger.info("Authentication successful for username: {}", authenticationRequest.getUsername());

	    // ✅ Set loginID in session
	    HttpSession session = request.getSession(true); // creates a new session if one doesn't exist
	    session.setAttribute("loginID", user.getLoginId().toString());
	    
		logger.info("| User Login by Login-Id : "+user.getLoginId().toString()+ " || IP-address : "+ HttpSessionHelper.getClientIPAddress(request));


	    logger.info("Session created with ID: {} for user: {}", session.getId(), user.getLoginId());

	    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
	    final String jwt = jwtUtil.generateToken(userDetails);

	    logger.debug("Generated JWT for username {}: {}", authenticationRequest.getUsername(), jwt);

	    return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}



	@GetMapping("/getRoleOfUser")
	public ResponseEntity<?> getRoleOfUser(@RequestHeader("Authorization") String authorizationHeader)
			throws Exception {
		String token = authorizationHeader.substring(7);
		String loginId = jwtUtil.extractUsername(token);
		List<UserRole> userRoles = userRoleRepo.findAllRoleByLoginId(loginId,null,null,null);
		userRoles.forEach(e->e.setSectionList(getSectionVisibleToRole(e.getRoleGuid())));
		return ResponseEntity.ok(userRoles);
	}

	private List<String> getSectionVisibleToRole(String roleGuid) {
		List<String> sectionList = userRoleRepo.findAllSectionListByRoleGuid(roleGuid);
		return sectionList;
	}
}
