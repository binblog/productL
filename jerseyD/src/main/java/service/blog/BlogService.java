package service.blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by bin.liang on 2016/12/1.
 */
@Path("blog")
public class BlogService {
    private Logger logger = LoggerFactory.getLogger(BlogService.class);

    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "hello,client";
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Blog getBlog(@PathParam("id") long id) {
        System.out.println("id is : " + id);

        Blog blog = new Blog();
        blog.setTitle("id is " + id);
        return blog;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addBlog(Blog blog) {
        System.out.println(blog);
        return "post success";
    }

    @POST
    @Path("post/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String postTest() {
        return "test post success";
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("id") long id) {
        System.out.println("delete id : " + id);

        return "delete success";
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String put(@PathParam("id")long id,Blog blog) {
        logger.info("id : {}, blog : {}", id , blog);

        return "put success";
    }
}
