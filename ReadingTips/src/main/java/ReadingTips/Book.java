/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReadingTips;

import java.util.List;

/**
 *
 * @author aatukallio
 */
public class Book extends Tip{   
    private String isbn;
    
    public Book(String title, String description, String author, List<String> tags,List<String> courses, String isbn) {
        super("Book",title,description,author,tags,courses);
        this.isbn = isbn;
    }
    
    public String getIsbn() {
        return this.isbn;
    }
    
    @Override
    public String toString() {
        String palautus = super.toString();
        if (this.getIsbn() != null & !this.getIsbn().isEmpty()) palautus += "\nISBN: " + this.isbn;
        return palautus;
    }
}
