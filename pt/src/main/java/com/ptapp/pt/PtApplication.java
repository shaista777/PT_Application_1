package com.ptapp.pt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaAuditing
public class PtApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(PtApplication.class, args);
	}


	@Configuration
	@EnableSwagger2
	@EnableConfigurationProperties({
        FileStorageProperties.class
})
	public class Swagger2Config {
		@Bean
		public Docket productApi() {
			return new Docket(DocumentationType.SWAGGER_2).select()
					.apis(RequestHandlerSelectors.basePackage("com.ptapp.pt")).paths(PathSelectors.any())
	                .build()
	                .apiInfo(getApiInformation());
		}
		private ApiInfo getApiInformation() {
	        return new ApiInfoBuilder().title("PT App API")
	                                   .description("Performance Test collection")
	                                   .contact(new Contact(null, null, null))
	                                   .license("Apache 2.0")
	                                   .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
	                                   .version("1.0.0")
	                                   .build();
	    }

	}
	
	@Configuration
	public class WebConfig implements WebMvcConfigurer {

	    @Bean
	    @Description("Thymeleaf template resolver serving HTML 5")
	    public ClassLoaderTemplateResolver templateResolver() {

	        var templateResolver = new ClassLoaderTemplateResolver();

	        templateResolver.setPrefix("templates/");
	        templateResolver.setCacheable(false);
	        templateResolver.setSuffix(".html");
	        templateResolver.setTemplateMode("HTML5");
	        templateResolver.setCharacterEncoding("UTF-8");

	        return templateResolver;
	    }

	    @Bean
	    @Description("Thymeleaf template engine with Spring integration")
	    public SpringTemplateEngine templateEngine() {

	        var templateEngine = new SpringTemplateEngine();
	        templateEngine.setTemplateResolver(templateResolver());

	        return templateEngine;
	    }

	    @Bean
	    @Description("Thymeleaf view resolver")
	    public ViewResolver viewResolver() {

	        var viewResolver = new ThymeleafViewResolver();

	        viewResolver.setTemplateEngine(templateEngine());
	        viewResolver.setCharacterEncoding("UTF-8");

	        return viewResolver;
	    }

	    @Override
	    public void addViewControllers(ViewControllerRegistry registry) {
	        registry.addViewController("/").setViewName("index");
	    }
	}


}
