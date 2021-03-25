/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.com.portal.service;

import gt.com.portal.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author usuario
 */
public interface ProductService {
    
    Page<Product> getAllProduct(Pageable pageable);
    
    Product saveProduct(Product product);
    
    Product updateProduct(String id, Product newProduct);
    
    void deleteProduct(String id);
    
    Product getProductById(String id);      
}
