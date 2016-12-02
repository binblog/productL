package example.service;

import example.domain.Blog;
import http.resource.rest.Path;

/**
 * Created by bin.liang on 2016/12/2.
 */
@Path("blog")
public interface BlogService {


   Blog getBlog(long id);

}
