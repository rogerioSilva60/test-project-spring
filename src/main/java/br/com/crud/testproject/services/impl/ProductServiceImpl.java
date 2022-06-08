package br.com.crud.testproject.services.impl;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.crud.testproject.entities.Products;
import br.com.crud.testproject.repositories.ProductRepository;
import br.com.crud.testproject.services.ProductService;
import br.com.crud.testproject.util.exceptions.BusinessException;
import br.com.crud.testproject.util.exceptions.NotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Products> getAll() {
        List<Products> products = repository.findByIsActiveOrderByNameAsc(true);
        return products;
    }

    @Override
    public Products getByIdAndIsActive(long id, boolean isActive) {
        Products product = repository.findByIdAndIsActive(id, isActive)
        .orElseThrow(
            () -> new NotFoundException(String.format("Not found Product with id (%s)", id )));
        return product;
    }

    @Override
    public Products save(Products product) {
        checkProductIfItExist(product.getName());
        return repository.save(product);
    }

    @Transactional
    @Override
    public Products update(long id, Products product) {
        checkProductIfItExist(product.getName());
        Products productOld = repository.findById(id)
        .orElseThrow(
            () -> new NotFoundException(String.format("Not found Product with id (%s)", id )));;
        productOld.prepare(product);
        return repository.saveAndFlush(productOld);
    }

    @Transactional
    @Override
    public void delete(long id) {   
        Products product = getByIdAndIsActive(id, true);
        repository.delete(product);
    }

    @Transactional
    @Override
    public void inactive(long id) {
        Products product = getByIdAndIsActive(id, true);
        product.setIsActive(false);
        repository.saveAndFlush(product);
    }

    @Transactional
    @Override
    public void active(long id) {
        Products product = getByIdAndIsActive(id, false);
        product.setIsActive(true);
        repository.saveAndFlush(product);
    }

    private void checkProductIfItExist(String name) {
        Products productOld = repository.findByNameIgnoreCase(name).orElse(null);
        if(!Objects.isNull(productOld)) {
            throw new BusinessException("Existing product, please try with another name.");
        }
    }
}
