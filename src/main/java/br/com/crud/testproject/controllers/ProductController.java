package br.com.crud.testproject.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.crud.testproject.controllers.assembler.GenericAssembler;
import br.com.crud.testproject.controllers.dtos.ProductDto;
import br.com.crud.testproject.controllers.dtos.inputs.ProductInput;
import br.com.crud.testproject.controllers.dtos.response.GenericResponse;
import br.com.crud.testproject.controllers.openapi.ProductOpenApi;
import br.com.crud.testproject.entities.Products;
import br.com.crud.testproject.services.ProductService;
import br.com.crud.testproject.util.Response;
import io.swagger.annotations.Api;

@Api(tags = "Products")
@RestController
@RequestMapping("v1/products")
public class ProductController implements ProductOpenApi {

  private GenericAssembler<Products, ProductDto> genericProductToProductDto;
  private GenericAssembler<ProductInput, Products> genericProductInputToProduct;
  private GenericResponse<ProductDto> genericResponse;
  private ProductService service;

  public ProductController(GenericAssembler<Products, ProductDto> genericProductToProductDto,
    GenericAssembler<ProductInput, Products> genericProductInputToProduct,
    GenericResponse<ProductDto> genericResponse, ProductService service) {
    this.genericProductToProductDto = genericProductToProductDto;
    this.genericProductInputToProduct = genericProductInputToProduct;
    this.genericResponse = genericResponse;
    this.service = service;
  }

  @GetMapping
  @Override
  public ResponseEntity<Response<List<ProductDto>>> getAll(
    @RequestParam(value = "isActive", defaultValue = "true") boolean isActive
  ) {
    List<Products> products = service.getAll(isActive);
    List<ProductDto> list = genericProductToProductDto.toCollection(products, ProductDto.class);
    return ResponseEntity.ok(genericResponse.toColectionResponse(list));
  }

  @GetMapping("{id}")
  @Override
  public ResponseEntity<Response<ProductDto>> getById(@PathVariable long id) {
    Products product = service.getById(id);
    var productDto = genericProductToProductDto.toObject(product, ProductDto.class);
    return ResponseEntity.ok(genericResponse.toObjectResponse(productDto));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Override
  public ResponseEntity<Response<ProductDto>> save(@RequestBody @Valid ProductInput input) {
    var product = genericProductInputToProduct.toObject(input, Products.class);
    var productDto = genericProductToProductDto.toObject(service.save(product), ProductDto.class);
    return ResponseEntity.status(HttpStatus.CREATED).body(genericResponse.toObjectResponse(productDto));
  }

  @PutMapping("{id}")
  @Override
  public ResponseEntity<Response<ProductDto>> update(@PathVariable long id, @RequestBody ProductInput input) {
    var product = genericProductInputToProduct.toObject(input, Products.class);
    var productDto = genericProductToProductDto.toObject(service.update(id, product), ProductDto.class);
    return ResponseEntity.ok(genericResponse.toObjectResponse(productDto));
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Override
  public ResponseEntity<Response<?>> delete(@PathVariable long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("{id}/active")
  @Override
  public ResponseEntity<Response<?>> active(@PathVariable long id) {
    service.active(id);
    return ResponseEntity.ok().build();
  }

  @PatchMapping("{id}/inactive")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Override
  public ResponseEntity<Response<?>> inactive(@PathVariable long id) {
    service.inactive(id);
    return ResponseEntity.noContent().build();
  } 

}
