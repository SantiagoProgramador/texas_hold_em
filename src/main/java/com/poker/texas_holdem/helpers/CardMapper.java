package com.poker.texas_holdem.helpers;

import java.util.Map;

import com.poker.texas_holdem.utils.exceptions.BadRequestException;

public class CardMapper {
  private static final Map<String, Integer> VALUE_TO_NUMBER = Map.ofEntries(
      Map.entry("2", 2),
      Map.entry("3", 3),
      Map.entry("4", 4),
      Map.entry("5", 5),
      Map.entry("6", 6),
      Map.entry("7", 7),
      Map.entry("8", 8),
      Map.entry("9", 9),
      Map.entry("10", 10),
      Map.entry("J", 11),
      Map.entry("Q", 12),
      Map.entry("K", 13),
      Map.entry("A", 14));

  private static final Map<String, String> SUIT = Map.ofEntries(
      Map.entry("S", "Spades"),
      Map.entry("H", "Hearts"),
      Map.entry("D", "Diamonds"),
      Map.entry("C", "Clubs"));

  private CardMapper() {
    throw new AssertionError("Instantiating utility class.");
  }

  public static Integer getValue(String number) {
    Integer value = VALUE_TO_NUMBER.get(number);
    if (value == null) {
      throw new BadRequestException("Invalid card number: " + number);
    }
    return value;
  }

  public static String getSuit(String suit) {
    String validateSuit = SUIT.get(suit);
    if (validateSuit == null) {
      throw new BadRequestException("Invalid card suit: " + suit);
    }
    return suit;
  }
}
