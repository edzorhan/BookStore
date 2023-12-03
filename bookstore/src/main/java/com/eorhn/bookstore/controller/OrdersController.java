package com.eorhn.bookstore.controller;


import com.eorhn.bookstore.model.requesttypes.ordersapis.PlaceOrderApiRequest;
import com.eorhn.bookstore.model.responsetypes.ordersapis.GetAllOrdersApiResponse;
import com.eorhn.bookstore.model.responsetypes.ordersapis.PlaceOrderApiResponse;
import com.eorhn.bookstore.service.OrdersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/orders")
@Tags({@Tag(name = "Order Operations")})
public class OrdersController {
    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }
    @GetMapping(path="/get/all")
    @Operation(summary = "Returns all placed orders.")
    public ResponseEntity<GetAllOrdersApiResponse> getAllMembers(){
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.getAllOrders());
    }
    @PostMapping(path="/placeOrder")
    @Operation(summary = "Places order on requested item.")
    public ResponseEntity<PlaceOrderApiResponse> placeOrder(@RequestBody @Valid PlaceOrderApiRequest apiRequest){
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.placeOrder(apiRequest));
    }

}
