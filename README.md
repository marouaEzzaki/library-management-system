# Library Management System - JPA Integration

This project demonstrates how to manage a library system using Java Persistence API (JPA). It models bidirectional relationships between entities like Authors, Publishers, Books, and Bookstores, showcasing how these relationships can be represented in a relational database.

## Table of Contents
- [Introduction](#introduction)
- [Technologies Used](#technologies-used)
- [Features](#features)
- [Setup](#setup)
- [Usage](#usage)
- [License](#license)

## Introduction

This project is designed to manage a library system where you can perform CRUD operations using JPA and MySQL (configured with Hibernate). The relationships between entities such as Authors, Publishers, Books, and Bookstores are bidirectional, making it a perfect demonstration of how to handle such relationships in a real-world database.

The system includes functionalities like adding books, authors, and publishers to the database, as well as retrieving information about books, their authors, and publishers.

## Technologies Used

- **Java**: The primary programming language used for this project.
- **JPA (Java Persistence API)**: For object-relational mapping (ORM).
- **MySQL**: For database management.
- **Hibernate**: The ORM framework used to interact with the MySQL database.
- **Maven**: For dependency management.
- **H2 Database (optional)**: You can choose to use H2 instead of MySQL for development purposes.

## Features

- **Bidirectional Relationships**: Demonstrates how to manage bidirectional relationships between entities such as Authors, Publishers, and Bookstores.
- **CRUD Operations**: Perform Create, Read, Update, and Delete operations on Authors, Publishers, Books, and Bookstores.
- **Database Generation**: The schema is generated and updated automatically through JPA.
- **Console-based**: Interact with the system through the console to display books, authors, and bookstores.

## Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/marouaEzzaki/library-management-system.git
   cd library-management-system
   
## Configure MySQL Database

1. **Ensure that you have MySQL installed and running.**
2. **Create a database named `librerias` (or change the database name in `persistence.xml`).**

## Add dependencies

Make sure all dependencies are included in your `pom.xml` file (for Maven) or configured in your project settings.

## Database Configuration

In the `persistence.xml`, configure the database connection parameters:

```xml
<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/librerias"/>
<property name="javax.persistence.jdbc.user" value="root"/>
<property name="javax.persistence.jdbc.password" value="yourpassword"/>
```
## Run the application

1. Open the `MainApplication.java` file.
2. Run the application using your IDE or from the command line.
3. The application will connect to the database and perform sample operations such as adding books, authors, and publishers.

## Usage

This application allows you to manage books, authors, bookstores, and their relationships. You can execute several queries to interact with the database:

### Automatically Added Data
- Authors, publishers, books, and bookstores will be automatically added to the database.

### Available Queries

1. **List all books:**
   - Retrieves all books from the database, displaying their titles, authors, and publishers.
   
2. **List all authors:**
   - Displays authors and their associated books.

3. **List all bookstores:**
   - Retrieves all bookstores and their book collections.

4. **Count books by author:**
   - Lists the number of books for each author along with their full name.

5. **Total earnings by author:**
   - Calculates and displays the total earnings for each author based on the price of their books.

6. **Books in multiple bookstores:**
   - Lists books that are sold in more than one bookstore, displaying the bookstores selling each book.

7. **Books with authors and publishers:**
   - Displays books along with their authors and publishers.

8. **Books and their bookstores:**
   - Lists books and the bookstores that sell them.

These queries will help you analyze and view the relationships between books, authors, and bookstores in the system.


```java
Queries.countBooksByAuthor(em);
Queries.listBooksInMultipleBookstores(em);
Queries.listBooksWithAuthorsAndEditorials(em);
Queries.totalEarningsByAuthor(em);
Queries.listBooks(em);
Queries.listAuthors(em);
Queries.listBookstores(em);
Queries.listBooksInBookstores(em);
```
## License

This project is licensed under the MIT License.
