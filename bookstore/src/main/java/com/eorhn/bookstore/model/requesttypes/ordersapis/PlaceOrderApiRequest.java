package com.eorhn.bookstore.model.requesttypes.ordersapis;

import com.eorhn.bookstore.model.dtos.MemberDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

public class PlaceOrderApiRequest {
    @Positive(message = "Book ID must be bigger than zero!")
    private long bookId;
    @Positive(message = "Member ID must be bigger than zero!")
    private long memberId;
    @Positive(message = "Order count must be bigger than zero!")
    private long orderCount;

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(long orderCount) {
        this.orderCount = orderCount;
    }
}
