package model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    
    @OneToMany(mappedBy = "publisher", cascade = CascadeType.PERSIST)
    private List<Book> bookCollection;  
    
 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Book> getBookCollection() {
        return bookCollection;
    }

    public void setBookCollection(List<Book> bookCollection) {
        this.bookCollection = bookCollection;
    }
    
    @Override
    public String toString() {
        return "Publisher [id=" + id + ", name=" + name + ", address=" + address + "]";
    }
}
