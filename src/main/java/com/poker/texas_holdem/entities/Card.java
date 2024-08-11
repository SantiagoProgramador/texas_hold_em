package com.poker.texas_holdem.entities;

import com.poker.texas_holdem.helpers.CardMapper;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class Card {
  private String value;
  private String suit;

  public Card(int valueNumber, String suit) {
    this.value = CardMapper.getValue(valueNumber);
    this.suit = CardMapper.getSuit(suit);
  }
}
