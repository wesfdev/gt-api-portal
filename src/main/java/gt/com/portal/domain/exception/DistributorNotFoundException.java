/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.com.portal.domain.exception;

/**
 *
 * @author usuario
 */
public class DistributorNotFoundException extends RuntimeException{

    public DistributorNotFoundException(String message) {
        super(message);
    }

    public DistributorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DistributorNotFoundException(Throwable cause) {
        super(cause);
    }
    
}
