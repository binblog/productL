package http.resource.example.service;

import http.resource.example.domain.Blog;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by bin.liang on 2016/12/2.
 */
@Path("blog")
public interface BlogService {

    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Blog getBlog(@PathParam("id") long id);


    /*@Path("img")
    @POST
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    void uploadImg(File img);*/


//    @POST
//    @Path("attachment")
//    @Produces(MediaType.MULTIPART_FORM_DATA)
//    String uploadAttachment(@FormParam("file") File file, @FormParam("name")String name);


//    @POST
//    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
//    String addBlog(Blog blog) ;
}
