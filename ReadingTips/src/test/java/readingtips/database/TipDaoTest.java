/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readingtips.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import readingtips.BlogPost;
import readingtips.Book;
import readingtips.Podcast;
import readingtips.Video;

/**
 *
 * @author tiitinha
 */
public class TipDaoTest {
    
    public static void main(String[] args) {
        TipDaoTest taa = new TipDaoTest();
        taa.createEditAndDeleteBlogPostTest();
    }
    
    public TipDaoTest() {
    }
    
    @Before
    public void setUp() {
        
    }

    @Test
    public void createTipDaoTest() {
        new TipDao();
    }

    @Test
    public void getAllMethodsTest() {
        TipDao tipDao = new TipDao();
        assertNotNull(tipDao.getAllTips());
        assertNotNull(tipDao.getAllBooksFromDatabase());
        assertNotNull(tipDao.getAllVideosFromDatabase());
        assertNotNull(tipDao.getAllPodcastsFromDatabase());
        assertNotNull(tipDao.getAllBlogPostsFromDatabase());  
        assertNotNull(tipDao.getAllTags());  
        assertNotNull(tipDao.getTipsTagFiltered(new ArrayList<>())); 
    }
    
    @Test
    public void createEditAndDeleteBookTest() {

        // create book
        TipDao tipDao = new TipDao();
        String newTitle = "titteli " + System.currentTimeMillis();
        Book book = new Book(newTitle, "tekijä", "kuvaus", null, null, "123-123");
        tipDao.createTip(book);
        assertTrue(
            tipDao.getAllBooksFromDatabase().stream().filter(x -> x.getTitle().equals(newTitle)).collect(Collectors.toList())
            .size() == 1
        );
        // edit book
        String editedTitle = newTitle + "v2";
        book.setTitle(editedTitle);
        tipDao.editTip(book.getId());
        assertEquals(tipDao.findTip(book.getId()).getTitle(), book.getTitle());
        // delete book
        tipDao.deleteTip(book.getId());
        assertNull(tipDao.findTip(book.getId()));
    }

    @Test
    public void createEditAndDeleteBlogPostTest() {
        // create blogpost
        TipDao tipDao = new TipDao();
        String newTitle = "titteli " + System.currentTimeMillis();
        BlogPost entity = new BlogPost(newTitle, "tekijä", "kuvaus", null, null, "urli");
        tipDao.createTip(entity);
        assertTrue(
            tipDao.getAllBlogPostsFromDatabase().stream().filter(x -> x.getTitle().equals(newTitle)).collect(Collectors.toList())
            .size() == 1
        );
        // edit blogpost
        String editedTitle = newTitle + "v2";
        entity.setTitle(editedTitle);
        tipDao.editTip(entity.getId());
        assertEquals(tipDao.findTip(entity.getId()).getTitle(), entity.getTitle());
        // delete blogpost
        tipDao.deleteTip(entity.getId());
        assertNull(tipDao.findTip(entity.getId()));
    }    

    @Test
    public void createEditAndDeletePodcastTest() {
        // create podcast
        TipDao tipDao = new TipDao();
        String newTitle = "titteli " + System.currentTimeMillis();
        Podcast entity = new Podcast(newTitle, "tekijä", "kuvaus", null, null, "podcastinnimi");
        tipDao.createTip(entity);
        assertTrue(
            tipDao.getAllPodcastsFromDatabase().stream().filter(x -> x.getTitle().equals(newTitle)).collect(Collectors.toList())
            .size() == 1
        );
        // edit podcast
        String editedTitle = newTitle + "v2";
        entity.setTitle(editedTitle);
        tipDao.editTip(entity.getId());
        assertEquals(tipDao.findTip(entity.getId()).getTitle(), entity.getTitle());
        // delete podcast
        tipDao.deleteTip(entity.getId());
        assertNull(tipDao.findTip(entity.getId()));
    }        

    @Test
    public void createEditAndDeleteVideoTest() {
        // create video
        TipDao tipDao = new TipDao();
        String newTitle = "titteli " + System.currentTimeMillis();
        Video entity = new Video(newTitle, "tekijä", "kuvaus", null, null, "urli");        
        tipDao.createTip(entity);
        assertTrue(
            tipDao.getAllVideosFromDatabase().stream().filter(x -> x.getTitle().equals(newTitle)).collect(Collectors.toList())
            .size() == 1
        );
        // edit video
        String editedTitle = newTitle + "v2";
        entity.setTitle(editedTitle);
        tipDao.editTip(entity.getId());
        assertEquals(tipDao.findTip(entity.getId()).getTitle(), entity.getTitle());
        // delete video
        tipDao.deleteTip(entity.getId());
        assertNull(tipDao.findTip(entity.getId()));
    }      
    
    @Test
    public void getTagsTest() {
        TipDao tipDao = new TipDao();
        List<String> tagit = Arrays.asList(new String[]{"rauha","tomaatti","kirves","npc"});
        Book book = new Book("newTitle", "tekijä", "kuvaus", tagit, null, "123-123");
        tipDao.createTip(book);
        assertTrue(tipDao.getAllTags().containsAll(tagit));
    }
    
    @Test
    public void findsCourse() {
        TipDao tipDao = new TipDao();
        List<String> courses = Arrays.asList(new String[]{"ohtu","ohja","lapio","linis"});
        Book book = new Book("newTitle", "tekijä", "kuvaus", null , courses, "123-123");
        tipDao.createTip(book);
        assertTrue(tipDao.getTipsCourseFiltered(courses).contains(book));
    }
    
    @Test
    public void doesNotFindCourse() {
        TipDao tipDao = new TipDao();
        List<String> courses = Arrays.asList(new String[]{"ohtu","ohja","lapio","linis"});
        List<String> coursesTesti = Arrays.asList(new String[]{"tätä kurssia ei löydy"});
        Book book = new Book("newTitle", "tekijä", "kuvaus", null , courses, "123-123");
        tipDao.createTip(book);
        assertTrue(tipDao.getTipsCourseFiltered(coursesTesti).isEmpty());
    }    
    
    @Test
    public void findsTip() {
        TipDao tipDao = new TipDao();
        List<String> tagit = Arrays.asList(new String[]{"rauha","tomaatti","kirves","npc"});
        Book book = new Book("newTitle", "tekijä", "kuvaus", tagit, null, "123-123");
        tipDao.createTip(book);
        assertTrue(tipDao.getTipsTagFiltered(tagit).contains(book));
    }
    
    @Test
    public void doesNotFindTip() {
        TipDao tipDao = new TipDao();
        List<String> tagit = Arrays.asList(new String[]{"rauha","tomaatti","kirves","npc"});
        List<String> tagitTesti = Arrays.asList(new String[]{"tätä tagia ei löydy"});
        Book book = new Book("newTitle", "tekijä", "kuvaus", tagit, null, "123-123");
        tipDao.createTip(book);
        assertTrue(tipDao.getTipsTagFiltered(tagitTesti).isEmpty());
    }
}
