/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.com.portal.controller;

import gt.com.portal.domain.Product;
import gt.com.portal.domain.exception.ProductNotFoundException;
import gt.com.portal.service.ProductService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author usuario
 */
@CrossOrigin
@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private static final Logger LOG = Logger.getLogger(ProductController.class.getName());

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity getAllProduct(Pageable pageable) {
        try {
            return ResponseEntity.ok(service.getAllProduct(pageable));
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getProductById(@PathVariable("id") String id) {
        try {
            Product product = service.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (ProductNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity saveProduct(@RequestBody Product product) {
        try {
            Product response = service.saveProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        try {
            Product response = service.updateProduct(id, product);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }    
    

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProductById(@PathVariable("id") String id) {
        try {
            service.deleteProduct(id);
            return ResponseEntity.accepted().build();
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
