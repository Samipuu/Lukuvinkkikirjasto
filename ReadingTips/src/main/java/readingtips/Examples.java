package readingtips;

import readingtips.database.BlogPostDao;
import readingtips.database.BookDao;
import readingtips.database.PodcastDao;
import readingtips.database.VideoDao;
import readingtips.system.SystemAccess;
import java.util.*;

/**
 * Luokka jossa esimerkkidataa
 */
public class Examples {

    // '2001 A Space Odyssey Arthur C. Clarke Audiobook.webm'
    // 'A Brief History of Time _ Audiobook _ Stephen Hawking.m4a'
    // 'Andrew Ng - Deep Learning, Education, and Real-World AI _ Lex Fridman Podcast #73.mp4'
    // 'Brian Kernighan - UNIX, C, AWK, AMPL, and Go Programming _ Lex Fridman Podcast #109.mp4'
    // 'Carl Sagan  - Pale blue dot - A Vision of the Human Future in Space [audiobook].webm'
    // 'Elon Musk - Tesla Autopilot _ Lex Fridman Podcast #18.mp4'
    // 'François Chollet - Measures of Intelligence _ Lex Fridman Podcast #120.mp4'
    // 'Garry Kasparov - Chess, Deep Blue, AI, and Putin _ Lex Fridman Podcast #46.mp4'
    // 'History is Wrong By Erich Von Daniken Complete Audiobook.m4a'
    // 'Indochine - Karma Girls (Clip officiel).mp4'
    // 'Indochine - Nos célébrations (Clip officiel).mp4'
    // 'Indochine - Un été français (Clip officiel).mp4'
    // 'James Gosling - Java, JVM, Emacs, and the Early Days of Computing _ Lex Fridman Podcast #126.mp4'
    // 'Joe Rogan Experience #1531 - Miley Cyrus.mp4'
    // 'Neuromancer William Gibson Audiobook.webm'
    // 'Rendezvous With Rama Arthur C Clarke Audiobook.m4a'
    // 'Richard Dawkins - Evolution, Intelligence, Simulation, and Memes _ Lex Fridman Podcast #87.mp4'
    // 'Silmarillion - J R R Tolkien - Complete Audiobook Unabridged.webm'
    // 'Starship Troopers - Robert A. Heinlein _ Audiobook.webm'
    // 'The Ancestors Tale by Richard Dawkins.webm'
    // 'The Andromeda Strain by Michael Crichton _ Audiobook.m4a'
    // 'The Theory of Everything - Origin and Fate of the Universe - Stephen Hawking - Unabridged Audiobook.webm'
    // 'Unfuck Yourself Audiobook By Gary John Bishop.m4a'
    
    /**
     * luo esimerkkidataa tietokantaan.
     */
    public static void createExamples() {

        SystemAccess systemAccess = new SystemAccess();
        VideoDao videoDao = new VideoDao();
        PodcastDao podcastDao = new PodcastDao();
        BlogPostDao blogPostDao = new BlogPostDao();
        BookDao bookDao = new BookDao();
        List<String> art_tag = new ArrayList();
        art_tag.add("art");
        List<String> adventure_tag = new ArrayList();
        art_tag.add("adventure");   
        List<String> tec_tag = new ArrayList();
        art_tag.add("technology");              
        List<String> music_tag = new ArrayList();
        art_tag.add("music");
        

        {
            Podcast podcast = new Podcast("Joe Rogan Experience #1531 - Miley Cyrus", "Joe",
                    "Joe ja Miley juttelee asioista", music_tag, null,
                    "Joe Rogan Experience #1531 - Miley Cyrus.mp4");
            systemAccess.updateLength(podcast);
            podcastDao.create(podcast);
        }
        {
            Video video = new Video("Karmee stara", "Ranskalainen trubaduuri",
                    "Ranskalainen trubaduuri on kaikkien idoli", art_tag, null,
                    "Indochine - Karma Girls (Clip officiel).mp4");
            systemAccess.updateLength(video);
            videoDao.create(video);
        }
        {
            Video video = new Video("Laulu kesästä", "Ranskalainen trubaduuri",
                    "Ranskalainen trubaduuri laulaa kesästä", art_tag, null,
                    "Indochine - Un été français (Clip officiel).mp4");
            systemAccess.updateLength(video);
            videoDao.create(video);
        }

        {
            Podcast podcast = new Podcast("Elon ja Lex", "Lex Friman",
                    "Elon Musk - Tesla Autopilot _ Lex Fridman Podcast #18", tec_tag, null,
                    "Elon Musk - Tesla Autopilot _ Lex Fridman Podcast #18.mp4");
            systemAccess.updateLength(podcast);
            podcastDao.create(podcast);
        }

        {
            // 'Indochine - Nos célébrations (Clip officiel).mp4'
            Video video = new Video("Brexit bileet", "Ranskalainen trubaduuri",
                    "Ranskalainen trubaduuri laulaa bileistä", music_tag, null,
                    "Indochine - Nos célébrations (Clip officiel).mp4");
            systemAccess.updateLength(video);
            videoDao.create(video);
        }

        {
            // use podcastname same as filename when local file
            Podcast podcast = new Podcast("Dr. Clarence Ellies: The Developer Who Helped us Collaborate", "Red Hat",
                    "COMMAND_LINE HEROES", tec_tag, null,
                    "https://www.redhat.com/en/command-line-heroes/season-6/clarence-ellis");
            podcastDao.create(podcast);
        }

        {
            BlogPost blogPost = new BlogPost(
                    "Cyberpunk 2077 Includes Visual Effects Designed To Trigger Epileptic Seizure ", "Slashdot.org",
                    "Macthorpe writes:"
                            + "solution it will implement \"as soon as possible.\" Hopefully, that will include a way to turn off strobing effects.",
                    tec_tag, null, "https://games.slashdot.org/story/20/12/08/2052258/");
            blogPostDao.create(blogPost);
        }

        {
            Book book = new Book("I Am a Strange oops", "Douglas Hofstadter",
                    "One of our greatest philosophers and scientists of the mind asks, "
                            + "where does the self come from -- and how our selves can exist in the minds of others. Can thought arise out of matter? Can self, soul, "
                            + "The most central and complex symbol in your brain is the one called",
                    null, null, " 978-0465030798");
            bookDao.create(book);
        }

    }

}
