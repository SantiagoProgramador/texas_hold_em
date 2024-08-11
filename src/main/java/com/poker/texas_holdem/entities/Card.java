package com.poker.texas_holdem.entities;

import com.poker.texas_holdem.helpers.CardValueMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Card {
  private String value;
  private String suit;

  public Card(int valueNumber, String suit) {
    this.value = CardValueMapper.getValue(valueNumber);
    this.suit = suit;
  }
}
