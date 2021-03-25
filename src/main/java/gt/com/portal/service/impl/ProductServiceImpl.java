/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.com.portal.service.impl;

import gt.com.portal.domain.Product;
import gt.com.portal.domain.exception.ProductNotFoundException;
import gt.com.portal.infrastucture.ProductRepository;
import gt.com.portal.service.ProductService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author usuario
 */
public class ProductServiceImpl implements ProductService{
    
    @Autowired
    private ProductRepository repository;

    @Override
    public Page<Product> getAllProduct(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public Product updateProduct(String id, Product newProduct) {
        return repository.findById(id)
           .map(product -> {
             product.setCode(newProduct.getCode());
             product.setDescription(newProduct.getDescription());
             product.setAmount(newProduct.getAmount());
             return repository.save(product);
           })
           .orElseGet(() -> {
             newProduct.setId(id);
             return repository.save(newProduct);
           });
    }

    @Override
    public void deleteProduct(String id) {
        Product product = getProductById(id);
        repository.deleteById(product.getId());
    }

    @Override
    public Product getProductById(String id) {
        Optional<Product> product = repository.findById(id);
        if(product.isPresent()){
            return product.get();
        }else{
            throw new ProductNotFoundException("Product not found");
        }
    }
    
}
