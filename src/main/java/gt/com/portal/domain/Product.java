/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.com.portal.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;

/**
 *
 * @author usuario
 */
public class Product implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    private String code;
    private String description;
    private double amount;

    public Product() {
    }

    public Product(String code, String description, double amount) {
        this.code = code;
        this.description = description;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
