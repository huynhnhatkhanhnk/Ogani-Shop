package com.edu;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.edu.entity.Account;
import com.edu.service.AccountService;
import com.edu.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	AccountService accountService;
	@Autowired
	UserService userService;

	@Autowired
	BCryptPasswordEncoder pe;

	// cơ chế mã hoá mật khẩu
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Cung cấp nguồn dữ liệu đăng nhập
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> {
			try {
				Account user = accountService.findById(username);
				  String password = user.getPassword();
				String[] roles = user.getAuthorities().stream().map(er -> er.getRole().getId())
						.collect(Collectors.toList()).toArray(new String[0]);
				return User.withUsername(username).password(password).roles(roles).build();
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(username + "not found!");
			}
		});
		auth.userDetailsService(userService);
	}

	// Phân quyền sử dụng
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable();
		http.authorizeRequests()
		.antMatchers("/order/**").authenticated()
		.antMatchers("/admin/**").hasAnyRole("STAF","DIRE")
		.antMatchers("/rest/authorities").hasRole("DIRE")
		.anyRequest().permitAll();
		
		
		http.formLogin()
		.loginPage("/security/login/form") // Địa chỉ của form
		.loginProcessingUrl("/security/login") //[/login] Nút Login submit form
		.defaultSuccessUrl("/security/login/success",false) // Đăng nhập thành công mới đc chuyển qua (false chuyển qua, còn true là ko)
		.failureUrl("/security/login/error") //nếu sai thì về trang lỗi
		.usernameParameter("username") // [username]
		.passwordParameter("password");//[password]
		
		http.rememberMe()
		.rememberMeParameter("remember");
		
		http.rememberMe()
		.tokenValiditySeconds(86400);
		
		http.exceptionHandling()
		.accessDeniedPage("/security/unauthoried");
		
		http.logout()
		.logoutUrl("/security/logoff")
		.logoutSuccessUrl("/security/logoff/success");
		
		//OAuth2 - Đăng nhập từ mạng xã hội
				http.oauth2Login()
					.loginPage("/security/login/form")
					.defaultSuccessUrl("/security/login/success", true)
					.failureUrl("/security/login/error")
					.authorizationEndpoint()
						.baseUri("/oauth2/authorization"); // đường dẫn bắt đầu cho login mxh
	}
//	//cho phép truy xuất RESTFUL API từ bên ngoài khác các domain
//	@Override
//	public void configure(WebSecurity web )  throws Exception{
//		web.ignoring().antMatchers(HttpMethod.OPTIONS,"/**");
//	}
}


