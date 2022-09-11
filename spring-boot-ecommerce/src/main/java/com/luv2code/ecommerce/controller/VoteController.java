package com.luv2code.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.ecommerce.dto.VoteDto;
import com.luv2code.ecommerce.service.VoteService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/vote")
public class VoteController {

     @Autowired	
      VoteService voteService;

    @PostMapping("/voting")
    public ResponseEntity<String> vote(@RequestBody VoteDto voteDto) {
    	
        voteService.vote(voteDto);
    	String message =  voteService.statusmessage ;

        return new ResponseEntity<>(message ,HttpStatus.OK);
    }
}