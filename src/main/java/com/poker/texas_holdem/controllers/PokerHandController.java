package com.poker.texas_holdem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poker.texas_holdem.dtos.request.PokerHandRequest;
import com.poker.texas_holdem.dtos.response.PokerHandResponse;
import com.poker.texas_holdem.services.PokerHandService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/poker")
@AllArgsConstructor
public class PokerHandController {

  @Autowired
  private final PokerHandService pokerHandService;

  @PostMapping
  public ResponseEntity<PokerHandResponse> pokerHand(@Validated @RequestBody PokerHandRequest pokerHandRequest) {
    return ResponseEntity.ok(pokerHandService.getWinner(pokerHandRequest));
  }
}
