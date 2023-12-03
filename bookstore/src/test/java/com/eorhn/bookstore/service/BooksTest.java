package com.eorhn.bookstore.service;

import com.eorhn.bookstore.model.dtos.BookDto;
import com.eorhn.bookstore.model.entities.TblBooks;
import com.eorhn.bookstore.model.enums.Errors;
import com.eorhn.bookstore.model.requesttypes.booksapis.InsertBookApiRequest;
import com.eorhn.bookstore.model.requesttypes.booksapis.UpdateBookApiRequest;
import com.eorhn.bookstore.model.responsetypes.booksapis.*;
import com.eorhn.bookstore.repository.BooksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BooksTest {

    @Mock
    BooksRepository booksRepository;
    @InjectMocks BooksService booksService;

    private TblBooks tblBooks;
    private List<TblBooks> booksList;
    private InsertBookApiRequest insertBookApiRequest;
    private UpdateBookApiRequest updateBookApiRequest;
    private BookDto bookDto;

    @BeforeEach
    void setupMocks(){
        this.tblBooks = new TblBooks();
        tblBooks.setBookId(1);
        tblBooks.setBookName("The Hobbit");
        tblBooks.setPrice(new BigDecimal(10));
        tblBooks.setAuthor("J.R.R Tolkien");
        tblBooks.setStockCount(10);

        this.booksList = new ArrayList<>();
        booksList.add(tblBooks);

        insertBookApiRequest = new InsertBookApiRequest();
        updateBookApiRequest = new UpdateBookApiRequest();
        bookDto = new BookDto();
        bookDto.setBookName(tblBooks.getBookName());
        bookDto.setBookId(tblBooks.getBookId());
        bookDto.setAuthor(tblBooks.getAuthor());
        bookDto.setPrice(tblBooks.getPrice());
        bookDto.setStockCount(tblBooks.getStockCount());
        insertBookApiRequest.setBookDto(bookDto);
        updateBookApiRequest.setBookDto(bookDto);
    }


    @Test
    @DisplayName("Unit test for successful response from getBook method.")
    void testGetBook(){
        when(booksRepository.findById(1)).thenReturn(Optional.of(tblBooks));
        GetBookApiResponse returned = this.booksService.getBook(1);
        assertEquals(returned.getResponseHeader().isSuccess(),true);
        assertEquals(returned.getBookInfo().getBookId(),1);
        assertEquals(returned.getBookInfo().getBookName(),"The Hobbit");
    }

    @Test
    @DisplayName("Unit test for unsuccessful response from getBook method.")
    void testGetBookEmpty(){
        when(booksRepository.findById(any(long.class))).thenReturn(Optional.empty());
        GetBookApiResponse returned = this.booksService.getBook(99);
        assertFalse(returned.getResponseHeader().isSuccess());
        assertEquals(returned.getResponseHeader().getMessage(), Errors.BOOK_NOT_FOUND.message);
        assertEquals(returned.getResponseHeader().getCode(), Errors.BOOK_NOT_FOUND.code);
    }

    @Test
    @DisplayName("Unit test for successful response from getAllBooks method.")
    void testGetAllBooks(){
        when(booksRepository.findAll()).thenReturn(booksList);
        GetAllBooksApiResponse returned = this.booksService.getAllBooks();
        assertTrue(returned.getResponseHeader().isSuccess());
    }

    @Test
    @DisplayName("Unit test for empty books table response from getAllBooks method.")
    void testEmptyBooksTable(){
        when(booksRepository.findAll()).thenReturn(new ArrayList<>());
        GetAllBooksApiResponse returned = this.booksService.getAllBooks();
        assertFalse(returned.getResponseHeader().isSuccess());
        assertEquals(returned.getResponseHeader().getMessage(),Errors.TABLE_IS_EMPTY.message);
        assertEquals(returned.getResponseHeader().getCode(),Errors.TABLE_IS_EMPTY.code);

    }

    @Test
    @DisplayName("Unit test for successful insert to books table with insertBooks method.")
    void testInsertBook(){
        when(booksRepository.findById(any(long.class))).thenReturn(Optional.empty());
        InsertBookApiResponse returned = this.booksService.insertBook(insertBookApiRequest);
        assertTrue(returned.getResponseHeader().isSuccess());
        assertEquals(returned.getResponseHeader().getMessage(),"New book added.");
    }

    @Test
    @DisplayName("Unit test for inserting duplicate row to books table.")
    void testInsertExistingBook(){
        when(booksRepository.findById(1)).thenReturn(Optional.of(tblBooks));
        InsertBookApiResponse returned = this.booksService.insertBook(insertBookApiRequest);
        assertFalse(returned.getResponseHeader().isSuccess());
        assertEquals(returned.getResponseHeader().getMessage(),Errors.BOOK_ALREADY_EXISTS.message);
        assertEquals(returned.getResponseHeader().getCode(),Errors.BOOK_ALREADY_EXISTS.code);

    }

    @Test
    @DisplayName("Unit test for successful updating of books table.")
    void testUpdateBook(){
        when(booksRepository.findById(1)).thenReturn(Optional.of(tblBooks));
        UpdateBookInfoApiResponse returned = this.booksService.updateBook(updateBookApiRequest);
        assertTrue(returned.getResponseHeader().isSuccess());
        assertEquals(returned.getResponseHeader().getMessage(),"Book information updated.");
    }

    @Test
    @DisplayName("Unit test for updating nonexistent row in table books.")
    void testUpdateNonExistentBook(){
        when(booksRepository.findById(1)).thenReturn(Optional.empty());
        UpdateBookInfoApiResponse returned = this.booksService.updateBook(updateBookApiRequest);
        assertFalse(returned.getResponseHeader().isSuccess());
        assertEquals(returned.getResponseHeader().getMessage(),Errors.BOOK_NOT_FOUND.message);
        assertEquals(returned.getResponseHeader().getCode(),Errors.BOOK_NOT_FOUND.code);
    }

    @Test
    @DisplayName("Unit test for deleting row in table books.")
    void testDeleteBook(){
        when(booksRepository.existsById(any(long.class))).thenReturn(true);
        DeleteBookApiResponse returned = this.booksService.deleteBook(1);
        assertTrue(returned.getResponseHeader().isSuccess());
        assertEquals(returned.getResponseHeader().getMessage(),"Record deleted.");
    }

    @Test
    @DisplayName("Unit test for deleting nonexistent row in table books.")
    void testDeleteNonExistentBook(){
        when(booksRepository.existsById(any(long.class))).thenReturn(false);
        DeleteBookApiResponse returned = this.booksService.deleteBook(1);
        assertFalse(returned.getResponseHeader().isSuccess());
        assertEquals(returned.getResponseHeader().getMessage(),Errors.DELETE_NOT_FOUND.message);
        assertEquals(returned.getResponseHeader().getCode(),Errors.DELETE_NOT_FOUND.code);
    }
}
