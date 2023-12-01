package com.eorhn.bookstore.model.requesttypes.membersapis;

import com.eorhn.bookstore.model.dtos.MemberDto;
import jakarta.validation.Valid;

public class InsertMemberApiRequest {

    @Valid
    private MemberDto memberInfo;

    public MemberDto getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(MemberDto memberInfo) {
        this.memberInfo = memberInfo;
    }
}
