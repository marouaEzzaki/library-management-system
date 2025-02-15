package main;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.entity.Author;
import model.entity.Publisher;
import queries.Queries;
import model.entity.Bookstore;
import model.entity.Book;

/**
 * Main application for managing a library system using JPA (Java Persistence API).
 * This application demonstrates how to model relationships between entities in a relational database.
 * Bidirectional relationships between Authors, Publishers, Books, and Bookstores are demonstrated in this project.
 */
public class MainApplication {

    public static EntityManagerFactory emf = null;
    public static EntityManager em = null;

    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("DataLibrary");

        startBBDD();

        em = emf.createEntityManager();

        // Execute queries to list books, authors, bookstores, and books in bookstores
        Queries.countBooksByAuthor(em);
        Queries.listBooksInMultipleBookstores(em);
        Queries.listBooksWithAuthorsAndEditorials(em);
        Queries.totalEarningsByAuthor(em);
        Queries.listBooks(em);
        Queries.listAuthors(em);
        Queries.listBookstores(em);
        Queries.listBooksInBookstores(em);

        em.close();
    }

    /**
     * Initializes the database with sample data.
     * This method creates authors, publishers, bookstores, and books,
     * and persists them in the database using JPA.
     */
    private static void startBBDD() {
        EntityManager em = null;

        try {
            // Create Authors
            Author author1 = new Author();
            author1.setFirstName("Gabriel");
            author1.setLastName("García Márquez");
            author1.setBirthDate("1990-07-23");

            Author author2 = new Author();
            author2.setFirstName("Isabel");
            author2.setLastName("Allende");
            author2.setBirthDate("1999-11-03");

            Author author3 = new Author();
            author3.setFirstName("Mario");
            author3.setLastName("Vargas Llosa");
            author3.setBirthDate("2000-01-09");

            // Create Publishers
            Publisher publisher1 = new Publisher();
            publisher1.setName("Penguin Random House");
            publisher1.setAddress("Calle Principal 123");

            Publisher publisher2 = new Publisher();
            publisher2.setName("Planeta");
            publisher2.setAddress("Calle Maroua 123");

            // Create Bookstores
            Bookstore bookstore1 = new Bookstore();
            bookstore1.setName("Librería Central");
            bookstore1.setAddress("Calle Principal 123");
            bookstore1.setOwnerName("Maroua Ezzaki");

            Bookstore bookstore2 = new Bookstore();
            bookstore2.setName("Librería del Barrio");
            bookstore2.setAddress("Avenida Libertad 45");
            bookstore2.setOwnerName("Yasmina Ezzaki");

            // Create List of Bookstores
            List<Bookstore> bookstoreList = new ArrayList<>();
            bookstoreList.add(bookstore1);
            bookstoreList.add(bookstore2);

            // Create Books
            Book book1 = new Book();
            book1.setTitle("One Hundred Years of Solitude");
            book1.setAuthor(author1);
            book1.setPublisher(publisher1);
            book1.setBookstoreList(bookstoreList);
            book1.setPrice(19.99);

            Book book2 = new Book();
            book2.setTitle("Love in the Time of Cholera");
            book2.setAuthor(author1);
            book2.setPublisher(publisher2);
            book2.setBookstoreList(bookstoreList);
            book2.setPrice(15.99);

            Book book3 = new Book();
            book3.setTitle("The House of the Spirits");
            book3.setAuthor(author2);
            book3.setPublisher(publisher1);
            book3.setBookstoreList(bookstoreList);
            book3.setPrice(12.99);

            Book book4 = new Book();
            book4.setTitle("Paula");
            book4.setAuthor(author2);
            book4.setPublisher(publisher2);
            book4.setBookstoreList(bookstoreList);
            book4.setPrice(22.50);

            Book book5 = new Book();
            book5.setTitle("The City and the Dogs");
            book5.setAuthor(author3);
            book5.setPublisher(publisher1);
            book5.setBookstoreList(bookstoreList);
            book5.setPrice(17.45);

            Book book6 = new Book();
            book6.setTitle("Conversation in the Cathedral");
            book6.setAuthor(author3);
            book6.setPublisher(publisher2);
            book6.setBookstoreList(bookstoreList);
            book6.setPrice(14.75);

            Book book7 = new Book();
            book7.setTitle("The Autumn of the Patriarch");
            book7.setAuthor(author1);
            book7.setPublisher(publisher1);
            book7.setBookstoreList(bookstoreList);
            book7.setPrice(20.30);

            Book book8 = new Book();
            book8.setTitle("The Savage Detectives");
            book8.setAuthor(author3);
            book8.setPublisher(publisher2);
            book8.setBookstoreList(bookstoreList);
            book8.setPrice(18.50);

            // Set Books in Bookstore Collections
            List<Book> bookstore1Collection = new ArrayList<>();
            bookstore1Collection.add(book1);
            bookstore1Collection.add(book3);
            bookstore1Collection.add(book5);
            bookstore1Collection.add(book7);
            bookstore1.setBookCollection(bookstore1Collection);

            List<Book> bookstore2Collection = new ArrayList<>();
            bookstore2Collection.add(book2);
            bookstore2Collection.add(book4);
            bookstore2Collection.add(book6);
            bookstore2Collection.add(book8);
            bookstore2.setBookCollection(bookstore2Collection);

            // Set Books in Author and Publisher Collections
            author1.setBookList(bookstore1Collection);
            author2.setBookList(bookstore2Collection);
            author3.setBookList(bookstore1Collection);

            publisher1.setBookCollection(bookstore1Collection);
            publisher2.setBookCollection(bookstore2Collection);

            em = emf.createEntityManager();
            em.getTransaction().begin();

            if (isValidBook(book1)) em.persist(book1);
            if (isValidBook(book2)) em.persist(book2);
            if (isValidBook(book3)) em.persist(book3);
            if (isValidBook(book4)) em.persist(book4);
            if (isValidBook(book5)) em.persist(book5);
            if (isValidBook(book6)) em.persist(book6);
            if (isValidBook(book7)) em.persist(book7);
            if (isValidBook(book8)) em.persist(book8);

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private static boolean isValidBook(Book book) {
        if (book.getTitle() == null || book.getTitle().isEmpty()) {
            System.err.println("Invalid book: title is missing.");
            return false;
        }
        if (book.getAuthor() == null || !isValidAuthor(book.getAuthor())) {
            System.err.println("Invalid book: author is invalid.");
            return false;
        }
        if (book.getPublisher() == null || !isValidPublisher(book.getPublisher())) {
            System.err.println("Invalid book: publisher is invalid.");
            return false;
        }
        if (book.getPrice() <= 0) {
            System.err.println("Invalid book: price must be positive.");
            return false;
        }
        return true;
    }

    private static boolean isValidAuthor(Author author) {
        return author.getFirstName() != null && !author.getFirstName().isEmpty()
                && author.getLastName() != null && !author.getLastName().isEmpty()
                && !author.getBirthDate().isEmpty();
    }

    private static boolean isValidPublisher(Publisher publisher) {
        return publisher.getName() != null && !publisher.getName().isEmpty()
                && publisher.getAddress() != null && !publisher.getAddress().isEmpty();
    }
}