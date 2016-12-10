package http.resource.example.service;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;

/**
 * Created by bin.liang on 2016/12/2.
 */
@Path("blog")
public interface BlogService {

//    @GET
//    @Path("{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    Blog getBlog(@PathParam("id") long id);


    /*@Path("img")
    @POST
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    void uploadImg(File img);*/


    @POST
    @Path("attachment")
    @Produces(MediaType.MULTIPART_FORM_DATA)
    String uploadAttachment(@FormParam("file") File file, @FormParam("name")String name);


//    @POST
//    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
//    String addBlog(Blog blog) ;
}
