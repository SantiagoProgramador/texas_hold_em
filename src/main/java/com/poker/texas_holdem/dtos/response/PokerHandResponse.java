package com.poker.texas_holdem.dtos.response;

import java.util.List;

import com.poker.texas_holdem.entities.Card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class PokerHandResponse {
  private String winnerHand;
  private String winnerHandType;
  private List<Card> compositionWinnerHand;
}
