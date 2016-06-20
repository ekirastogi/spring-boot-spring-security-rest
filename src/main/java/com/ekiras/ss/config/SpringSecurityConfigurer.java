package com.ekiras.ss.config;

import com.ekiras.ss.security.config.RestSecurityConfigurerAdapter;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * @author ekansh
 * @since 30/3/16
 */
@EnableWebSecurity
@Order(1)
public class SpringSecurityConfigurer extends RestSecurityConfigurerAdapter{


}
