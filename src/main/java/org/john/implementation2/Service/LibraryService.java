package org.john.implementation2.Service;

import org.john.implementation2.Model.Book;
import org.john.implementation2.Model.Person;

public interface LibraryService {
    void LibraryServiceImpl();

    void addBook(Book book);
    void addPersonToQueue(Person person);
    void borrowBooks();
    boolean returnBook(Book book);
}
