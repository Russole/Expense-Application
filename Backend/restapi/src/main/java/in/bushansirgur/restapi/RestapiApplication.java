package in.bushansirgur.restapi;

import org.modelmapper.ModelMapper; // ModelMapper 是一個用於物件映射（Object Mapping）的工具，常用於將 DTO（Data Transfer Object）與實體（Entity）之間進行轉換。
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

// @SpringBootApplication，這是一個 Spring Boot 應用的主要註解，它是由以下三個註解組成：
// @Configuration：表示這個類別是一個 Spring 的設定類，可以在這個類別中定義 Bean，
// 例如：如果 Spring Web 在 pom.xml 或 build.gradle 中被引入，那麼 Spring Boot 會自動加載 Spring MVC，並內建一個 Tomcat 伺服器。。
// @EnableAutoConfiguration：讓 Spring Boot 自動配置應用程式的環境（例如加載 Web 伺服器）。
// @ComponentScan：自動掃描當前 package 下的 @Component、@Service、@Repository 等 Spring Bean，並將其加入 Spring 容器中。
@SpringBootApplication
public class RestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
//		啟動 Spring Boot 應用。
//		啟用自動配置（@EnableAutoConfiguration）。
//		自動掃描 package 下的元件（@ComponentScan）。
//		允許在這個類別中定義 @Bean（@SpringBootConfiguration）。
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//		return args -> {
//			System.out.println("註冊的 Spring Beans:");
//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			for (String beanName : beanNames) {
//				System.out.println(beanName);
//			}
//		};
//	}
}
