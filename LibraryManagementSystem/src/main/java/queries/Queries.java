package queries;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.entity.Author;
import model.entity.Book;
import model.entity.Bookstore;

/**
 * A class to execute different queries related to books, authors, bookstores, and their relationships.
 * These queries are used to retrieve and display data from the database.
 */
@SuppressWarnings("unchecked")
public class Queries {

    /**
     * List all books from the database.
     * It retrieves books and prints their titles, authors, and publishers.
     *
     * @param em the EntityManager used to interact with the database
     */
    public static void listBooks(EntityManager em) {
        Query query = em.createQuery("Select b from Book b");
        
        List<Book> books = query.getResultList();
        System.out.println("================BOOKS==================");
        for (Book book : books) {
            System.out.println(book + ": " + book.getPublisher() + " Author= " + book.getAuthor());
        }
        System.out.println();
    }

    /**
     * List all authors from the database.
     * It retrieves authors and prints their names and associated books.
     *
     * @param em the EntityManager used to interact with the database
     */
    public static void listAuthors(EntityManager em) {
        Query query = em.createQuery("Select a from Author a");
        List<Author> authors = query.getResultList();
        System.out.println("================AUTHORS==================");
        for (Author author : authors) {
            System.out.println(author + "; " + author.getBookList());
        }
        System.out.println();
    }

    /**
     * List all bookstores from the database.
     * It retrieves bookstores and prints their names and associated books.
     *
     * @param em the EntityManager used to interact with the database
     */
    public static void listBookstores(EntityManager em) {
        Query query = em.createQuery("Select bs from Bookstore bs");
        List<Bookstore> bookstores = query.getResultList();
        System.out.println("================BOOKSTORES==================");
        for (Bookstore bookstore : bookstores) {
            System.out.println(bookstore + "; " + bookstore.getBookCollection());
        }
        System.out.println();
    }

    /**
     * List all books along with the bookstores that sell them.
     * It retrieves books and prints their associated bookstores.
     *
     * @param em the EntityManager used to interact with the database
     */
    public static void listBooksInBookstores(EntityManager em) {
        Query query = em.createQuery("Select b from Book b");
        List<Book> books = query.getResultList();
        System.out.println("================BOOKS AND THEIR BOOKSTORES==================");
        for (Book book : books) {
            System.out.println(book + "; " + book.getBookstoreList());
        }
        System.out.println();
    }
}