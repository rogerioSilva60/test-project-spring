package br.com.crud.testproject.product.controller;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.crud.testproject.controllers.ProductController;
import br.com.crud.testproject.controllers.assembler.GenericAssembler;
import br.com.crud.testproject.controllers.dtos.ProductDto;
import br.com.crud.testproject.controllers.dtos.inputs.ProductInput;
import br.com.crud.testproject.controllers.dtos.response.GenericResponse;
import br.com.crud.testproject.entities.Products;
import br.com.crud.testproject.services.ProductService;
import br.com.crud.testproject.util.Response;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc
public class ProductControllerTest {
    
    private static final String PRODUCT_API = "/v1/products";
    
    @Autowired
    MockMvc mvc;    
    @MockBean
    ProductService service;
    @MockBean
    GenericAssembler<ProductInput, Products> genericProductInputToProduct;
    @MockBean
    GenericAssembler<Products, ProductDto> genericProductToProductDto;
    @MockBean
    GenericResponse<ProductDto> genericResponse;

    @Test
    @DisplayName("Must successfully create a product")
    public void createProductTest() throws Exception {
        //Scenery
        ProductInput productInputFake = new ProductInput("Arroz", "Tipo 1", new BigDecimal("5.99"));
        Products productFake = new Products(productInputFake.getName(), productInputFake.getDescription(), productInputFake.getPrice());
        Products productSaveFake = new Products(1, productInputFake.getName(), productInputFake.getDescription(), productInputFake.getPrice());
        ProductDto productDtoFake = new ProductDto(productSaveFake.getId(), productSaveFake.getName(), productSaveFake.getDescription(), productSaveFake.getPrice());
        Response<ProductDto> responseProductFake = new Response<>(productDtoFake);

        //Simulating the response when creating the product
        BDDMockito.given(genericProductInputToProduct.toObject(Mockito.any(ProductInput.class), Mockito.any())).willReturn(productFake);
        BDDMockito.given(genericProductToProductDto.toObject(Mockito.any(Products.class), Mockito.any())).willReturn(productDtoFake);
        BDDMockito.given(genericResponse.toObjectResponse(productDtoFake)).willReturn(responseProductFake);        
        BDDMockito.given(service.save(productFake)).willReturn(productSaveFake);
        String json = new ObjectMapper().writeValueAsString(productInputFake);

        //Executation
        //Simulating sending to the controller
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(PRODUCT_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        //Verification
        mvc
            .perform(request)
            .andExpect(status().isCreated())
            .andExpect(jsonPath("data").isNotEmpty())
            .andExpect(jsonPath("data.id").value(productSaveFake.getId()))
            .andExpect(jsonPath("data.name").value(productSaveFake.getName()))
            .andExpect(jsonPath("data.description").value(productSaveFake.getDescription()));
    }
}
