package com.haanbhai.blogs;

import com.haanbhai.blogs.repositiories.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogsApplicationTests {

	@Autowired
	private UserRepo userRepo;
	@Test
	void contextLoads() {
	}

	@Test
	public void repoTest()
	{
		String className = this.userRepo.getClass().getName();
		String packageName = this.userRepo.getClass().getPackageName();
		System.out.println(className);
		System.out.println(packageName);
	}

}
