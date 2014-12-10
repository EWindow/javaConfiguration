package com.priyank.sample.bean;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.priyank.sample.domain.Employee;

public class CustomUserBean implements UserDetails {

	// Filed used by UserDetails
	private Collection<? extends GrantedAuthority> grantedAuthorities;
	private String password;
	private String username;
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialsNonExpired;
	private boolean isEnabled;

	public CustomUserBean(
			 String username, String password, boolean isAccountNonExpired,
			boolean isAccountNonLocked, boolean isCredentialsNonExpired,
			boolean isEnabled, Collection<? extends GrantedAuthority> grantedAuthorities, Employee employee) {
		super();
		this.grantedAuthorities = grantedAuthorities;
		this.password = password;
		this.username = username;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
		this.employee = employee;
	}

	// Field used for custom purpose
	private Employee employee;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	public Employee getEmployee() {
		return employee;
	}

}
