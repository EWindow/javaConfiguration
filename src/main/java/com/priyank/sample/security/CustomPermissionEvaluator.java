package com.priyank.sample.security;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.priyank.sample.bean.CustomUserBean;
import com.priyank.sample.domain.Employee;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

	@Override
	public boolean hasPermission(Authentication authentication,
			Object targetDomainObject, Object permission) {
		if (authentication != null && permission instanceof String) {
			if (authentication.getPrincipal() instanceof CustomUserBean) {
				CustomUserBean user = (CustomUserBean) authentication
						.getPrincipal();
				if (targetDomainObject instanceof Employee) {
					Employee emp = (Employee) targetDomainObject;
					if (emp.getCreatedBy() != user.getEmployee().getId())
						return false;
					else
						return true;
				}
				return true;
			} else {
				if (authentication.getAuthorities().contains(
						new SimpleGrantedAuthority("ROLE_ADMIN"))) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication,
			Serializable targetId, String targetType, Object permission) {
		throw new RuntimeException(
				"Id and Class permissions are not supperted by this application");
	}
}
