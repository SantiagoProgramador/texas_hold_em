package com.poker.texas_holdem.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class PokerHand {
  private String handType;
  private List<Card> cards;
}
