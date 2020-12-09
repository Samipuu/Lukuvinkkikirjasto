package readingtips;

import readingtips.database.BlogPostDao;
import readingtips.database.BookDao;
import readingtips.database.PodcastDao;
import readingtips.database.VideoDao;
import readingtips.system.SystemAccess;

/**
 * Luokka jossa esimerkkidataa
 */
public class Examples {

    /**
     * luo esimerkkidataa tietokantaan.
     */
    public static void createExamples() {

        SystemAccess systemAccess = new SystemAccess();
        VideoDao videoDao = new VideoDao();
        PodcastDao podcastDao = new PodcastDao();
        BlogPostDao blogPostDao = new BlogPostDao();
        BookDao bookDao = new BookDao();

        {
            Video video = new Video("Karmee stara", "Ranskalainen trubaduuri",
                    "Ranskalainen trubaduuri on kaikkien idoli", null, null,
                    "Indochine - Karma Girls (Clip officiel).mp4");
            systemAccess.updateLength(video);
            videoDao.create(video);
        }
        {
            Video video = new Video("Laulu kesästä", "Ranskalainen trubaduuri",
                    "Ranskalainen trubaduuri laulaa kesästä", null, null,
                    "Indochine - Un été français (Clip officiel).mp4");
            systemAccess.updateLength(video);
            videoDao.create(video);
        }

        {
            Podcast podcast = new Podcast("Elon ja Lex", "Lex Friman",
                    "Elon Musk - Tesla Autopilot _ Lex Fridman Podcast #18", null, null,
                    "Elon Musk - Tesla Autopilot _ Lex Fridman Podcast #18.mp4");
            systemAccess.updateLength(podcast);
            podcastDao.create(podcast);
        }

        {
            // 'Indochine - Nos célébrations (Clip officiel).mp4'
            Video video = new Video("Brexit bileet", "Ranskalainen trubaduuri",
                    "Ranskalainen trubaduuri laulaa bileistä", null, null,
                    "Indochine - Nos célébrations (Clip officiel).mp4");
            systemAccess.updateLength(video);
            videoDao.create(video);
        }

        {
            // use podcastname same as filename when local file
            Podcast podcast = new Podcast("Dr. Clarence Ellies: The Developer Who Helped us Collaborate", "Red Hat",
                    "COMMAND_LINE HEROES", null, null,
                    "https://www.redhat.com/en/command-line-heroes/season-6/clarence-ellis");
            podcastDao.create(podcast);
        }

        {
            BlogPost blogPost = new BlogPost(
                    "Cyberpunk 2077 Includes Visual Effects Designed To Trigger Epileptic Seizure ", "Slashdot.org",
                    "Macthorpe writes:"
                            + "solution it will implement \"as soon as possible.\" Hopefully, that will include a way to turn off strobing effects.",
                    null, null, "https://games.slashdot.org/story/20/12/08/2052258/");
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
