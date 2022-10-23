package br.org.serratec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.org.serratec.model.Cliente;
import br.org.serratec.model.Endereco;
import br.org.serratec.model.Pedido;
import br.org.serratec.model.Produto;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2) 
		.select()
		.apis(RequestHandlerSelectors.basePackage("br.org.serratec.controller"))
		.paths(PathSelectors.any())
		.build()
		.ignoredParameterTypes(Cliente.class, Endereco.class, Pedido.class, Produto.class)
		.apiInfo(apiInfo());
	}
		
		public ApiInfo apiInfo() {
			ApiInfo apiInfo = new ApiInfoBuilder()
					.title("PROJETO API RESTFUL GRUPO 3")
					.description("Essa é uma API de ECommerce, desenvolvida pelos alunos: Amanda Katarina Casadem, Breno Rick Espíndola Sanglar, Bruna Ferreira Machado, Gabriel Almeida Barrio Nuevo, Joao Pedro Chocron e Yuri Ramos de Oliveira")
					.license("Apache License 2.0")
					.licenseUrl("http://www.apache.org/license")
					.termsOfServiceUrl("/service.html")
					.version("1.0")
					.contact(new Contact("Grupo 3", null, ""))
					.build();
			return apiInfo;
							
		}
		
}
