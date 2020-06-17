package com.Auth.LDAP.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

@Configuration
public class LDAPSecurityConfig extends WebSecurityConfigurerAdapter{

	@Value("${ldap.urls}")
    private String ldapUrls;

    @Value("${ldap.base.dn}")
    private String ldapBaseDn;

    @Value("${ldap.username}")
    private String ldapSecurityPrincipal;

    @Value("${ldap.password}")
    private String ldapPrincipalPassword;

    @Value("${ldap.user.dn.pattern}")
    private String ldapUserDnPattern;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/login**").anonymous()
//                .antMatchers("/resources/**").permitAll()
//                .antMatchers("/assets/**").permitAll()
//                .antMatchers("/").authenticated()
//                .antMatchers("/home").authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll()
//                .and()
//                .csrf()
//                .disable();
    	
    	http
        .authorizeRequests()
        .mvcMatchers("/login").permitAll()
          .anyRequest().authenticated()
          .and()
          .formLogin();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    	auth
//      .ldapAuthentication()
//      .contextSource()
//      .url(ldapUrls)
//      .managerDn(ldapSecurityPrincipal)
//      .managerPassword(ldapPrincipalPassword)
//      .and()
//      .userDnPatterns(ldapUserDnPattern);
////    	auth.ldapAuthentication()
////        //.userDnPatterns("uid={0},ou=sales")
////        //.userSearchBase("ou=sales")
////        //.userSearchFilter("uid={0}")
////   //.groupSearchBase("ou=sales")
////   //.groupSearchFilter("uniqueMember={0}")
////        .contextSource()
////        .url(ldapUrls + ldapBaseDn)
////        .managerDn(ldapSecurityPrincipal)
////        .managerPassword(ldapPrincipalPassword)
////        .and()
////        .passwordCompare()
////        .passwordEncoder(new LdapShaPasswordEncoder())
////   .passwordAttribute("Global12$");
//    }
    
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//    return passwordEncoder;
//    }
    
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.ldapAuthentication()
//            .userDnPatterns(ldapUserDnPattern)
//            .groupSearchBase("ou=sales")
//            .contextSource(contextSource())
//            .passwordCompare()
//            .passwordEncoder(new LdapShaPasswordEncoder())
//            .passwordAttribute("userPassword");      
//    }

    @Bean
    public DefaultSpringSecurityContextSource contextSource() {
            return  new DefaultSpringSecurityContextSource(Arrays.asList("ldap://192.168.10.198:389/"), ldapBaseDn);
    }
//    
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.ldapAuthentication()
	        .userDnPatterns(ldapUserDnPattern)
	        .userSearchBase("ou=sales")
	        .userSearchFilter("uid={0}")
	        .groupSearchBase("ou=sales")
	        //.groupSearchFilter("uniqueMember={0}")
	        .contextSource()
	        .url(ldapUrls+ldapBaseDn)
	        .managerDn(ldapSecurityPrincipal)
	        .managerPassword(ldapPrincipalPassword)
	        .and()
	        .passwordCompare()
	        .passwordEncoder(new LdapShaPasswordEncoder())
	        .passwordAttribute("userPassword");		
	}
    
    
}
