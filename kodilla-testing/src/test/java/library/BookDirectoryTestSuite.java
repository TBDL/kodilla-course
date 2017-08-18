package com.kodilla.testing.library;

import org.junit.*;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;



public class BookDirectoryTestSuite {


    private List<Book> generateListOfNBooks(int booksQuantity) {
        List<Book> resultList = new ArrayList<Book>();
        for(int n = 1; n <= booksQuantity; n++){
            Book theBook = new Book("Title " + n, "Author " + n, 1970 + n);
            resultList.add(theBook);
        }
        return resultList;
    }

    @Test
    public void testListBooksWithConditionsReturnList() {
        // Given
        LibraryDatabase libraryDatabaseMock = mock(LibraryDatabase.class);
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        List<Book> resultListOfBooks = new ArrayList<Book>();
        Book book1 = new Book("Secrets of Alamo", "John Smith", 2008);
        Book book2 = new Book("Secretaries and Directors", "Dilbert Michigan", 2012);
        Book book3 = new Book("Secret life of programmers", "Steve Wolkowitz", 2016);
        Book book4 = new Book("Secrets of Java", "Ian Tenewitch", 2010);
        resultListOfBooks.add(book1);
        resultListOfBooks.add(book2);
        resultListOfBooks.add(book3);
        resultListOfBooks.add(book4);
        when(libraryDatabaseMock.listBooksWithCondition("Secret"))
                .thenReturn(resultListOfBooks);

        // When
        List<Book> theListOfBooks = bookLibrary.listBooksWithCondition("Secret");

        // Then
        assertEquals(4, theListOfBooks.size());
    }

    @Test
    public void testListBooksWithConditionMoreThan20() {
        // Given
        LibraryDatabase libraryDatabaseMock = mock(LibraryDatabase.class);
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        List<Book> resultListOf0Books = new ArrayList<Book>();
        List<Book> resultListOf15Books = generateListOfNBooks(15);
        List<Book> resultListOf40Books = generateListOfNBooks(40);
        when(libraryDatabaseMock.listBooksWithCondition(anyString()))
                .thenReturn(resultListOf15Books);
        when(libraryDatabaseMock.listBooksWithCondition("ZeroBooks"))
                .thenReturn(resultListOf0Books);
        when(libraryDatabaseMock.listBooksWithCondition("FourtyBooks"))
                .thenReturn(resultListOf40Books);

        // When
        List<Book> theListOfBooks0 = bookLibrary.listBooksWithCondition("ZeroBooks");
        List<Book> theListOfBooks15 = bookLibrary.listBooksWithCondition("Any title");
        List<Book> theListOfBooks40 = bookLibrary.listBooksWithCondition("FourtyBooks");
        // Then

        assertEquals(0, theListOfBooks0.size());
        assertEquals(15, theListOfBooks15.size());
        assertEquals(0, theListOfBooks40.size());
    }

    @Test
    public void testListBooksWithConditionFragmentShorterThan3() {
        // Given
        LibraryDatabase libraryDatabaseMock = mock(LibraryDatabase.class);
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        List<Book> resultListOf10Books = generateListOfNBooks(10);
        when(libraryDatabaseMock.listBooksWithCondition(anyString())).thenReturn(resultListOf10Books);
        // When
        List<Book> theListOfBooks10 = bookLibrary.listBooksWithCondition("An");
        // Then
        assertEquals(0, theListOfBooks10.size());
        verify(libraryDatabaseMock, times(0)).listBooksWithCondition(anyString());
    }


    @Test
    public void listBooksInHandsOfWithCondition0(){
        // Given
        LibraryDatabase libraryDatabaseMock = mock(LibraryDatabase.class);
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        LibraryUser user1 = new LibraryUser("Jan", "Lipa", "67856486");
        ArrayList<Book> booksToRent = new ArrayList<Book>();
        {when(libraryDatabaseMock.listBooksInHandsOf(user1)).thenReturn(booksToRent);}
        // When
        List<Book> books  = libraryDatabaseMock.listBooksInHandsOf(user1);
        // Assert
        assertEquals(0, books.size());
    }

    @Test
    public void listBooksInHandsOfWithCondition1(){
        // Given
        LibraryDatabase libraryDatabaseMock = mock(LibraryDatabase.class);
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        LibraryUser user1 = new LibraryUser("Jan", "Lipa", "67856486");
        ArrayList<Book> booksToRent = new ArrayList<Book>();
        Book book1 = new Book("title1","author1",1999 );
        booksToRent.add(book1);
        boolean rentedBook = bookLibrary.rentABook(user1,book1);
        if(rentedBook){when(libraryDatabaseMock.listBooksInHandsOf(user1)).thenReturn(booksToRent);}
        // When
        List<Book> books  = libraryDatabaseMock.listBooksInHandsOf(user1);
        // Assert
        assertEquals(1, books.size());
    }

    @Test
    public void listBooksInHandsOfWithCondition5(){
        // Given
        LibraryDatabase libraryDatabaseMock = mock(LibraryDatabase.class);
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        LibraryUser user1 = new LibraryUser("Jan", "Lipa", "67856486");
        ArrayList<Book> booksToRent = new ArrayList<Book>();
        Book book1 = new Book("title1","author1",1999 );
        Book book2 = new Book("title2","author2",1991 );
        Book book3 = new Book("title3","author3",1992 );
        Book book4 = new Book("title4","author4",1993 );
        Book book5 = new Book("title5","author5",1994 );
        booksToRent.add(book1);
        booksToRent.add(book2);
        booksToRent.add(book3);
        booksToRent.add(book4);
        booksToRent.add(book5);
        boolean rentedBook1 = bookLibrary.rentABook(user1,book1);
        boolean rentedBook2 = bookLibrary.rentABook(user1,book2);
        boolean rentedBook3 = bookLibrary.rentABook(user1,book3);
        boolean rentedBook4 = bookLibrary.rentABook(user1,book4);
        boolean rentedBook5 = bookLibrary.rentABook(user1,book5);
        if(rentedBook1 && rentedBook2 && rentedBook3 && rentedBook4 && rentedBook5)
        {when(libraryDatabaseMock.listBooksInHandsOf(user1)).thenReturn(booksToRent);}
        // When
        List<Book> books  = libraryDatabaseMock.listBooksInHandsOf(user1);
        // Assert
        assertEquals(5, books.size());
    }


}