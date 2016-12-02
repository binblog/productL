package service.blog;

import domain.blog.Blog;
import mapper.blog.BlogMapper;

public class BlogService {

    private BlogMapper blogMapper;

    public Blog selectBlog(long id) {
        System.out.println("mapper : " + blogMapper);
        return blogMapper.selectBlog(id);
    }

    public BlogMapper getBlogMapper() {
        return blogMapper;
    }

    public void setBlogMapper(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }
}
