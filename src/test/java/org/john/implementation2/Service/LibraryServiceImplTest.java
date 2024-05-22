package org.john.implementation2.Service;

import org.john.enums.Role;
import org.john.implementation2.Model.Book;
import org.john.implementation2.Model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceImplTest {
    private LibraryService libraryService;

    @BeforeEach
    void setUp(){
        libraryService = new LibraryServiceImpl();

    }

    @Test
    void addBook() {
        Book book = new Book("Intro to Java","James Gosling");
        libraryService.addBook(book);

        Queue<Book> books = ((LibraryServiceImpl)libraryService).getBooks();
        assertEquals(1,books.size());
        assertEquals(book, books.peek());
    }

    @Test
    void addPersonToQueue() {
        Book book = new Book("Intro to Java","James Gosling");
        Person person = new Person("John Agene",12, Role.SENIOR_STUDENT,book);
        libraryService.addPersonToQueue(person);

        Queue<Person> bookReuests = ((LibraryServiceImpl)libraryService).getBookRequets();
        assertEquals(person,bookReuests.peek());


    }

    @Test
    void borrowBooks() {
        Book book1 = new Book("Intro to Java","James Gosling");
        Book book2 = new Book("Intro to Testing with Junits5","Uzoma Ibezim");

        libraryService.addBook(book1);
        libraryService.addBook(book2);

        Person student1 = new Person("John Kudus",26,Role.JUNIOR_STUDENT,book1);
        Person student2 = new Person("John Cast",38,Role.TEACHER,book1);
        Person student3 = new Person("Jayson Mark",27,Role.SENIOR_STUDENT,book2);
        Person student4 = new Person("John Kudus",29,Role.SENIOR_STUDENT,book2);

        libraryService.addPersonToQueue(student1);
        libraryService.addPersonToQueue(student2);
        libraryService.addPersonToQueue(student3);
        libraryService.addPersonToQueue(student4);

        libraryService.borrowBooks();

        assertTrue(((LibraryServiceImpl)libraryService).isBookBorrowedBy(student1,book1));
        assertFalse(((LibraryServiceImpl)libraryService).isBookBorrowedBy(student1,book1));
        assertTrue(((LibraryServiceImpl)libraryService).isBookBorrowedBy(student1,book2));


    }

    @Test
    void returnBook() {
        Book book = new Book("Data science","Femi Dairo");
        libraryService.addBook(book);
        Person teacher = new Person("Segun Osiki",34,Role.TEACHER,book);
        libraryService.addPersonToQueue(teacher);
        libraryService.borrowBooks();

        assertTrue(((LibraryServiceImpl)libraryService).isBookBorrowedBy(teacher,book));
        assertTrue(((LibraryServiceImpl)libraryService).returnBook(book));
        assertTrue(((LibraryServiceImpl)libraryService).isBookBorrowedBy(teacher,book));

        Book nonBorrowedBook = new Book("Intro to MIS","Gadibia daro");
        assertFalse(libraryService.returnBook(nonBorrowedBook));


    }
}