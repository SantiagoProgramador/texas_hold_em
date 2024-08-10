package com.poker.texas_holdem.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Suit {
  SPADES("S"),
  HEARTS("H"),
  DIAMONDS("D"),
  CLUBS("C");

  private final String initial;
}
