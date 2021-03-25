/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.com.portal.infrastucture;

import gt.com.portal.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author usuario
 */
public interface ProductRepository extends MongoRepository<Product, String>{
    
}
