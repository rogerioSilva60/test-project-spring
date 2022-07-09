package br.com.crud.testproject.product.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.crud.testproject.entities.Products;
import br.com.crud.testproject.repositories.ProductRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProductRepositoryTest {
    
    @Autowired
    ProductRepository repository;

    @Test
    @DisplayName("Must save a product")
    public void saveProductTest() {
        //Scenery
        Products productFake = new Products("Arroz", "Tipo 1", new BigDecimal("5.99"));
        
        //Executation
        Products product = repository.save(productFake);

        //Verification
        assertNotNull(product);
        assertEquals(product.getId(), product.getId());
        assertEquals(product.getName(), product.getName());
        assertEquals(product.getDescription(), product.getDescription());
    }
}
