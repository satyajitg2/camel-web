package com.web.camel.camelweb;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class CamelWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelWebApplication.class, args);
	}

/*

	@Controller
	class SwaggerUIConfig {
		@RequestMapping("/swagger-ui")
		public String redirectToUi() {
			return "redirect:/webjars/swagger-ui/index.html?url=/api-doc&validatorUrl=";
		}
	}
*/

/*
	//This is needed is CamelHttpTransportServet is not registered automatically.
	@Bean
	ServletRegistrationBean servletRegistrationBean() {
		ServletRegistrationBean servlet = new ServletRegistrationBean
				(new CamelHttpTransportServlet(), "/camel/*");
		servlet.setName("CamelServlet");
		return servlet;
	}

 */
}
