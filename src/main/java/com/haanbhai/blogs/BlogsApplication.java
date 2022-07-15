package com.haanbhai.blogs;

import com.haanbhai.blogs.config.AppConstants;
import com.haanbhai.blogs.entities.Role;
import com.haanbhai.blogs.repositiories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogsApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepo roleRepo;
	public static void main(String[] args) {
		SpringApplication.run(BlogsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("worldsEdge"));
		try{
			Role role1 = new Role();
			role1.setRoleId(AppConstants.ADMIN_USER);
			role1.setRole("ADMIN_USER");

			Role role2 = new Role();
			role2.setRoleId(AppConstants.BLOGGER_USER);
			role2.setRole("BLOGGER_USER");

			List<Role> roles = List.of(role1,role2);
			List<Role> result = this.roleRepo.saveAll(roles);
			result.forEach(r->{
				System.out.println(r.getRole());
			});
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
