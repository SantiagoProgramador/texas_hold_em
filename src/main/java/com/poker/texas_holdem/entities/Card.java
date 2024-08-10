package com.poker.texas_holdem.entities;

import com.poker.texas_holdem.utils.enums.Suit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Card {
  private String value;
  private Suit suit;
}
