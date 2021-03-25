/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.com.portal.controller;

import gt.com.portal.domain.Distributor;
import gt.com.portal.domain.exception.DistributorException;
import gt.com.portal.domain.exception.DistributorNotFoundException;
import gt.com.portal.service.DistributorService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author usuario
 */
@CrossOrigin
@RestController
@RequestMapping("/v1/distributor")
public class DistributorController {

    private static final Logger LOG = Logger.getLogger(DistributorController.class.getName());

    @Autowired
    private DistributorService service;

    @GetMapping
    public ResponseEntity getAllDistributor(Pageable pageable) {
        try {
            return ResponseEntity.ok(service.getAllDistributor(pageable));
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getDistributorById(@PathVariable("id") String id) {
        try {
            Distributor distributor = service.getDistributorById(id);
            return ResponseEntity.ok(distributor);
        } catch (DistributorNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity saveDistributor(@RequestBody Distributor distributor) {
        try {
            Distributor response = service.saveDistributor(distributor);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateDistributor(@PathVariable("id") String id, @RequestBody Distributor distributor) {
        try {
            Distributor response = service.updateDistributor(id, distributor);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDistributorById(@PathVariable("id") String id) {
        try {
            service.deleteDistributor(id);
            return ResponseEntity.accepted().build();
        } catch (DistributorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
        String message;
        try {
            service.uploadFile(file);
            message = "Uploaded file successfull: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (DistributorException e) {
            message = "Error file: " + file.getOriginalFilename() + " - " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + " - " +  e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }

    }
    
    @PostMapping("/bulk-products/{distributor}")
    public ResponseEntity uploadFileProductByDistributo(@PathVariable("distributor") String distributor,
    @RequestParam("file") MultipartFile file) {
        String message;
        try {
            //service.loadingBulk(distributor, file);
            message = "Uploaded file successfull: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (DistributorException e) {
            message = "Error file: " + file.getOriginalFilename() + " - " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + " - " +  e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }

    }    

}
