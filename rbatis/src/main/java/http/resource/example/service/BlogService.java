package http.resource.example.service;

import http.resource.example.domain.Blog;
import http.resource.rest.GET;
import http.resource.rest.Path;
import http.resource.rest.PathParam;

/**
 * Created by bin.liang on 2016/12/2.
 */
@Path("blog")
public interface BlogService {

    @GET
    @Path("{id}")
   Blog getBlog(@PathParam("id") long id);
}
