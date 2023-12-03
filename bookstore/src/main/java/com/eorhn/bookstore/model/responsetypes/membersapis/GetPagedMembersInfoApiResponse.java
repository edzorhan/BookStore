package com.eorhn.bookstore.model.responsetypes.membersapis;

import com.eorhn.bookstore.model.dtos.MemberDto;
import com.eorhn.bookstore.model.entities.TblMembers;
import com.eorhn.bookstore.model.interfaces.ApiResponse;
import com.eorhn.bookstore.model.responsetypes.ResponseHeader;
import org.springframework.data.domain.Page;

import java.util.List;

public class GetPagedMembersInfoApiResponse implements ApiResponse {

    private ResponseHeader responseHeader;
    private List<TblMembers> memberInfo;


    public List<TblMembers> getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(List<TblMembers> memberInfo) {
        this.memberInfo = memberInfo;
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
