package com.eorhn.bookstore.service;

import com.eorhn.bookstore.model.entities.TblBooks;
import com.eorhn.bookstore.model.requesttypes.booksapis.InsertBookApiRequest;
import com.eorhn.bookstore.model.requesttypes.booksapis.UpdateBookApiRequest;
import com.eorhn.bookstore.model.responsetypes.booksapis.*;
import com.eorhn.bookstore.repository.BooksRepository;
import com.eorhn.bookstore.utilities.ResponseHeaderHelper;
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
                response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse("Book does not exist",02));
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
            response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse("Table is empty",01));
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
                  response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse("Book does not exists!",02));
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse("Update caught exception",-1));
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
                response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse("Book already exists!",03));
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse("Insert caught exception",-1));
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
            response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse("Record does not exists!",04));
        }
        return response;
    }


}
