package com.eorhn.bookstore.model.responsetypes.ordersapis;

import com.eorhn.bookstore.model.entities.TblMembers;
import com.eorhn.bookstore.model.entities.TblOrders;
import com.eorhn.bookstore.model.interfaces.ApiResponse;
import com.eorhn.bookstore.model.responsetypes.ResponseHeader;

import java.util.List;

public class GetAllOrdersApiResponse implements ApiResponse {

    private ResponseHeader responseHeader;
    private List<TblOrders> orders;

    public List<TblOrders> getOrders() {
        return orders;
    }

    public void setOrders(List<TblOrders> orders) {
        this.orders = orders;
    }

    @Override
    public ResponseHeader getResponseHeader() {
        return this.responseHeader;
    }

    @Override
    public void setResponseHeader(ResponseHeader header) {
        this.responseHeader = header;
    }
}
