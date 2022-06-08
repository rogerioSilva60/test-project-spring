package br.com.crud.testproject.controllers.openapi;

import java.util.List;
import org.springframework.http.ResponseEntity;
import br.com.crud.testproject.controllers.dtos.ProductDto;
import br.com.crud.testproject.controllers.dtos.inputs.ProductInput;
import br.com.crud.testproject.util.Response;
import io.swagger.annotations.*;

public interface ProductOpenApi {

  // @ApiResponses(value = {
  //   @ApiResponse(code = 400, message = "Missing or invalid request body"),
  //   @ApiResponse(code = 500, message = "Internal error")
  // })
  @ApiOperation("get all products")
  ResponseEntity<Response<List<ProductDto>>> getAll();

  @ApiOperation("get by id product")
  ResponseEntity<Response<ProductDto>> getById(long id);
  
  @ApiOperation("save product")
  ResponseEntity<Response<ProductDto>> save(@ApiParam(name = "body", value = "Represents the product") ProductInput input);
  
  @ApiOperation("update by id product")
  ResponseEntity<Response<ProductDto>> update(long id,
  @ApiParam(name = "body", value = "Represents the product") 
  ProductInput input);
  
  @ApiOperation("delete by id product")
  ResponseEntity<Response<?>> delete(long id);

  @ApiOperation("activate by id product")
  ResponseEntity<Response<?>> active(long id);

  @ApiOperation("inactive by id product")
  ResponseEntity<Response<?>> inactive(long id);

}