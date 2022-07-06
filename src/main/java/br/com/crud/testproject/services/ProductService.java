package br.com.crud.testproject.services;

import java.util.List;

import br.com.crud.testproject.entities.Products;

public interface ProductService  {
    
    List<Products> getAll(boolean isActive);

    Products getById(long id);

    Products save(Products product);

    Products update(long id, Products product);

    void delete(long id);

    void inactive(long id);

    void active(long id);
    
}
