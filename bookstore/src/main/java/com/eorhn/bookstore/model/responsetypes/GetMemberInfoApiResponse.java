package com.eorhn.bookstore.model.responsetypes;

import com.eorhn.bookstore.model.entities.Members;

public class GetMemberInfoApiResponse {

    private Members memberInfo;

    public Members getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(Members memberInfo) {
        this.memberInfo = memberInfo;
    }
}
