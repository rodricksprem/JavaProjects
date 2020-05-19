package com.bct.weeklystatus.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
 
public class JwtResponse {
  private String accessToken;
  private String type = "Bearer";
  private String username;

  private String name;
  private Collection<? extends GrantedAuthority> authorities;
 
  public JwtResponse(String accessToken,String name, String username, Collection<? extends GrantedAuthority> authorities) {
    this.accessToken = accessToken;
    this.username = username;
    this.authorities = authorities;
    this.name=name;
    this.type="Bearer";
  }

  public String getAccessToken() {
    return accessToken;
  }
 
  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }
 
  public String getTokenType() {
    return type;
  }
 
  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }
 
  public String getUsername() {
    return username;
  }
 
  public void setUsername(String username) {
    this.username = username;
  }
 
  public String getName() {
	    return name;
	  }
	 
	  public void setName(String name) {
	    this.name = name;
	  }
	  
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }
}