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
     * Counts the number of books for each author.
     * It retrieves the full name of authors and the count of their books.
     *
     * @param em the EntityManager used to interact with the database
     * @return a list of Object arrays containing author names and their book count
     */
    public static List<Object[]> countBooksByAuthor(EntityManager em) {
        String jpql = "SELECT CONCAT(a.firstName, ' ', a.lastName), COUNT(b.id) " +
                      "FROM Book b " +
                      "JOIN b.author a " +
                      "GROUP BY a.firstName, a.lastName";
        List<Object[]> result = em.createQuery(jpql, Object[].class).getResultList();
        System.out.println("================BOOK COUNT BY AUTHOR==================");
        for (Object[] row : result) {
            System.out.println(row[0] + ": " + row[1] + " books");
        }
        System.out.println();
        return result;
    }


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
     * Retrieves total earnings by each author based on the price of their books.
     * It calculates the sum of the book prices for each author and prints the results.
     *
     * @param em the EntityManager used to interact with the database
     */
    public static void totalEarningsByAuthor(EntityManager em) {
        String jpql = "SELECT CONCAT(a.firstName, ' ', a.lastName), SUM(b.price) " +
                      "FROM Book b " +
                      "JOIN b.author a " +
                      "GROUP BY a.firstName, a.lastName";
        List<Object[]> result = em.createQuery(jpql, Object[].class).getResultList();
        
        System.out.println("================EARNINGS BY EACH AUTHOR==================");
        for (Object[] row : result) {
            System.out.println("Author: " + row[0] + ", Total Earnings: " + row[1]);
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
     * List books available in more than one bookstore.
     * It retrieves books and their associated bookstores if they are sold in more than one store.
     *
     * @param em the EntityManager used to interact with the database
     */
    public static void listBooksInMultipleBookstores(EntityManager em) {
        Query query = em.createQuery(
            "SELECT b FROM Book b WHERE SIZE(b.bookstoreList) > 1"
        );
        List<Book> books = query.getResultList();
        System.out.println("================BOOKS IN MULTIPLE BOOKSTORES==================");
        for (Book book : books) {
            System.out.println(book + "; Bookstores: " + book.getBookstoreList());
        }
        System.out.println();
    }
    
    /**
     * List books along with their authors and editorials.
     * It retrieves books, author names, and editorial names.
     *
     * @param em the EntityManager used to interact with the database
     * @return a list of Object arrays containing book titles, author names, and editorial names
     */
    public static List<Object[]> listBooksWithAuthorsAndEditorials(EntityManager em) {
    	 String jpql = "SELECT b.title, CONCAT(a.firstName, ' ', a.lastName), p.name " +
    	            "FROM Book b " +
    	            "JOIN b.author a " +
    	            "JOIN b.publisher p";
        List<Object[]> result = em.createQuery(jpql, Object[].class).getResultList();
        System.out.println("================BOOKS WITH AUTHORS AND EDITORIALS==================");
        for (Object[] row : result) {
            System.out.println("Title: " + row[0] + ", Author: " + row[1] + ", Editorial: " + row[2]);
        }
        System.out.println();
        return result;
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