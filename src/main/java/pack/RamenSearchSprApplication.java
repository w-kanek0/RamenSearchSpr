package pack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// warファイルでデプロイする場合はSpringBootServletInitializerを継承する必要あり
@SpringBootApplication
public class RamenSearchSprApplication extends SpringBootServletInitializer {

	// warファイルでデプロイする場合は必要
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RamenSearchSprApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RamenSearchSprApplication.class, args);
	}

}
