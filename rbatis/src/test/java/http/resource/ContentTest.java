package http.resource;


import http.resource.example.domain.Blog;
import http.resource.example.service.BlogService;
import org.junit.Test;

import java.io.File;

/**
 * Created by bin.liang on 2016/12/2.
 */
public class ContentTest {

    @Test
    public void test() {

        // resourceFactory
        ResourceContent content = new ResourceContent();

        content.setPreUrl("http://localhost:8080/rbatis/");


        content.addClass(BlogService.class);

        BlogService blogService = content.getResource(BlogService.class);


//        Blog blog = blogService.getBlog(1);
//
//        System.out.println("开始存放到数据库 : " + blog);
//
//
//        System.out.println("执行其他逻辑");


        Blog blog = new Blog();
        blog.setTitle("中国");

//        System.out.println(blogService.addBlog(blog));
        File file = new File("1.png");
//        blogService.uploadImg(file);
        blogService.uploadAttachment(file, "1_upload.png");

    }
}
