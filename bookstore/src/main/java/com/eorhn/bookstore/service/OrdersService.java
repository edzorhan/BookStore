package com.eorhn.bookstore.service;

import com.eorhn.bookstore.model.entities.TblBooks;
import com.eorhn.bookstore.model.entities.TblMembers;
import com.eorhn.bookstore.model.entities.TblOrders;
import com.eorhn.bookstore.model.requesttypes.ordersapis.PlaceOrderApiRequest;
import com.eorhn.bookstore.model.responsetypes.ordersapis.GetAllOrdersApiResponse;
import com.eorhn.bookstore.model.responsetypes.ordersapis.PlaceOrderApiResponse;
import com.eorhn.bookstore.repository.BooksRepository;
import com.eorhn.bookstore.repository.MembersRepository;
import com.eorhn.bookstore.repository.OrdersRepository;
import com.eorhn.bookstore.utilities.ResponseHeaderHelper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final BooksRepository booksRepository;
    private final MembersRepository membersRepository;

    public OrdersService(OrdersRepository ordersRepository,
                         BooksRepository booksRepository,
                         MembersRepository membersRepository)
    {
        this.ordersRepository = ordersRepository;
        this.booksRepository = booksRepository;
        this.membersRepository = membersRepository;
    }


    /**
     * Places an order with requested book and member parameters, Calculates price and saves to Orders table.
     * Calculates remaining stock count and updates book inventory.
     * @Throws NoSuchElementException if book or member not present.
     * @param apiRequest
     * @return PlaceOrderApiResponse
     * @rollback on caught exception
     */
    @Transactional
    public PlaceOrderApiResponse placeOrder(PlaceOrderApiRequest apiRequest){
        PlaceOrderApiResponse response = new PlaceOrderApiResponse();
        TblOrders tblOrders = new TblOrders();

        try {
            TblBooks book = getBookInfo(apiRequest.getBookId());
            TblMembers member = getMemberInfo(apiRequest.getMemberId());
            long currentStock = book.getStockCount();

            if (book.getStockCount() > apiRequest.getOrderCount()){
                tblOrders.setCount(apiRequest.getOrderCount());
                tblOrders.setBookId(apiRequest.getBookId());
                tblOrders.setMemberId(apiRequest.getMemberId());
                tblOrders.setAddress(member.getAddress());
                tblOrders.setPrice(book.getPrice().multiply(BigDecimal.valueOf(apiRequest.getOrderCount())));//Calculating price
                ordersRepository.save(tblOrders);//Placing order

                book.setStockCount(currentStock - apiRequest.getOrderCount());//Calculate remaining stock count
                booksRepository.save(book);//Update book stocks

                response.setResponseHeader(ResponseHeaderHelper.constructSuccessResponse("Order placed!"));
            }
            else {
                response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse("Not enough stocks to place an order!",05));
            }
        } catch (NoSuchElementException ex){
            response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse(ex.getMessage(), 05));
        }catch (Exception e){
            response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse(e.getMessage(), -1));
        }

        return response;

    }

    public GetAllOrdersApiResponse getAllOrders(){
        GetAllOrdersApiResponse response = new GetAllOrdersApiResponse();

        Optional<List<TblOrders>> ordersList = Optional.of(ordersRepository.findAll());
        if (!ordersList.get().isEmpty()){
            response.setOrders(ordersList.get());
            response.setResponseHeader(ResponseHeaderHelper.constructSuccessResponse());
        }
        else {
            response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse("Table is empty",01));
        }
        return response;
    }

    public TblBooks getBookInfo(long bookId){
        Optional<TblBooks> book = booksRepository.findById(bookId);
        if (book.isPresent()){
            return book.get();
        }
        else {
            throw new NoSuchElementException("Book does not exist!");
        }
    }

    public TblMembers getMemberInfo(long memberId){
        Optional<TblMembers> member = membersRepository.findById(memberId);
        if (member.isPresent()){
            return member.get();
        }
        else {
            throw new NoSuchElementException("Member does not exist!");
        }
    }



}
