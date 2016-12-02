package mapper.test;

import domain.blog.Blog;
import mapper.blog.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by bin.liang on 2016/11/22.
 */
public class BlogTest {


    @Test
    public void  t1() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory  = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            Blog blog = session.selectOne("mapper.blog.BlogMapper.selectBlog", 1);

            System.out.println(blog);
        } finally {
            session.close();
        }
    }

    @Test
    public void t2() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory  = new SqlSessionFactoryBuilder().build(inputStream);


        SqlSession session = sqlSessionFactory.openSession();
        try {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            Blog blog = mapper.selectBlog(1);

            System.out.println(blog);
        } finally {
            session.close();
        }
    }
}
