package com.eorhn.bookstore.controller;

import com.eorhn.bookstore.model.enums.BookSortFields;
import com.eorhn.bookstore.model.enums.MemberSortFields;
import com.eorhn.bookstore.model.requesttypes.booksapis.InsertBookApiRequest;
import com.eorhn.bookstore.model.requesttypes.booksapis.UpdateBookApiRequest;
import com.eorhn.bookstore.model.responsetypes.booksapis.*;
import com.eorhn.bookstore.model.responsetypes.membersapis.GetPagedMembersInfoApiResponse;
import com.eorhn.bookstore.service.BooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/books")
@Tags({@Tag(name = "Book Inventory Operations")})
public class BooksController {
    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping(path="/get/{bookId}")
    @Operation(summary = "Returns book by given ID.")
    public ResponseEntity<GetBookApiResponse> getBook(@NumberFormat(style= NumberFormat.Style.NUMBER) @PathVariable long bookId){
        return ResponseEntity.status(HttpStatus.OK).body(booksService.getBook(bookId));
    }

    @GetMapping
    @Operation(summary = "Returns paginated results of books by query parameters.")
    public ResponseEntity<GetPagedBooksInfoApiResponse> getAllMembersPaginated(@RequestParam(defaultValue = "0") int page,
                                                                                 @RequestParam(defaultValue = "2") int sizePerPage,
                                                                                 @RequestParam(defaultValue = "ID") BookSortFields memberSortFields,
                                                                                 @RequestParam(defaultValue = "ASC") Sort.Direction sortDirection){
        Pageable pageable = PageRequest.of(page, sizePerPage, sortDirection, memberSortFields.getColumnName());
        return ResponseEntity.status(HttpStatus.OK).body(booksService.allBooksInfoPaginated(pageable));
    }

    @GetMapping(path="/get/all")
    @Operation(summary = "Returns all books in inventory.")
    public ResponseEntity<GetAllBooksApiResponse> getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(booksService.getAllBooks());
    }

    @PostMapping(path="/update")
    @Operation(summary = "Updates existing row in inventory.")
    public ResponseEntity<UpdateBookInfoApiResponse> updateBook(@RequestBody UpdateBookApiRequest apiRequest){
        return ResponseEntity.status(HttpStatus.OK).body(booksService.updateBook(apiRequest));
    }

    @PostMapping(path="/insert")
    @Operation(summary = "Inserts new book record to table.")
    public ResponseEntity<InsertBookApiResponse> insertBook(@RequestBody @Valid InsertBookApiRequest apiRequest){
        return ResponseEntity.status(HttpStatus.OK).body(booksService.insertBook(apiRequest));
    }
    @DeleteMapping(path="/delete/{bookId}")
    @Operation(summary = "Deletes book record by given ID.")
    public ResponseEntity<DeleteBookApiResponse> deleteBook(@NumberFormat(style= NumberFormat.Style.NUMBER) @PathVariable long bookId){
        return ResponseEntity.status(HttpStatus.OK).body(booksService.deleteBook(bookId));
    }
}
