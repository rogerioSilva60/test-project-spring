package br.com.crud.testproject.controllers;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.crud.testproject.controllers.openapi.Base64OpenApi;
import io.swagger.annotations.Api;

@Api(tags = "Base64")
@RestController
@RequestMapping("v1/base64")
public class Base64Controller implements Base64OpenApi {
  
  @Autowired
  private Base64 base64;

  @PostMapping("/encode")
  @ResponseStatus(HttpStatus.CREATED)
  @Override
  public ResponseEntity<String> encode(@RequestBody(required = true) String text) {
    String encode = new String(base64.encode(text.getBytes()));
    return ResponseEntity.status(HttpStatus.CREATED).body(encode);
  }

  @GetMapping("/decode")
  @Override
  public ResponseEntity<String> decode(@RequestParam(name = "key", required = true) String key) {
    String decode = new String(base64.decode(key.getBytes()));
    return ResponseEntity.ok(decode);
  }
}
