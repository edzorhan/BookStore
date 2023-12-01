package com.eorhn.bookstore.model.requesttypes.ordersapis;

import com.eorhn.bookstore.model.dtos.MemberDto;
import jakarta.validation.Valid;

public class PlaceOrderApiRequest {

    private long bookId;
    private long memberId;
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
