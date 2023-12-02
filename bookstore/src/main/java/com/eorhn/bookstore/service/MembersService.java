package com.eorhn.bookstore.service;

import com.eorhn.bookstore.model.dtos.MemberDto;
import com.eorhn.bookstore.model.entities.TblMembers;
import com.eorhn.bookstore.model.requesttypes.membersapis.InsertMemberApiRequest;
import com.eorhn.bookstore.model.requesttypes.membersapis.UpdateMemberApiRequest;
import com.eorhn.bookstore.model.responsetypes.membersapis.*;
import com.eorhn.bookstore.repository.MembersRepository;
import com.eorhn.bookstore.utilities.ResponseHeaderHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembersService {

    private final MembersRepository membersRepository;

    public MembersService(MembersRepository membersRepository){
        this.membersRepository = membersRepository;
    }


    public GetMemberInfoApiResponse memberInfo(long id){
        GetMemberInfoApiResponse response = new GetMemberInfoApiResponse();
        MemberDto memberInfo = new MemberDto();
        Optional<TblMembers> tblMembers = membersRepository.findById(id);

            if (tblMembers.isPresent()){
                response.setMemberInfo(tblMembers.get());
                response.setResponseHeader(ResponseHeaderHelper.constructSuccessResponse());
            }
            else {
                response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse("Member does not exist",02));

            }
            return response;
    }

    public GetAllMembersInfoApiResponse allMembersInfo(){
        GetAllMembersInfoApiResponse response = new GetAllMembersInfoApiResponse();
        TblMembers tblMembers = new TblMembers();
        Optional<List<TblMembers>> allMembers = Optional.of(membersRepository.findAll());
        if (!allMembers.get().isEmpty()){
            response.setMemberInfo(allMembers.get());
            response.setResponseHeader(ResponseHeaderHelper.constructSuccessResponse());
        }
        else {
            response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse("Table is empty",01));
        }
        return response;
    }


    public UpdateMemberInfoApiResponse updateMemberInfo(UpdateMemberApiRequest apiRequest){
        UpdateMemberInfoApiResponse response = new UpdateMemberInfoApiResponse();
        TblMembers tblMembers = new TblMembers();
        try {
            Optional<TblMembers> memberDto = membersRepository.findById(apiRequest.getMemberInfo().getMemberId());
            if (memberDto.isPresent()){
                tblMembers.setMemberId(apiRequest.getMemberInfo().getMemberId());
                tblMembers.setName(apiRequest.getMemberInfo().getName());
                tblMembers.setSurname(apiRequest.getMemberInfo().getSurname());
                tblMembers.setAddress(apiRequest.getMemberInfo().getAddress());
                membersRepository.save(tblMembers);
                response.setResponseHeader(ResponseHeaderHelper.constructSuccessResponse("Member information updated."));
            }
            else{
                  response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse("Member does not exists!",02));
                  return response;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse("Update caught exception",-1));
        }
        return response;
    }

    public InsertMemberApiResponse insertMember(InsertMemberApiRequest apiRequest){
        InsertMemberApiResponse response = new InsertMemberApiResponse();
        TblMembers tblMembers = new TblMembers();
        try {
            Optional<TblMembers> memberDto = membersRepository.findById(apiRequest.getMemberInfo().getMemberId());
            if (memberDto.isEmpty()){
                tblMembers.setMemberId(apiRequest.getMemberInfo().getMemberId());
                tblMembers.setName(apiRequest.getMemberInfo().getName());
                tblMembers.setSurname(apiRequest.getMemberInfo().getSurname());
                tblMembers.setAddress(apiRequest.getMemberInfo().getAddress());
                membersRepository.save(tblMembers);
                response.setResponseHeader(ResponseHeaderHelper.constructSuccessResponse("New member created."));
            }
            else{
                response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse("Member already exists!",03));
                return response;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse("Insert caught exception",-1));
        }
        return response;
    }

    public DeleteMemberApiResponse deleteMember(long id){
        DeleteMemberApiResponse response = new DeleteMemberApiResponse();
        if(membersRepository.existsById(id)){
            membersRepository.deleteById(id);
            response.setResponseHeader(ResponseHeaderHelper.constructSuccessResponse("Record deleted."));
        }
        else{
            response.setResponseHeader(ResponseHeaderHelper.constructErrorResponse("Record does not exists!",04));
        }
        return response;
    }


}
