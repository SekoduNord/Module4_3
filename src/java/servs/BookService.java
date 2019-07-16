/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servs;

import com.fasterxml.jackson.xml.XmlMapper;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * REST Web Service
 *
 * @author Olivier
 */
@Path("/BookServices")
public class BookService {

    private Book b = new Book();

    public BookService() {
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/getBook")
    public Response getBook() throws IOException, JAXBException {
            //TODO return proper representation object
            if (b.getTitle() == null) {
                b.setTitle("Original Title");
                Author a = new Author();
                a.setFn("First Name");
                a.setLn("Last Name");
                b.setAuthor(a);
                b.setPublisher("Original Publisher");
            }
            
            StringWriter sw = new StringWriter();
            JAXBContext jaxBContext = JAXBContext.newInstance(Book.class);
            Marshaller marshaller = jaxBContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(b, sw);
            String body = sw.toString();
            return Response.ok(body, MediaType.APPLICATION_XML).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/putBook")
    public String putBook(File file) throws JAXBException {
            JAXBContext jaxBContext = JAXBContext.newInstance(Book.class);
            Unmarshaller unmarshaller = jaxBContext.createUnmarshaller();
            Book bb = (Book) unmarshaller.unmarshal(file);           
            this.b = bb.clone();
            
        return b.getAuthor().getFn() + " " + b.getAuthor().getLn();
    }
}

//curl -X GET http://localhost:8084/Module4Activity3/webresources/BookServices/getBook
//curl -X POST -d "<?xml version=/"1.0/" encoding="UTF-8" standalone=/"yes/"?><book><author><fn>First Name</fn><ln>Last Name</ln></author><publisher>Original Publisher</publisher><title>Original Title</title></book>" -H "Content-Type: application/xml" http://localhost:8084/Module4Activity3/webresources/BookServices/putBook
