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
import readingtips.Video;

/**
 *
 * @author tiitinha
 */
public class VideoDaoTest {
    
    // test x
    public static void main(String[] args) {
        VideoDaoTest test = new VideoDaoTest();
        test.setUp();
        test.listVideos();
        test.insertVideo();
    }

    private VideoDao dao;

    @Before
    public void setUp() {
        DatabaseTestSetup dataBaseTestSetup = new DatabaseTestSetup();
        dataBaseTestSetup.alustaTietokanta();
        dao = new VideoDao();
    }

    @Test
    public void insertVideo() {
        List<String> tags = Arrays.asList("tag1", "tag2");
        List<String> courses = Arrays.asList("course1", "course2");
        Video video = new Video("Muumi2020", "sairas tarina", "Toove", tags, courses, "muumit.com");
        dao.create(video);
        assertTrue(video.getId() > 0);
    }

    @Test
    public void listVideos() {
        List<String> tags = Arrays.asList("tag1", "tag2");
        List<String> courses = Arrays.asList("course1", "course2");
        Video video = new Video("Muumi2020", "sairas tarina", "Toove", tags, courses, "muumit.com");
        // Integer id = dao.create(book);
        dao.create(video);
        Video video2 = new Video("Muumi2021", "sairas tarina2", "Toove", tags, courses, "muumit.com");
        // Integer id2 = dao.create(book2);
        dao.create(video);

        List<Video> list = dao.list();

        assertTrue(list.size() > 1);
    }

    @Test
    public void listVideosContainsAddedVideo() {
        List<String> tags = Arrays.asList("tag1", "tag2");
        List<String> courses = Arrays.asList("course1", "course2");
        Video video = new Video("Muumi2020", "sairas tarina", "Toove", tags, courses, "muumit.com");
        dao.create(video);
        Video video2 = new Video("Muumi2021", "sairas tarina2", "Toove", tags, courses, "muumit.com");
        dao.create(video2);

        List<Video> list = dao.list();

        assertTrue(video.equals(list.get((0))));
    }

    @Test
    public void updateVideoUpdatesVideo() {
        List<String> tags = Arrays.asList("tag1", "tag2");
        List<String> courses = Arrays.asList("course1", "course2");
        Video video = new Video("Muumi2020", "sairas tarina", "Toove", tags, courses, "muumit.com");
        dao.create(video);
        video.setAuthor("sairas tarina2");
        dao.update(video);
        
        List<Video> list = dao.list();

        assertTrue(video.getAuthor().equals(list.get(0).getAuthor()));
    }

    @Test
    public void deleteBookDeletesBook() {
        List<String> tags = Arrays.asList("tag1", "tag2");
        List<String> courses = Arrays.asList("course1", "course2");
        Video video = new Video("Muumi2020", "sairas tarina", "Toove", tags, courses, "muumit.com");
        dao.create(video);
        dao.delete(video);

        List<Video> list = dao.list();

        assertTrue(list.size() == 0);
    }
}
