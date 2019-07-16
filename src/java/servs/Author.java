/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Olivier
 */
@XmlRootElement(name = "author")
public class Author {
    
//    @XmlElement(name = "firstName")
    private String fn;
//    @XmlElement(name = "lastName")
    private String ln;
    
    public Author(){
        
    }

    /**
     * @return the fn
     */
    public String getFn() {
        return fn;
    }

    /**
     * @param fn the fn to set
     */
    public void setFn(String fn) {
        this.fn = fn;
    }

    /**
     * @return the ln
     */
    public String getLn() {
        return ln;
    }

    /**
     * @param ln the ln to set
     */
    public void setLn(String ln) {
        this.ln = ln;
    }
    
    
}
