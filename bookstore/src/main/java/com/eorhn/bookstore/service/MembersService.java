package com.eorhn.bookstore.service;

import com.eorhn.bookstore.model.responsetypes.GetMemberInfoApiResponse;
import com.eorhn.bookstore.repository.MembersRepository;
import org.springframework.stereotype.Service;

@Service
public class MembersService {

    private final MembersRepository membersRepository;

    public MembersService(MembersRepository membersRepository){
        this.membersRepository = membersRepository;
    }

    public GetMemberInfoApiResponse memberInfo(long id){
        GetMemberInfoApiResponse response = new GetMemberInfoApiResponse();
        response.setMemberInfo(membersRepository.findBymemberId(id));
        return response;
    }

}
