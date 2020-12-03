/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readingtips.database;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import readingtips.BlogPost;

/**
 *
 * @author tiitinha
 */
public class BlogPostDaoTest {

    // test x
    public static void main(String[] args) {
        BlogPostDaoTest test = new BlogPostDaoTest();
        test.setUp();
        test.listBlogPosts();
        test.insertBook();
    }

    private BlogPostDao dao;

    @Before
    public void setUp() {
        DatabaseTestSetup dataBaseTestSetup = new DatabaseTestSetup();
        dataBaseTestSetup.alustaTietokanta();
        dao = new BlogPostDao();
    }

    @Test
    public void insertBook() {
        List<String> tags = Arrays.asList("tag1", "tag2");
        List<String> courses = Arrays.asList("course1", "course2");
        BlogPost video = new BlogPost("Muumi2020", "sairas tarina", "Toove", tags, courses, "muumit.com");
        dao.create(video);
        assertTrue(video.getId() > 0);
    }

    @Test
    public void listBlogPosts() {
        List<String> tags = Arrays.asList("tag1", "tag2");
        List<String> courses = Arrays.asList("course1", "course2");
        BlogPost blogPost = new BlogPost("Muumi2020", "sairas tarina", "Toove", tags, courses, "muumit.com");
        // Integer id = dao.create(book);
        dao.create(blogPost);
        BlogPost blogPost2 = new BlogPost("Muumi2021", "sairas tarina2", "Toove", tags, courses, "muumit.com");
        // Integer id2 = dao.create(book2);
        dao.create(blogPost2);

        List<BlogPost> list = dao.list();

        assertTrue(list.size() > 1);
    }

    @Test
    public void listBlogPostsContainsAddedBlogPost() {
        List<String> tags = Arrays.asList("tag1", "tag2");
        List<String> courses = Arrays.asList("course1", "course2");
        BlogPost blogPost = new BlogPost("Muumi2020", "sairas tarina", "Toove", tags, courses, "muumit.com");
        dao.create(blogPost);
        BlogPost blogPost2 = new BlogPost("Muumi2021", "sairas tarina2", "Toove", tags, courses, "muumit.com");
        dao.create(blogPost2);

        List<BlogPost> list = dao.list();

        assertTrue(blogPost.equals(list.get((0))));
    }

    @Test
    public void updateBlogPostkUpdatesBlogPost() {
        List<String> tags = Arrays.asList("tag1", "tag2");
        List<String> courses = Arrays.asList("course1", "course2");
        BlogPost blogPost = new BlogPost("Muumi2020", "sairas tarina", "Toove", tags, courses, "muumit.com");
        dao.create(blogPost);
        blogPost.setAuthor("sairas tarina2");
        dao.update(blogPost);

        List<BlogPost> list = dao.list();

        assertTrue(blogPost.getAuthor().equals(list.get(0).getAuthor()));
    }

    @Test
    public void deleteBookDeletesBook() {
        List<String> tags = Arrays.asList("tag1", "tag2");
        List<String> courses = Arrays.asList("course1", "course2");
        BlogPost blogPost = new BlogPost("Muumi2020", "sairas tarina", "Toove", tags, courses, "muumit.com");
        dao.create(blogPost);
        dao.delete(blogPost);

        List<BlogPost> list = dao.list();

        assertTrue(list.size() == 0);
    }
}
