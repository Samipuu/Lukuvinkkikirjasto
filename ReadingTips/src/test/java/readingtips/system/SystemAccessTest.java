package readingtips.system;

import readingtips.entity.BlogPost;
import readingtips.entity.Book;
import readingtips.entity.Podcast;
import readingtips.entity.Video;
import readingtips.database.BlogPostDao;
import readingtips.database.BookDao;
import readingtips.database.PodcastDao;
import readingtips.database.VideoDao;

public class SystemAccessTest {

    public static void main(String[] args) {

        SystemAccess systemAccess = new SystemAccess();

        // test update video length
        {
            Video video = new Video("Laulu kesästä", "Ranskalainen trubaduuri",
                    "Ranskalainen trubaduuri laulaa kesästä", null, null,
                    "Indochine - Un été français (Clip officiel).mp4");
            systemAccess.updateLength(video);
            System.out.println("update video length: " + video.getLength());
        }

        // test update podcast length
        {
            Podcast podcast = new Podcast("Elon ja Lex", "Lex Friman",
                    "Elon Musk - Tesla Autopilot _ Lex Fridman Podcast #18", null, null,
                    "Elon Musk - Tesla Autopilot _ Lex Fridman Podcast #18.mp4");
            systemAccess.updateLength(podcast);
            System.out.println("update podcast length: " + podcast.getLength());
        }

        // test video
        {
            Video video = new Video("Laulu kesästä", "Ranskalainen trubaduuri",
                    "Ranskalainen trubaduuri laulaa kesästä", null, null,
                    "Indochine - Un été français (Clip officiel).mp4");
            VideoDao videoDao = new VideoDao();
            videoDao.create(video);
            systemAccess.open(video);
        }

        // test podcast-video
        {
            // use podcastname same as filename when local file
            Podcast podcast = new Podcast("Elon ja Lex", "Lex Friman",
                    "Elon Musk - Tesla Autopilot _ Lex Fridman Podcast #18", null, null,
                    "Elon Musk - Tesla Autopilot _ Lex Fridman Podcast #18.mp4");
            PodcastDao podcastDao = new PodcastDao();
            podcastDao.create(podcast);
            systemAccess.open(podcast);
        }

        // test podcast-web_url
        {
            // use podcastname same as filename when local file
            Podcast podcast = new Podcast("Dr. Clarence Ellies: The Developer Who Helped us Collaborate", "Red Hat",
                    "COMMAND_LINE HEROES", null, null,
                    "https://www.redhat.com/en/command-line-heroes/season-6/clarence-ellis");
            PodcastDao podcastDao = new PodcastDao();
            podcastDao.create(podcast);
            systemAccess.open(podcast);
        }

        // test blogpost
        {
            BlogPost blogPost = new BlogPost(
                    "Cyberpunk 2077 Includes Visual Effects Designed To Trigger Epileptic Seizure ", "Slashdot.org",
                    "Macthorpe writes:"
                            + "solution it will implement \"as soon as possible.\" Hopefully, that will include a way to turn off strobing effects.",
                    null, null, "https://games.slashdot.org/story/20/12/08/2052258/");
            BlogPostDao blogPostDao = new BlogPostDao();
            blogPostDao.create(blogPost);
            systemAccess.open(blogPost);
        }

        // test book
        {
            Book book = new Book("I Am a Strange oops", "Douglas Hofstadter",
                    "One of our greatest philosophers and scientists of the mind asks, "
                            + "where does the self come from -- and how our selves can exist in the minds of others. Can thought arise out of matter? Can self, soul, "
                            + "The most central and complex symbol in your brain is the one called",
                    null, null, " 978-0465030798");
            BookDao bookDao = new BookDao();
            bookDao.create(book);
            systemAccess.open(book);
        }

        // test podcast-audio TODO: lataa jostain joku ja testaa..
        // {
        // // use podcastname same as filename when local file
        // Podcast podcast = new Podcast("nimi", "tekijä",
        // "kuvaus", null, null,
        // "podcastname/url");
        // PodcastDao podcastDao = new PodcastDao();
        // podcastDao.create(podcast);
        // systemAccess.open(podcast);
        // }

    }

}
