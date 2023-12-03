package com.eorhn.bookstore.service;

import com.eorhn.bookstore.model.entities.TblBooks;
import com.eorhn.bookstore.model.enums.Errors;
import com.eorhn.bookstore.model.requesttypes.booksapis.InsertBookApiRequest;
import com.eorhn.bookstore.model.requesttypes.booksapis.UpdateBookApiRequest;
import com.eorhn.bookstore.model.responsetypes.booksapis.*;
import com.eorhn.bookstore.repository.BooksRepository;
import com.eorhn.bookstore.utilities.ResponseHeaderHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    private final BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository){
        this.booksRepository = booksRepository;
    }


    public GetBookApiResponse getBook(long id){
        GetBookApiResponse response = new GetBookApiResponse();

        Optional<TblBooks> tblBooks = booksRepository.findById(id);
            if (tblBooks.isPresent()){
                response.setBookInfo(tblBooks.get());
                response.setResponseHeader(ResponseHeaderHelper.constructSuccessResponse());
            }
            else {
                response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse(Errors.BOOK_NOT_FOUND.message,Errors.BOOK_NOT_FOUND.code));
            }
            return response;
    }

    /**
     * Returns paginated and sorted list of all books by given Page parameters.
     * @param pageable
     * @return GetPagedMembersInfoApiResponse
     */
    public GetPagedBooksInfoApiResponse allBooksInfoPaginated(Pageable pageable){
        GetPagedBooksInfoApiResponse response = new GetPagedBooksInfoApiResponse();
        Optional<Page<TblBooks>> allBooks = Optional.of(booksRepository.findAll(pageable));
        if (!allBooks.get().isEmpty()) {
            response.setBookInfo(allBooks.get().getContent());
            response.setResponseHeader(ResponseHeaderHelper.constructSuccessResponse());
        }
        else {
            response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse(Errors.TABLE_IS_EMPTY.message,Errors.TABLE_IS_EMPTY.code));
        }

        return response;
    }

    public GetAllBooksApiResponse getAllBooks(){
        GetAllBooksApiResponse response = new GetAllBooksApiResponse();

        Optional<List<TblBooks>> booksList = Optional.of(booksRepository.findAll());
        if (!booksList.get().isEmpty()){
            response.setBooksList(booksList.get());
            response.setResponseHeader(ResponseHeaderHelper.constructSuccessResponse());
        }
        else {
            response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse(Errors.TABLE_IS_EMPTY.message, Errors.TABLE_IS_EMPTY.code));
        }
        return response;
    }


    public UpdateBookInfoApiResponse updateBook(UpdateBookApiRequest apiRequest){
        UpdateBookInfoApiResponse response = new UpdateBookInfoApiResponse();
        TblBooks tblBooks = new TblBooks();
        try {
            Optional<TblBooks> bookInfo = booksRepository.findById(apiRequest.getBookDto().getBookId());
            if (bookInfo.isPresent()){
                tblBooks.setBookId(apiRequest.getBookDto().getBookId());
                tblBooks.setBookName(apiRequest.getBookDto().getBookName());
                tblBooks.setAuthor(apiRequest.getBookDto().getAuthor());
                tblBooks.setPrice(apiRequest.getBookDto().getPrice());
                booksRepository.save(tblBooks);
                response.setResponseHeader(ResponseHeaderHelper.constructSuccessResponse("Book information updated."));
            }
            else{
                  response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse(Errors.BOOK_NOT_FOUND.message,Errors.BOOK_NOT_FOUND.code));
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse(Errors.UPDATE_GENERAL_EXCEPTION.message, Errors.UPDATE_GENERAL_EXCEPTION.code));
        }
        return response;
    }

    public InsertBookApiResponse insertBook(InsertBookApiRequest apiRequest){
        InsertBookApiResponse response = new InsertBookApiResponse();
        TblBooks tblBooks = new TblBooks();
        try {
            Optional<TblBooks> book = booksRepository.findById(apiRequest.getBookDto().getBookId());
            if (book.isEmpty()){
                tblBooks.setBookId(apiRequest.getBookDto().getBookId());
                tblBooks.setBookName(apiRequest.getBookDto().getBookName());
                tblBooks.setAuthor(apiRequest.getBookDto().getAuthor());
                tblBooks.setPrice(apiRequest.getBookDto().getPrice());
                tblBooks.setStockCount(apiRequest.getBookDto().getStockCount());
                booksRepository.save(tblBooks);
                response.setResponseHeader(ResponseHeaderHelper.constructSuccessResponse("New book added."));
            }
            else{
                response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse(Errors.BOOK_ALREADY_EXISTS.message,Errors.BOOK_ALREADY_EXISTS.code));
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse(Errors.INSERT_GENERAL_EXCEPTION.message,Errors.INSERT_GENERAL_EXCEPTION.code));
        }
        return response;
    }

    public DeleteBookApiResponse deleteBook(long id){
        DeleteBookApiResponse response = new DeleteBookApiResponse();
        if(booksRepository.existsById(id)){
            booksRepository.deleteById(id);
            response.setResponseHeader(ResponseHeaderHelper.constructSuccessResponse("Record deleted."));
        }
        else{
            response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse(Errors.DELETE_NOT_FOUND.message,Errors.DELETE_NOT_FOUND.code));
        }
        return response;
    }


}
