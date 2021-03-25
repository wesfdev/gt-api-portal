/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.com.portal.service;

import gt.com.portal.domain.Distributor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author usuario
 */
public interface DistributorService {
    
    Page<Distributor> getAllDistributor(Pageable pageable);
    
    Distributor saveDistributor(Distributor distributor);
    
    Distributor updateDistributor(String id, Distributor newDistributor);
    
    void deleteDistributor(String id);
    
    Distributor getDistributorById(String id);
    
    void uploadFile(MultipartFile file);
}
