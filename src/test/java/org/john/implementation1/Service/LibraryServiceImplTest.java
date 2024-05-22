package org.john.implementation1.Service;

import org.john.enums.Role;
import org.john.implementation1.Model.Book;
import org.john.implementation1.Model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.PriorityQueue;
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
        Book book = new Book("Intro to Phython","James Gosling");
        libraryService.addBook(book);

        Queue<Book> books = ((LibraryServiceImpl)libraryService).getBooks();
        assertEquals(1, books.size());
        assertEquals(book,books.peek());
    }

    @Test
    void addPersonToQueue() {
        Book book = new Book("Intro to Testing with Junit5","Master Uzoma");
        Person person1 = new Person("John Samuel", Role.JUNIOR_STUDENT, book);
        Person person2 = new Person ("Ejemba Doris",Role.SENIOR_STUDENT,book);
        libraryService.addPersonToQueue(person1);

        HashMap<String, PriorityQueue<Person>> bookRequest = ((LibraryServiceImpl)libraryService).getBookRequest();
        assertEquals(1,bookRequest.get(book.getTitle()).size());
        assertEquals(person1, bookRequest.get(book.getTitle()).peek());
        assertEquals(person2, bookRequest.get(book.getTitle()).peek());
    }

    @Test
    void borrowBook() {
        Book book1 = new Book("Intro to Java", "James Gosling");
        Book book2 = new Book("Intro to Testing with Junit", "Segun osiki");
        libraryService.addBook(book1);
        libraryService.addBook(book2);

        Person teacher = new Person("Sunday Agbo", Role.TEACHER, book2);
        Person seniorStudent2 = new Person("Ifeoma Okereke", Role.SENIOR_STUDENT, book2);

        Person juniorStudent = new Person("Okereke Ify", Role.JUNIOR_STUDENT, book2);
        Person seniorStudent = new Person("Tosin Fagba", Role.SENIOR_STUDENT, book2);

        libraryService.addPersonToQueue(teacher);
        libraryService.addPersonToQueue(seniorStudent);


        libraryService.addPersonToQueue(juniorStudent);
        libraryService.addPersonToQueue(seniorStudent2);

        libraryService.borrowBook();
        assertTrue(((LibraryServiceImpl) libraryService).isBookBorrowedBy(teacher, book1));
        assertFalse(((LibraryServiceImpl) libraryService).isBookBorrowedBy(seniorStudent, book1));

        assertFalse(((LibraryServiceImpl) libraryService).isBookBorrowedBy(juniorStudent, book2));
        assertTrue(((LibraryServiceImpl) libraryService).isBookBorrowedBy(seniorStudent2, book2));

    }

    @Test
    void returnBook() {
        Book book = new Book("Data science","Set Bayo");
        libraryService.addBook(book);

        Person teacher = new  Person("Segun Osiki", Role.TEACHER,book);
        libraryService.addPersonToQueue(teacher);
        libraryService.borrowBook();

        assertTrue(((LibraryServiceImpl) libraryService).isBookBorrowedBy(teacher, book));

        assertTrue(libraryService.returnBook(book));
        assertFalse(((LibraryServiceImpl) libraryService).isBookBorrowedBy(teacher, book));

        Book nonBorrowedBook = new Book("Intro to MIS","Gadibia daro");
        assertFalse(libraryService.returnBook(nonBorrowedBook));

    }

}