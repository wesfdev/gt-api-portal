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
public class AuthorizedChannelNotFoundException extends RuntimeException{

    public AuthorizedChannelNotFoundException(String message) {
        super(message);
    }

    public AuthorizedChannelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorizedChannelNotFoundException(Throwable cause) {
        super(cause);
    }
    
}
