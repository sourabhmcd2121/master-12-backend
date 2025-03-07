package com.master.app.pims.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class AuthenticationRequest {
	 private String username;
	 private String password;
	    
}
