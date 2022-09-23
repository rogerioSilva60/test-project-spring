package br.com.crud.testproject.config;

import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
  @Bean
  public Docket docket(){
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("br.com.crud.testproject"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo())
            .globalResponses(HttpMethod.GET, globalResponse)
            .globalResponses(HttpMethod.PUT, globalResponse)
            .globalResponses(HttpMethod.POST, globalResponse)
            .globalResponses(HttpMethod.DELETE, globalResponse)
            .globalResponses(HttpMethod.PATCH, globalResponse)
            .useDefaultResponseMessages(false)
            .tags(
              new Tag("Products", "Products"),
              new Tag("Base64", "Base64")
            );
  }

  private ApiInfo apiInfo(){
    return new ApiInfoBuilder()
            .title("TestProject API")
            .description("Spring Boot for system TestProject")
            .version("1.0")
            .license("Apache License Version 2.0")
            .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
            .contact(contact())
            .build();
  }

  private Contact contact(){
    return new Contact(
            "TestProject",
            "https://www.testproject.com.br/",
            "rogeriosilva60@gmail.com"
    );
  }

  final List<Response> globalResponse = Arrays.asList(
    new ResponseBuilder()
    .code("400")
    .description("Missing or invalid request body")
    .build(),
    new ResponseBuilder()
    .code("500")
    .description("Internal error")
    .build()
  );
}
