package com.ecommerceJee.demo.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.ecommerceJee.demo.service.IUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private IUserService userService;

//	@Autowired
//	private IMyActionService actionService;

	@Override
	// L'authentification
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}

	// Pour que le bean BCryptPasswordEncoder soit injectable
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

	@Override
	// L'authorisation
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll().//http://localhost:8080/ est accéssible à tout le monde
		antMatchers("/login").permitAll().//http://localhost:8080/login est accéssible à tout le monde
		antMatchers("/article/**").hasAnyAuthority("ADMIN", "CLIENT").
		//http://localhost:8080/login est accéssible 
		//aux utilisateurs qui ont le role ADMIN ou bien CLIENT
		antMatchers("/panier/**").hasAuthority("ADMIN").
		//http://localhost:8080/admin est accéssible 
		//aux utilisateurs qui ont le role ADMIN 
		antMatchers("/user/**").hasAuthority("ADMIN").
		antMatchers("/role/**").hasAuthority("ADMIN").

		//http://localhost:8080/client est accéssible 
		//aux utilisateurs qui ont le role CLIENT 
		anyRequest().authenticated().
		//Cross Site request Forgery
		and().csrf().disable()
		.formLogin().loginPage("/login").
		failureUrl("/login?error=true").
		defaultSuccessUrl("/index")
		.usernameParameter("username").passwordParameter("password").
		and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).
		logoutSuccessUrl("/").and()
	    .exceptionHandling().accessDeniedPage("/access-denied");
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	//
	// http.authorizeRequests().antMatchers("/").permitAll();
	// http.authorizeRequests().antMatchers("/login").permitAll();
	//
	// List<MyActionVo> list = actionService.getAll();
	//
	// for (MyActionVo vo : list) {
	// http.authorizeRequests().antMatchers(vo.getAction()).hasAnyAuthority(toTab(vo.getRoles()));
	//
	// }
	//
	// http.authorizeRequests().anyRequest().authenticated().and().csrf().disable().formLogin().loginPage("/login")
	// .failureUrl("/login?error=true").defaultSuccessUrl("/welcome").usernameParameter("username")
	// .passwordParameter("password").and().logout().logoutRequestMatcher(new
	// AntPathRequestMatcher("/logout"))
	// .logoutSuccessUrl("/").and().exceptionHandling().accessDeniedPage("/access-denied");
	// }
	//
	// private String[] toTab(List<RoleVo> roles) {
	// String[] tab = new String[roles.size()];
	// int i = 0;
	// for (RoleVo roleVo : roles) {
	// tab[i] = roleVo.getRole();
	// i++;
	// }
	// return tab;
	// }

//	
//	public void init() {
//		
//		boolean enabled=true;
//		boolean accountNonExpired=true;
//		boolean credentialsNonExpired=true;
//		boolean accountNonLocked=true;
//		
//		
//		userService.cleanDataBase();
//		userService.save(new RoleVo("ADMIN"));
//		userService.save(new RoleVo("CLIENT"));
//
//		RoleVo roleAdmin = userService.getRoleByName("ADMIN");
//		RoleVo roleClient = userService.getRoleByName("CLIENT");
//
//		UserVo admin1 = new UserVo("admin1", "admin1", Arrays.asList(roleAdmin),enabled,accountNonExpired,credentialsNonExpired,accountNonLocked);
//		UserVo client1 = new UserVo("client1", "client1", Arrays.asList(roleClient),enabled,accountNonExpired,credentialsNonExpired,accountNonLocked);
//		userService.save(admin1);
//		userService.save(client1);
//
//		MyActionVo adminAction = new MyActionVo("/ADMIN/**", Arrays.asList(roleAdmin));
//		MyActionVo clientAction = new MyActionVo("/CLIENT/**", Arrays.asList(roleClient));
//		actionService.save(adminAction);
//		actionService.save(clientAction);
//	}

	@Override
	public void run(String... args) throws Exception {
	/*
		boolean enabled=true;
		boolean accountNonExpired=true;
		boolean credentialsNonExpired=true;
		boolean accountNonLocked=true;
		
		
		//userService.cleanDataBase();
		userService.save(new RoleVo("ADMIN"));
		userService.save(new RoleVo("CLIENT"));

		RoleVo roleAdmin = userService.getRoleByName("ADMIN");
		RoleVo roleClient = userService.getRoleByName("CLIENT");

		UserVo admin1 = new UserVo("admin1", "admin1", Arrays.asList(roleAdmin),enabled,accountNonExpired,credentialsNonExpired,accountNonLocked);
		UserVo client1 = new UserVo("client1", "client1", Arrays.asList(roleClient),enabled,accountNonExpired,credentialsNonExpired,accountNonLocked);
		userService.save(admin1);
		userService.save(client1);

		MyActionVo adminAction = new MyActionVo("/ADMIN/**", Arrays.asList(roleAdmin));
		MyActionVo clientAction = new MyActionVo("/CLIENT/**", Arrays.asList(roleClient));
		actionService.save(adminAction);
		actionService.save(clientAction);
		*/
	}

}
