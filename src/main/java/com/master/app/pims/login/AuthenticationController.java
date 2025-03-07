package com.master.app.pims.login;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") 
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

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		UserDetail user = userRepository.findByLoginId(authenticationRequest.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException(
						"User not found with username: " + authenticationRequest.getUsername()));

		if (!userDetailsService.validatePassword(authenticationRequest.getPassword(), user.getPwd(), user.getSalt())) {
			throw new BadCredentialsException("Incorrect username or password");
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);

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
