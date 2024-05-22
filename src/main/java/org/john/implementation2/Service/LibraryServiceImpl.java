package org.john.implementation2.Service;

import org.john.implementation2.Model.Book;
import org.john.implementation2.Model.Person;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class LibraryServiceImpl implements LibraryService {

    private HashMap<String, Queue<Book>> books;
    private Queue<Person> bookRequets;
    private HashMap<Book, Person> borrowedBooks;

    @Override
    public void LibraryServiceImpl() {
        this.books = new HashMap<>();
        this.bookRequets = new LinkedList<>();
        this.borrowedBooks = new HashMap<>();

    }
    @Override
    public void addBook(Book book) {
        books.putIfAbsent(book.getTitle(), new LinkedList<>());
        books.get(book.getTitle()).add(book);
    }

    @Override
    public void addPersonToQueue(Person person) {
        bookRequets.add(person);

    }

    @Override
    public void borrowBooks() {
        while (!bookRequets.isEmpty()) {
            Person person = bookRequets.poll();
            String requestBookTitle = person.getRequestedBook().getTitle();
            Queue<Book> bookQueue = books.get(requestBookTitle);

            if (bookQueue != null && !bookQueue.isEmpty()) {
                Book book = bookQueue.poll();
                borrowedBooks.put(book, person);
                System.out.println(person + "has borrowed " + book);

            } else {
                System.out.println("No copies of " + requestBookTitle + "are available for " + person);
            }
        }

    }

    @Override
    public boolean returnBook(Book book) {
        if (borrowedBooks.containsKey(book)) {
            Person person = borrowedBooks.remove(book);
            books.get(book.getTitle()).add(book);
            System.out.println(person + "has returned" + book);
            return true;

        } else {
            System.out.println("This book was not borrowed from this library");

            return false;
        }

    }
        public Queue<Book> getBooks(){
        Queue<Book> allBooks = new LinkedList<>();
        for(Queue<Book> bookQueue : books.values()){
            allBooks.addAll(bookQueue);
        }
        return allBooks;

    }

    public Queue<Person> getBookRequets(){
        return new LinkedList<>(bookRequets);

    }
    public boolean isBookBorrowedBy(Person person, Book book){
        return borrowedBooks.get(book) != null && borrowedBooks.get(book).equals(person);

    }

}
