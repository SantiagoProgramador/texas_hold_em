package com.poker.texas_holdem.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poker.texas_holdem.dtos.request.PokerHandRequest;
import com.poker.texas_holdem.dtos.response.PokerHandResponse;
import com.poker.texas_holdem.entities.Card;
import com.poker.texas_holdem.entities.PokerHand;
import com.poker.texas_holdem.utils.enums.Suit;

@Service
public class PokerHandService {

  public PokerHandResponse getWinner(PokerHandRequest pokerHandRequest) {
    // logic to get the winner of the poker hand
    // return the winner
    return PokerHandResponse.builder()
        .winnerHand(determineWinner())
        .compositionWinnerHand(pokerHandRequest.getHand1().getCards())
        .winnerHandType(pokerHandRequest.getHand1().getClass().getName())
        .build();
  }

  private PokerHand evaluateHand(List<Card> cards) {
    // Evaluate the hand based on the cards
    // This is a placeholder for the actual implementation
    Card card = new Card("1", Suit.CLUBS);
    System.out.println(card.getValue() + card.getSuit().getInitial());
    return new PokerHand(null);

  }

  private PokerHand compareHands(PokerHand hand1, PokerHand hand2) {
    // Compare the two hands and return the winner
    // This is a placeholder for the actual implementation
    return hand1;
  }

  private String determineWinner() {

    return "hand1";
  }

}
