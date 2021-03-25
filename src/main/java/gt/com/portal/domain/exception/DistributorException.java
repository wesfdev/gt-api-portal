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
public class DistributorException extends RuntimeException{

    public DistributorException(String message) {
        super(message);
    }

    public DistributorException(String message, Throwable cause) {
        super(message, cause);
    }

    public DistributorException(Throwable cause) {
        super(cause);
    }
    
}
