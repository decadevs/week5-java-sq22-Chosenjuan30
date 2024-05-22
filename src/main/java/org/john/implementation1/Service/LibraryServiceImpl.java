package org.john.implementation1.Service;

import org.john.implementation1.Model.Book;
import org.john.implementation1.Model.Person;

import java.util.HashMap;

import java.util.LinkedList;

import java.util.PriorityQueue;

import java.util.Queue;

public class LibraryServiceImpl implements LibraryService{

    private final HashMap<String, Queue<Book>>books;

    private HashMap<String, PriorityQueue<Person>>bookRequest;

    private  HashMap<Book, Person>borrowedBooks;

    public LibraryServiceImpl() {
        this.books = new HashMap<>();
        this.bookRequest = new HashMap<>();
        this.borrowedBooks = new HashMap<>();
    }
    @Override
    public void addBook(Book book){
        books.putIfAbsent(book.getTitle(),new LinkedList<>());
        books.get(book.getTitle()).add(book);

    }
    @Override
    public void addPersonToQueue(Person person){

        String requestedBookTitle =(person.getRequestBook().getTitle());

        bookRequest.putIfAbsent(requestedBookTitle,new PriorityQueue<>(new RoleComparator()));

        bookRequest.get(requestedBookTitle).add(person);
    }

    @Override
    public void borrowBook() {
        for(String bookTitle : books.keySet()){
            Queue<Book> bookQueue = books.get(bookTitle);
            PriorityQueue<Person> requestQueue = bookRequest.get(bookTitle);
            while (!bookQueue.isEmpty() && requestQueue != null && !requestQueue.isEmpty()){
                Book book = bookQueue.poll();
                Person person = requestQueue.poll();
                borrowedBooks.put(book,person);
                assert person !=null;
                System.out.println(person.getName() + "has borrowed" + book);
            }
        }

    }

    @Override
    public boolean returnBook(Book book) {
        Person person = borrowedBooks.remove(book);
        if(person == null)
            return false;
        books.get(book.getTitle()).add(book);
        return true;
    }
    public Queue<Book> getBooks(){
        Queue<Book> allBooks = new LinkedList<>();
        for(Queue<Book> bookQueue : books.values()) {
            allBooks.addAll(bookQueue);
        }
       return allBooks;
    }
    public HashMap<String,PriorityQueue<Person>> getBookRequest(){
        return bookRequest;

    }
    public boolean isBookBorrowedBy(Person person, Book book){
        return borrowedBooks.get(book) !=null && borrowedBooks.get(book).equals(person);
    }
}
