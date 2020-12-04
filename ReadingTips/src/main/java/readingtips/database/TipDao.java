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

    protected BookDao bookDao;
    protected VideoDao videoDao;
    protected PodcastDao podcastDao;
    protected BlogPostDao blogPostDao;
    protected List<Tip> allTips;

    public TipDao() {
        bookDao = new BookDao();
        videoDao = new VideoDao();
        podcastDao = new PodcastDao();
        blogPostDao = new BlogPostDao();

        allTips = new ArrayList();

        List<Book> books = getAllBooksFromDatabase();
        allTips.addAll(books);

        List<Video> videos = getAllVideosFromDatabase();
        allTips.addAll(videos);

        List<Podcast> podcasts = getAllPodcastsFromDatabase();
        allTips.addAll(podcasts);

        List<BlogPost> blogPosts = getAllBlogPostsFromDatabase();
        allTips.addAll(blogPosts);
    }

    public List<Tip> getAllTips() {
        return allTips;
    }

    List<Book> getAllBooksFromDatabase() {
        List<Book> books = bookDao.list();

        return books;
    }

    protected List<Video> getAllVideosFromDatabase() {
        List<Video> videos = videoDao.list();

        return videos;
    }

    protected List<Podcast> getAllPodcastsFromDatabase() {
        List<Podcast> podcasts = podcastDao.list();

        return podcasts;
    }

    protected List<BlogPost> getAllBlogPostsFromDatabase() {
        List<BlogPost> blogPosts = blogPostDao.list();

        return blogPosts;
    }

    public void createTip(Tip tip) {
        allTips.add(tip);
        if (tip.getClass() == Book.class) {
            Book book = (Book) tip;
            bookDao.create(book);
        } else if (tip.getClass() == Video.class) {
            Video video = (Video) tip;
            videoDao.create(video);
        } else if (tip.getClass() == Podcast.class) {
            Podcast podcast = (Podcast) tip;
            podcastDao.create(podcast);
        } else if (tip.getClass() == BlogPost.class) {
            BlogPost blogPost = (BlogPost) tip;
            blogPostDao.create(blogPost);
        }
    }

    public void deleteTip(int identificator) {
        Tip tip = findTip(identificator);

        if (tip.getClass() == Book.class) {
            Book book = (Book) tip;
            bookDao.delete(book);
        } else if (tip.getClass() == Video.class) {
            Video video = (Video) tip;
            videoDao.delete(video);
        } else if (tip.getClass() == Podcast.class) {
            Podcast podcast = (Podcast) tip;
            podcastDao.delete(podcast);
        } else if (tip.getClass() == BlogPost.class) {
            BlogPost blogPost = (BlogPost) tip;
            blogPostDao.delete(blogPost);
        }
        
        allTips.remove(tip);
    }

    public void editTip(int identificator) {
        Tip tip = findTip(identificator);

        if (tip.getClass() == Book.class) {
            Book book = (Book) tip;
            bookDao.update(book);
        } else if (tip.getClass() == Video.class) {
            Video video = (Video) tip;
            videoDao.update(video);
        } else if (tip.getClass() == Podcast.class) {
            Podcast podcast = (Podcast) tip;
            podcastDao.update(podcast);
        } else if (tip.getClass() == BlogPost.class) {
            BlogPost blogPost = (BlogPost) tip;
            blogPostDao.update(blogPost);
        }
        
    }

    protected Tip findTip(int identificator) {
        return allTips.stream().filter(t -> t.getId() == identificator).findFirst().orElse(null);
    }

}
