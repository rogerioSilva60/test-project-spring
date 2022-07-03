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
    public List<Products> getAll(boolean isActive) {
        List<Products> products = repository.findByIsActiveOrderByNameAsc(isActive);
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
        return repository.findById(id)
        .map(p -> {
            p.setDescription(product.getDescription());
            p.setName(product.getName());
            return repository.saveAndFlush(p);
        })
        .orElseThrow(
            () -> new NotFoundException(String.format("Not found Product with id (%s)", id )));
    }

    @Transactional
    @Override
    public void delete(long id) {   
        Products product = getByIdAndIsActive(id, false);
        repository.delete(product);
    }

    @Transactional
    @Override
    public void inactive(long id) {
        productActivation(id, true, false);
    }

    @Transactional
    @Override
    public void active(long id) {
        productActivation(id, false, true);
    }

    private Products productActivation(Long id, boolean isActive, boolean activate) {
        return repository.findByIdAndIsActive(id, isActive)
        .map(p -> {
            p.setIsActive(activate);
            return repository.saveAndFlush(p);
        })
        .orElseThrow(
            () -> new NotFoundException(String.format("Not found Product with id (%s) in %s", id, (isActive ? "active" : "inactive"))));
    }

    private void checkProductIfItExist(String name) {
        Products productOld = repository.findByNameIgnoreCase(name).orElse(null);
        if(!Objects.isNull(productOld)) {
            throw new BusinessException("Existing product, please try with another name.");
        }
    }
}
