/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readingtips.database;

import java.util.ArrayList;
import java.util.List;
import readingtips.Book;
import readingtips.Tip;

/**
 *
 * @author tiitinha
 */
public class TipDao {
    
    private BookDao bookDao;

    public TipDao() {
        bookDao = new BookDao();
    }
    
    public List<Tip> getAllTips() {
        List<Tip> tips = new ArrayList<>();
        
        List<Book> books = getAllBooks();
        
        tips.addAll(books);
        
        return tips;
    }
    
    private List<Book> getAllBooks() {
        List<Book> books = bookDao.list();
        
        return books;
    }
    
    public boolean createTip(Tip tip) {
        if (tip.getClass() == Book.class) {
            Book book = (Book) tip;
            bookDao.create(book);
            return true;
        }
        return false;
    }
    
}
