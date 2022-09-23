package br.com.crud.testproject.controllers.openapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

public interface Base64OpenApi {
  
  @ApiOperation("encode to base64")
  ResponseEntity<String> encode(
    @ApiParam(name = "text", value = "Represents the text to encode", example = "Hello World!")
    @RequestBody String text);

  @ApiOperation("decode to base64")
  ResponseEntity<String> decode(
    @ApiParam(name = "key", value = "Represents the encoded key") 
    @RequestParam String key);
}
