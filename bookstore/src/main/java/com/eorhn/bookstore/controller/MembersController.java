package com.eorhn.bookstore.controller;

import com.eorhn.bookstore.model.enums.MemberSortFields;
import com.eorhn.bookstore.model.requesttypes.membersapis.InsertMemberApiRequest;
import com.eorhn.bookstore.model.requesttypes.membersapis.UpdateMemberApiRequest;
import com.eorhn.bookstore.model.responsetypes.membersapis.*;
import com.eorhn.bookstore.service.MembersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/members")
@Tags({@Tag(name = "Members Operations")})
public class MembersController {
    private final MembersService membersService;

    public MembersController(MembersService membersService) {
        this.membersService = membersService;
    }

    @GetMapping(path="/get/{memberId}")
    @Operation(summary = "Returns member by given ID.")
    public ResponseEntity<GetMemberInfoApiResponse> getMember(@NumberFormat(style= NumberFormat.Style.NUMBER) @PathVariable long memberId){
        return ResponseEntity.status(HttpStatus.OK).body(membersService.memberInfo(memberId));
    }

    @GetMapping
    @Operation(summary = "Returns paginated results of members by query parameters.")
    public ResponseEntity<GetPagedMembersInfoApiResponse> getAllMembersPaginated(@RequestParam(defaultValue = "0") int page,
                                                                               @RequestParam(defaultValue = "2") int sizePerPage,
                                                                               @RequestParam(defaultValue = "ID") MemberSortFields memberSortFields,
                                                                               @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection){
        Pageable pageable = PageRequest.of(page, sizePerPage, sortDirection, memberSortFields.getColumnName());
        return ResponseEntity.status(HttpStatus.OK).body(membersService.allMembersInfoPaginated(pageable));
    }


    @GetMapping(path="/get/all")
    @Operation(summary = "Returns all members.")
    public ResponseEntity<GetAllMembersInfoApiResponse> getAllMembers(){
        return ResponseEntity.status(HttpStatus.OK).body(membersService.allMembersInfo());
    }

    @PostMapping(path="/update")
    @Operation(summary = "Updates existing member.")
    public ResponseEntity<UpdateMemberInfoApiResponse> updateMember(@RequestBody @Valid UpdateMemberApiRequest apiRequest){
        return ResponseEntity.status(HttpStatus.OK).body(membersService.updateMemberInfo(apiRequest));
    }

    @PostMapping(path="/insert")
    @Operation(summary = "Inserts new member to table.")
    public ResponseEntity<InsertMemberApiResponse> insertMember(@RequestBody @Valid InsertMemberApiRequest apiRequest){
        return ResponseEntity.status(HttpStatus.OK).body(membersService.insertMember(apiRequest));
    }
    @DeleteMapping(path="/delete/{memberId}")
    @Operation(summary = "Deletes book record by given ID.")
    public ResponseEntity<DeleteMemberApiResponse> deleteMember(@NumberFormat(style= NumberFormat.Style.NUMBER) @PathVariable long memberId){
        return ResponseEntity.status(HttpStatus.OK).body(membersService.deleteMember(memberId));
    }
}
