package readingtips.database;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import readingtips.Podcast;

public class PodcastDaoTest {

    // test x
    public static void main(String[] args) {
        PodcastDaoTest test = new PodcastDaoTest();
        test.setUp();
        //test.listBooks();
        test.insertPodcast();
    }

    private PodcastDao dao;

    @Before
    public void setUp() {
        DatabaseTestSetup dataBaseTestSetup = new DatabaseTestSetup();
        dataBaseTestSetup.alustaTietokanta();
        dao = new PodcastDao();
    }

    @Test
    public void insertPodcast() {
        List<String> tags = Arrays.asList("tag1", "tag2");
        List<String> courses = Arrays.asList("course1", "course2");
        Podcast podcast = new Podcast("Title", "Author", "Description", tags, courses, "PodcastName");
        dao.create(podcast);
        assertTrue(podcast.getId() > 0);
    }

    @Test
    public void listPodcast() {
        List<String> tags = Arrays.asList("tag1", "tag2");
        List<String> courses = Arrays.asList("course1", "course2");
        Podcast podcast = new Podcast("Title", "Author", "Description", tags, courses, "PodcastName");
        dao.create(podcast);
        Podcast podcast2 = new Podcast("Title2", "Author2", "Description2", tags, courses, "PodcastName");
        dao.create(podcast2);

        List<Podcast> list = dao.list();

        assertTrue(list.size() > 1);
    }
    // NOT WORKING YET
    // @Test
    // public void updatePodcastUpdatesPodcast() {
    //     List<String> tags = Arrays.asList("tag1", "tag2");
    //     List<String> courses = Arrays.asList("course1", "course2");
    //     Podcast podcast = new Podcast("Title", "Author", "Description", tags, courses, "PodcastName");
    //     dao.create(podcast);
    //     Podcast podcast2 = new Podcast("Title2", "Author2", "Description2", tags, courses, "PodcastName");
    //     dao.update(podcast2);

    //     List<Podcast> list = dao.list();
    //     assertTrue(podcast2.equals(list.get(0)));
    // }
    /**
     * Test of create method, of class PodcastDao.
     */
}
