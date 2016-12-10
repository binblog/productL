package service.blog;

import org.apache.commons.io.FileUtils;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by bin.liang on 2016/12/1.
 */
@Path("blog")
public class BlogApi {
    private Logger logger = LoggerFactory.getLogger(BlogApi.class);

    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "hello,client";
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBlog(@PathParam("id") long id) {
        logger.info("getBlog : {}", id);


        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if (id == 1) {
            Blog blog = new Blog();
            blog.setTitle("hello , my id is " + id);
            return Response.ok(blog, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for id: " + id).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addBlog(Blog blog) {
        logger.info("post blog : {}", blog);
        return "post success";
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("id") long id) {
        logger.info("delete id : " + id);

        return "delete success";
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String put(@PathParam("id") long id, Blog blog) {
        logger.info("id : {}, blog : {}", id, blog);

        return "put success";
    }

//    @POST
//    @Path("img")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public String uploadImage(@FormDataParam("file") InputStream fileInputStream) {
//        System.out.println("input : " + fileInputStream);
//
//        return "upload success";
//    }


    @POST
    @Path("attachment")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String uploadAttachment(@FormDataParam("file") InputStream fileInputStream, @FormDataParam("name")String name) {

        // String name = "1_upload.png";
        logger.info("file : {}, name : {}", fileInputStream, name);

        try {
            FileUtils.copyInputStreamToFile(fileInputStream, new File(name));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "upload success";
    }


    @POST
    @Path("img")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public String uploadImage(InputStream fileInputStream) {
        System.out.println("input : " + fileInputStream);


        try {
            FileUtils.copyInputStreamToFile(fileInputStream, new File("updateData"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "upload success";
    }

}