/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.com.portal.service;

import gt.com.portal.domain.AuthorizedChannel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author usuario
 */
public interface AuthorizedChannelService {
    
    Page<AuthorizedChannel> getAllAuthorizedChannel(Pageable pageable);
    
    AuthorizedChannel saveAuthorizedChannel(AuthorizedChannel authorizedChannel);
    
    AuthorizedChannel updateAuthorizedChannel(String id, AuthorizedChannel newAuthorizedChannel);
    
    void deleteAuthorizedChannel(String id);
    
    AuthorizedChannel getAuthorizedChannelById(String id);   
    
}
