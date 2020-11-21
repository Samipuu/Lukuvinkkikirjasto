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
public class Book {     
    private String title;
    private String author;
    private String isbn;
    private List<Tag> tags;
    private List<Course> courses;
    
    public Book(String title, String author,String isbn,List<Tag> tags, List <Course> courses) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.tags = tags;
        this.courses = courses;
    }
}
