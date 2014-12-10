package com.priyank.sample.initializer;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import com.priyank.sample.configuration.MultiHttpSecurityConfig;

public class SecurityWebApplicationInitializer extends
		AbstractSecurityWebApplicationInitializer {
	public SecurityWebApplicationInitializer() {
		super(MultiHttpSecurityConfig.class);
	}
}
