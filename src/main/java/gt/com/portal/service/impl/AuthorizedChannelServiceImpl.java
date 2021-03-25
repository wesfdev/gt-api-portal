/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.com.portal.service.impl;

import gt.com.portal.domain.AuthorizedChannel;
import gt.com.portal.domain.exception.AuthorizedChannelNotFoundException;
import gt.com.portal.infrastucture.AuthorizedChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import gt.com.portal.service.AuthorizedChannelService;
import java.util.Optional;

/**
 *
 * @author usuario
 */
public class AuthorizedChannelServiceImpl implements AuthorizedChannelService {
    
    @Autowired
    private AuthorizedChannelRepository repository;

    @Override
    public Page<AuthorizedChannel> getAllAuthorizedChannel(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public AuthorizedChannel saveAuthorizedChannel(AuthorizedChannel authorizedChannel) {
        return repository.save(authorizedChannel);
    }

    @Override
    public AuthorizedChannel updateAuthorizedChannel(String id, AuthorizedChannel newAuthorizedChannel) {
        return repository.findById(id)
           .map(authorizedChannel -> {
             authorizedChannel.setCode(newAuthorizedChannel.getCode());
             authorizedChannel.setName(newAuthorizedChannel.getName());
             return repository.save(authorizedChannel);
           })
           .orElseGet(() -> {
             newAuthorizedChannel.setId(id);
             return repository.save(newAuthorizedChannel);
           });
    }

    @Override
    public void deleteAuthorizedChannel(String id) {
        AuthorizedChannel authorizedChannel = getAuthorizedChannelById(id);
        repository.deleteById(authorizedChannel.getId());
    }

    @Override
    public AuthorizedChannel getAuthorizedChannelById(String id) {
        Optional<AuthorizedChannel> authorizedChannel = repository.findById(id);
        if(authorizedChannel.isPresent()){
            return authorizedChannel.get();
        }else{
            throw new AuthorizedChannelNotFoundException("AuthorizedChannel not found");
        }
    }

}
