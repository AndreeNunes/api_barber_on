package br.com.api.barberon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppBarberOnApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppBarberOnApplication.class, args);
	}
	
	@Bean
    public FilterRegistrationBean<CORSFilter> corsFilterRegistration() {
        FilterRegistrationBean<CORSFilter> registrationBean =
                new FilterRegistrationBean<CORSFilter>(new CORSFilter());
        registrationBean.setName("CORS Filter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }

}
