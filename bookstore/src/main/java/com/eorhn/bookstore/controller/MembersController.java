package com.eorhn.bookstore.controller;

import com.eorhn.bookstore.model.responsetypes.GetMemberInfoApiResponse;
import com.eorhn.bookstore.service.MembersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/members")
public class MembersController {
    private final MembersService membersService;

    public MembersController(MembersService membersService) {
        this.membersService = membersService;
    }

    @GetMapping(path="/get/{memberId}")
    public ResponseEntity<GetMemberInfoApiResponse> getMember(@PathVariable long memberId){
        return ResponseEntity.status(HttpStatus.OK).body(membersService.memberInfo(memberId));
    }
}
