package com.master.app.pims.sso.janparichay;


public class JanParichayTokenValidResponse {
	  private boolean tokenValid;
	  
	  private String status;
	  
	  public boolean isTokenValid() {
	    return this.tokenValid;
	  }
	  
	  public void setTokenValid(boolean tokenValid) {
	    this.tokenValid = tokenValid;
	  }
	  
	  public String getStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(String status) {
	    this.status = status;
	  }
	}