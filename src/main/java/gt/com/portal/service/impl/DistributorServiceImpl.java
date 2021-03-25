/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.com.portal.service.impl;

import gt.com.portal.service.util.CSVUtil;
import static gt.com.portal.service.util.FieldsUtil.*;
import gt.com.portal.domain.Distributor;
import gt.com.portal.domain.exception.DistributorException;
import gt.com.portal.domain.exception.DistributorNotFoundException;
import gt.com.portal.infrastucture.DistributorRepository;
import gt.com.portal.service.DistributorService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.csv.CSVRecord;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author usuario
 */
public class DistributorServiceImpl implements DistributorService{
    
    @Autowired
    private DistributorRepository repository;

    @Override
    public Page<Distributor> getAllDistributor(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Distributor saveDistributor(Distributor distributor) {
        return repository.save(distributor);
    }

    @Override
    public Distributor updateDistributor(String id, Distributor newDistributor) {
        return repository.findById(id)
           .map(distributor -> {
             distributor.setCode(newDistributor.getCode());
             distributor.setName(newDistributor.getName());
             distributor.setAlertEmail(newDistributor.getAlertEmail());
             distributor.setNotificationEmail(newDistributor.getNotificationEmail());
             return repository.save(distributor);
           })
           .orElseGet(() -> {
             newDistributor.setId(id);
             return repository.save(newDistributor);
           });
    }
    
    @Override
    public void deleteDistributor(String id) {
        Distributor distributor = getDistributorById(id);
        repository.deleteById(distributor.getId());
    }

    @Override
    public Distributor getDistributorById(String id) {
        Optional<Distributor> distributor = repository.findById(id);
        if(distributor.isPresent()){
            return distributor.get();
        }else{
            throw new DistributorNotFoundException("Distributor not found");
        }
    }

    @Override
    public void uploadFile(MultipartFile file) {
        if(CSVUtil.isCSV(file)){
            List<Distributor> distributors = csvToDistributor(file);
            repository.saveAll(distributors);
        }else{
            throw new DistributorException("Invalid format.");
        }
    }
    
    private List<Distributor> csvToDistributor(MultipartFile file) {
        try {
            Iterable<CSVRecord> records = CSVUtil.getRecords(file.getInputStream());
            List<Distributor> distributors = new ArrayList<>();
            
            for (CSVRecord record : records) {
                Distributor distributor = new Distributor();
                distributor.setCode(record.get(CODE));
                distributor.setName(record.get(NAME));
                distributor.setNotificationEmail(record.get(NOTIFICATION_EMAIL));
                distributor.setAlertEmail(record.get(ALERT_EMAIL));
                distributors.add(distributor);
            }            
            return distributors;
        } catch (IOException ex) {
            throw new DistributorException("Fail to save data: " + ex.getMessage(), ex);
        }
    }
    
}
