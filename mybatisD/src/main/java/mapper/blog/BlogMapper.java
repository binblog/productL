package mapper.blog;

import domain.blog.Blog;

/**
 * Created by bin.liang on 2016/11/22.
 */
public interface BlogMapper {
    Blog selectBlog(long id);
}
