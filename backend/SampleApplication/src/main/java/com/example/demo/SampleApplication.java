package com.example.demo;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import com.example.demo.*;

@ComponentScan
@SpringBootApplication
public class SampleApplication {

	@Autowired
	MediaRepository mediaRepo;
	@Autowired
	static UserRepository userRepository;

	@Autowired
	static MediaService mediaService;

	public static void main(String[] args) throws Exception {

		SpringApplication.run(SampleApplication.class, args);
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
//		mongoOperation.createCollection(MediaUser.class);
//		mongoOperation.createCollection(MediaFile.class);
		MediaFile m1 = new MediaFile("Earth",new URL("http://static.videogular.com/assets/videos/videogular.mp4"));
		MediaFile m2 = new MediaFile("Bigger Meltdowns",new URL("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4"));
		List<MediaFile> mfile = new ArrayList<MediaFile>();
		mfile.add(m1);
		mfile.add(m2);
//		MediaUser mUser1 = new MediaUser("walle", mfile, "hidden", "ADMIN" );
//		MediaUser mUser2 = new MediaUser("cr7", mfile, "akie", "ADMIN" );
//		MediaUser mUser3 = new MediaUser("evader", mfile, "tusick", "ADMIN" );
//		MediaUser mUser4 = new MediaUser("peeps", mfile, "awp", "ADMIN" );
//		mongoOperation.save(mUser1, "Users");
//		mongoOperation.save(mUser2, "Users");
//		mongoOperation.save(mUser3, "Users");
//		mongoOperation.save(mUser4, "Users");
		MediaUser mUser1 = mongoOperation.findById("walle", MediaUser.class);
		System.out.println(mUser1.toString());
		//System.out.println(mediaService.getUserbyId("cr7").toString());

	}

	@Configuration
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	@EnableWebSecurity
	public static class SecurityConfig extends WebSecurityConfigurerAdapter {

		public UserRepository userRepo;

//		@Bean
//		InMemoryUserDetailsManager userDetailsManager() {
//
//			@SuppressWarnings("deprecation")
//			UserBuilder builder = User.withDefaultPasswordEncoder();
//
//			UserDetails greg = builder.username("greg").password("turnquist").roles("ADMIN").build();
//			UserDetails ollie = builder.username("ollie").password("gierke").roles("USER", "ADMIN").build();
//
//			return new InMemoryUserDetailsManager(greg, ollie);
//		}
		
		@Bean
		InMemoryUserDetailsManager userDetailsManager() {
			ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
			MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
			try {

				List<MediaUser> userList = mongoOperation.findAll(MediaUser.class, "Users");
				@SuppressWarnings("deprecation")
				UserBuilder builder = User.withDefaultPasswordEncoder();
				List<UserDetails> userDetailList = new ArrayList<UserDetails>();
				userList.get(0).toString();
				userList.get(1).toString();
				userList.get(2).toString();
				userList.get(3).toString();
				for (MediaUser mUser : userList) {
					System.out.println(mUser.toString());
					UserDetails user = builder.username(mUser.getUsername()).password(mUser.getPassword())
							.roles(mUser.getRole()).build();
					System.out.println(user);
					userDetailList.add(user);
				}
				return new InMemoryUserDetailsManager(userDetailList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;

		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.httpBasic()
					 .and().authorizeRequests().antMatchers("/**").permitAll()
//					.and().authorizeRequests().antMatchers("/{username}/delete/**").hasRole("ADMIN").and()
//					.authorizeRequests().antMatchers("/{username}/media/**").hasAnyRole("ADMIN", "USER")
					// .and().authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()
					.and().csrf().disable();
		}

		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
		}
	}

}
