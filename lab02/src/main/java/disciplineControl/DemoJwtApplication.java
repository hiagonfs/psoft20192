package disciplineControl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import disciplineControl.filters.TokenFilter;

@SpringBootApplication
public class DemoJwtApplication {

	@Bean
	public FilterRegistrationBean<TokenFilter> filterJwt() {

		FilterRegistrationBean<TokenFilter> filterRB = new FilterRegistrationBean<TokenFilter>();
		filterRB.setFilter(new TokenFilter());
		filterRB.addUrlPatterns("api/auth/usuarios/*");
		return filterRB;

	}

	public static void main(String[] args) {

		SpringApplication.run(DemoJwtApplication.class, args);

	}

}
