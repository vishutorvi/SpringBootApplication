package uttara.com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages={"uttara.com.spring"})
@EnableWebMvc
@EnableJpaAuditing
public class BikesStoreApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(BikesStoreApplication.class, args);
		
		for(String name:ctx.getBeanDefinitionNames()) {
			System.out.println(name);
		}
	}
}
