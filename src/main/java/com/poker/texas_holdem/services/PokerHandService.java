package com.poker.texas_holdem.services;

import java.util.List;

import com.poker.texas_holdem.dtos.request.PokerHandRequest;
import com.poker.texas_holdem.dtos.response.PokerHandResponse;
import com.poker.texas_holdem.entities.Card;
import com.poker.texas_holdem.entities.PokerHand;

public class PokerHandService {

  public PokerHandResponse getWinner(PokerHandRequest pokerHandRequest) {
    // logic to get the winner of the poker hand
    // return the winner
    return new PokerHandResponse(null, null, null);
  }

  public PokerHand evaluateHand(List<Card> cards) {
    // Evaluate the hand based on the cards
    // This is a placeholder for the actual implementation
    return new PokerHand(null, null);

  }

  public PokerHand compareHands(PokerHand hand1, PokerHand hand2) {
    // Compare the two hands and return the winner
    // This is a placeholder for the actual implementation
    return hand1;
  }

  

}
