package mapper.blog;

import domain.blog.Blog;

import java.util.List;

/**
 * Created by bin.liang on 2016/11/22.
 */
public interface BlogMapper {
    Blog selectBlog(long id);

    List<Blog> selectOn(List<Long> list);
}
