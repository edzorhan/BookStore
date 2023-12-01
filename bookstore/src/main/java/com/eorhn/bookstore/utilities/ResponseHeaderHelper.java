package com.eorhn.bookstore.utilities;

import com.eorhn.bookstore.model.responsetypes.ResponseHeader;

public class ResponseHeaderHelper {

    public static ResponseHeader constructSuccessResponse(){
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setCode(0);
        responseHeader.setSuccess(true);
        responseHeader.setMessage("Success");
        return  responseHeader;
    }

    public static ResponseHeader constructSuccessResponse(String message){
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setCode(0);
        responseHeader.setSuccess(true);
        responseHeader.setMessage(message);
        return  responseHeader;
    }

    public static ResponseHeader constructErrorResponse(String message,long code){
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setMessage(message);
        responseHeader.setCode(code);
        responseHeader.setSuccess(false);
        return responseHeader;
    }
}
