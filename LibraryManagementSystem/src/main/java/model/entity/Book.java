package model.entity;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;  
    private double price;  
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Publisher publisher;  
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Author author; 
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Bookstore> bookstoreList = new ArrayList<Bookstore>(); 
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Bookstore> getBookstoreList() {
        return bookstoreList;
    }

    public void setBookstoreList(List<Bookstore> bookstoreList) {
        this.bookstoreList = bookstoreList;
    }
    
    
    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", price=" + price + ", publisher=" + publisher + ", author="
                + author + ", bookstore list=" + bookstoreList + "]";
    }    
}
