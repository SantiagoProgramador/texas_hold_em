package com.poker.texas_holdem.controllers;

import java.util.ArrayList;
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
import com.poker.texas_holdem.helpers.CardMapper;
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
    List<Card> hand1Cards = parseCards(pokerHandRequest.getHand1());
    List<Card> hand2Cards = parseCards(pokerHandRequest.getHand2());

    if (hand1Cards.size() != 5 || hand2Cards.size() != 5) {
      throw new BadRequestException("A hand must have 5 cards");
    }
    validateUniqueCards(hand1Cards, hand2Cards);
    return ResponseEntity.ok(pokerHandService.getWinner(hand1Cards, hand2Cards));
  }

  private List<Card> parseCards(String handString) {
    String[] cardStrings = handString.split(" ");
    List<Card> cards = new ArrayList<>();

    for (String cardString : cardStrings) {
      String value = cardString.substring(0, cardString.length() - 1);
      String suit = cardString.substring(cardString.length() - 1);

      Integer cardValue = CardMapper.getValue(value);
      String cardSuit = CardMapper.getSuit(suit);

      cards.add(new Card(cardValue, cardSuit));
    }

    return cards;
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
