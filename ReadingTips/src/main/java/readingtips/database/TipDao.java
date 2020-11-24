/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readingtips.database;

import java.util.ArrayList;
import java.util.List;
import readingtips.BlogPost;
import readingtips.Book;
import readingtips.Podcast;
import readingtips.Tip;
import readingtips.Video;

/**
 *
 * @author tiitinha
 */
public class TipDao {

    private BookDao bookDao;
    private VideoDao videoDao;
    private PodcastDao podcastDao;
    private BlogPostDao blogPostDao;

    public TipDao() {
        bookDao = new BookDao();
        videoDao = new VideoDao();
        podcastDao = new PodcastDao();
        blogPostDao = new BlogPostDao();
    }

    public List<Tip> getAllTips() {
        List<Tip> tips = new ArrayList<>();

        List<Book> books = getAllBooks();
        tips.addAll(books);
        
        List<Video> videos = getAllVideos();
        tips.addAll(videos);
        
        List<Podcast> podcasts = getAllPodcasts();
        tips.addAll(podcasts);
        
        List<BlogPost> blogPosts = getAllBlogPosts();
        tips.addAll(blogPosts);
        
        return tips;
    }

    private List<Book> getAllBooks() {
        List<Book> books = bookDao.list();

        return books;
    }

    private List<Video> getAllVideos() {
        List<Video> videos = videoDao.list();

        return videos;
    }

    private List<Podcast> getAllPodcasts() {
        List<Podcast> podcasts = podcastDao.list();

        return podcasts;
    }

    private List<BlogPost> getAllBlogPosts() {
        List<BlogPost> blogPosts = blogPostDao.list();

        return blogPosts;
    }

    public boolean createTip(Tip tip) {
        if (tip.getClass() == Book.class) {
            Book book = (Book) tip;
            bookDao.create(book);
            return true;
        } else if (tip.getClass() == Video.class) {
            Video video = (Video) tip;
            videoDao.create(video);
            return true;
        } else if (tip.getClass() == Podcast.class) {
            Podcast podcast = (Podcast) tip;
            podcastDao.create(podcast);
        } else if (tip.getClass() == BlogPost.class) {
            BlogPost blogPost = (BlogPost) tip;
            blogPostDao.create(blogPost);
        }
        return false;
    }

}
