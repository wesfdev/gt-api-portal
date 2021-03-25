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
public class Distributor  implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    private String name;
    private String code;
    private String notificationEmail;
    private String alertEmail;

    public Distributor() {
    }

    public Distributor(String name, String code, String email) {
        this.name = name;
        this.code = code;
        this.notificationEmail = email;
        this.alertEmail = email;
    }
    

    public Distributor(String name, String code, String notificationEmail, String alertEmail) {
        this.name = name;
        this.code = code;
        this.notificationEmail = notificationEmail;
        this.alertEmail = alertEmail;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNotificationEmail() {
        return notificationEmail;
    }

    public void setNotificationEmail(String notificationEmail) {
        this.notificationEmail = notificationEmail;
    }

    public String getAlertEmail() {
        return alertEmail;
    }

    public void setAlertEmail(String alertEmail) {
        this.alertEmail = alertEmail;
    }
    
    

    @Override
    public String toString() {
        return "Distributor{" + "id=" + id + ", name=" + name + ", code=" + code + ", notificationEmail=" + notificationEmail + ", alertEmail=" + alertEmail + '}';
    }
    
}
