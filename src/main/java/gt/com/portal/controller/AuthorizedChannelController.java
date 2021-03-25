/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.com.portal.controller;

import gt.com.portal.domain.AuthorizedChannel;
import gt.com.portal.domain.exception.AuthorizedChannelNotFoundException;
import gt.com.portal.service.AuthorizedChannelService;
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
@RequestMapping("/v1/channel")
public class AuthorizedChannelController {

    private static final Logger LOG = Logger.getLogger(AuthorizedChannelController.class.getName());

    @Autowired
    private AuthorizedChannelService service;

    @GetMapping
    public ResponseEntity getAllAuthorizedChannel(Pageable pageable) {
        try {
            return ResponseEntity.ok(service.getAllAuthorizedChannel(pageable));
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getAuthorizedChannelById(@PathVariable("id") String id) {
        try {
            AuthorizedChannel authorizedChannel = service.getAuthorizedChannelById(id);
            return ResponseEntity.ok(authorizedChannel);
        } catch (AuthorizedChannelNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity saveAuthorizedChannel(@RequestBody AuthorizedChannel authorizedChannel) {
        try {
            AuthorizedChannel response = service.saveAuthorizedChannel(authorizedChannel);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity updateAuthorizedChannel(@PathVariable("id") String id, 
            @RequestBody AuthorizedChannel authorizedChannel) {
        try {
            AuthorizedChannel response = service.updateAuthorizedChannel(id, authorizedChannel);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }    
    

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAuthorizedChannelById(@PathVariable("id") String id) {
        try {
            service.deleteAuthorizedChannel(id);
            return ResponseEntity.accepted().build();
        } catch (AuthorizedChannelNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
