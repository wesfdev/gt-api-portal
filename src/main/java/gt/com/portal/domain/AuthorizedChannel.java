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
public class AuthorizedChannel  implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    private String code;
    private String name;

    public AuthorizedChannel() {
    }

    public AuthorizedChannel(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
