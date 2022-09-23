package br.com.crud.testproject.config;

import org.apache.commons.codec.binary.Base64;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Base64Config {
  
  @Bean
  public Base64 base64() {
    return new Base64();
  }
}
