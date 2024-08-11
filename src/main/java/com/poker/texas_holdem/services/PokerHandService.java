package com.poker.texas_holdem.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.poker.texas_holdem.dtos.request.PokerHandRequest;
import com.poker.texas_holdem.dtos.response.PokerHandResponse;
import com.poker.texas_holdem.entities.Card;
import com.poker.texas_holdem.entities.PokerHand;

@Service
public class PokerHandService {

  public PokerHandResponse getWinner() {
    // logic to get the winner of the poker hand
    // return the winner
    return new PokerHandResponse();
  }

}
