package com.eorhn.bookstore.model.requesttypes.membersapis;

import com.eorhn.bookstore.model.dtos.MemberDto;

public class UpdateMemberApiRequest {

    private MemberDto memberInfo;

    public MemberDto getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(MemberDto memberInfo) {
        this.memberInfo = memberInfo;
    }
}