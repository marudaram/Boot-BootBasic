package com.simple.basic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.simple.basic.controller.HomeController;
import com.simple.basic.util.UserAuthHandler;
import com.simple.basic.util.UserSuccessHandler;



@Configuration //개별적인 스프링 빈 설정 파일
public class WebConfig implements WebMvcConfigurer{

	//빈을 보관하고 있는 장소 (스프링 컨테이너)
//	@Autowired
//	ApplicationContext applicationContext;
//	
//	//properties파일에 선언된 변수를 바로 참조
//	@Value("${server.port}")
//	String port;
//	
//	
//	@Bean //해당 메서드 실행하게 됨
//	public void test() {
//		
//		TestBean t = applicationContext.getBean(TestBean.class);
//		System.out.println(t);
//		
//		HomeController h = applicationContext.getBean(HomeController.class);
//		System.out.println(h);
//		
//		int c = applicationContext.getBeanDefinitionCount();
//		System.out.println("빈의개수:" + c);
//		
//		System.out.println("properties에 선언된 값: " + port);
//	}
//	
//	// 반환은 해당 클래스명, 변수명은 보통 동일하게 써준다
//	@Bean
//	public TestBean testBean() {
//		return new TestBean(); //빈으로 등록
//	}
	
	//프리핸들러
	@Bean
	public UserAuthHandler userAuthHandler() {
		
		return new UserAuthHandler(); //반환된 값을 스프링의 빈으로 등록
	}
	
	//포스트핸들러
	@Bean
	public UserSuccessHandler userSuccessHandler() {
		return new UserSuccessHandler();
	}
	

	//WebMvcConfigurer 클래스가 제공해주는 인터셉터 추가함수
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(userAuthHandler())
				.addPathPatterns("/user/*") //패스경로 포함
				.excludePathPatterns("/user/login"); //패스경로 제외
				
		//경로별로 인터셉터를 다르게 등록..
		registry.addInterceptor(userSuccessHandler())
				.addPathPatterns("/user/*");
				
		
	}
	
	
	
	
}
