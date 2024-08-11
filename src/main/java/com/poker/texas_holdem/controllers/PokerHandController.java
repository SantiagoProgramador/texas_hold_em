package com.poker.texas_holdem.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poker.texas_holdem.dtos.request.PokerHandRequest;
import com.poker.texas_holdem.dtos.response.PokerHandResponse;
import com.poker.texas_holdem.entities.Card;
import com.poker.texas_holdem.services.PokerHandService;
import com.poker.texas_holdem.utils.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/poker")
@AllArgsConstructor
public class PokerHandController {

  @Autowired
  private final PokerHandService pokerHandService;

  @PostMapping
  public ResponseEntity<PokerHandResponse> pokerHand(@Validated @RequestBody PokerHandRequest pokerHandRequest) {
    if (pokerHandRequest.getHand1().getCards().size() != 5 || pokerHandRequest.getHand2().getCards().size() != 5) {
      throw new BadRequestException("A hand must have 5 cards");
    }
    validateUniqueCards(pokerHandRequest.getHand1().getCards(), pokerHandRequest.getHand2().getCards());
    return ResponseEntity.ok(pokerHandService.getWinner());
  }

  private void validateUniqueCards(List<Card> hand1, List<Card> hand2) {
    Set<Card> cardSet = new HashSet<>(hand1);
    cardSet.addAll(hand2);

    int totalCards = hand1.size() + hand2.size();

    if (cardSet.size() != totalCards) {
      throw new BadRequestException("There are duplicate cards in the hand");
    }
  }
}
