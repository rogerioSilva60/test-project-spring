package br.com.crud.testproject.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.crud.testproject.entities.Products;
import br.com.crud.testproject.repositories.ProductRepository;
import br.com.crud.testproject.services.ProductService;
import br.com.crud.testproject.services.impl.ProductServiceImpl;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {
    
    ProductService service;

    @MockBean
    ProductRepository repository;

    //Runs before each test method
    @BeforeEach
    public void setUp(){
        this.service = new ProductServiceImpl(repository);
    }

    @Test
    @DisplayName("Must save a product")
    public void saveProductTest() {
        //Scenery
        Products productFake = new Products("Arroz", "Tipo 1", new BigDecimal("5.99"));
        Products productSaveFake = new Products(1, productFake.getName(), productFake.getDescription(), productFake.getPrice()); 
    
        //Simulating the response when creating the product
        Mockito.when(repository.findByNameIgnoreCase(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(repository.save(Mockito.any(Products.class))).thenReturn(productSaveFake);
    
        //Executation
        Products product = service.save(productFake);

        //Verification
        assertNotNull(product);
        assertEquals(productSaveFake.getId(), product.getId());
        assertEquals(productSaveFake.getName(), product.getName());
        assertEquals(productSaveFake.getDescription(), product.getDescription());
    }
}
