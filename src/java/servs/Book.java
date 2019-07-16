/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servs;

import com.fasterxml.jackson.xml.XmlMapper;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Olivier
 */
@XmlRootElement(name = "book")
public class Book implements Serializable, Comparable<Book>, Cloneable {

//    @XmlElement(name = "title")
    private String title;
    private Author author;
//    @XmlElement(name = "publisher")
    private String publisher;


    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public int compareTo(Book o) {
        if (this.title == o.getTitle()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public Book clone() {
        Book b = new Book();
        b.setTitle(this.title);
        b.setAuthor(this.author);
        b.setPublisher(this.publisher);
        return b;
    }

    @Override
    public String toString()  {
        String xml = "";
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xml = xmlMapper.writeValueAsString(this);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
        return xml;
    }
}
