package readingtips;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PodcastTest {

    // @Before
    // public static void setUp() {
        
    // }

    @Test
    public void nameIsCorrect() {
        Podcast podcast = new Podcast("Title", "Author", null, null, null, "PodcastName");
        assertEquals("PodcastName", podcast.getPodcastName());
    }
}
