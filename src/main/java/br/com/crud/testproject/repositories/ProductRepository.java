package br.com.crud.testproject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.crud.testproject.entities.Products;

public interface ProductRepository extends JpaRepository<Products, Long>{

    List<Products> findByIsActiveOrderByNameAsc(boolean isActive);

    Optional<Products> findByIdAndIsActive(long id, boolean isActive);

    Optional<Products> findByNameIgnoreCase(String name);
    
}
