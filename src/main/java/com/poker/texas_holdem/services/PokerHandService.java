package com.poker.texas_holdem.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.poker.texas_holdem.dtos.request.PokerHandRequest;
import com.poker.texas_holdem.dtos.response.PokerHandResponse;
import com.poker.texas_holdem.entities.Card;
import com.poker.texas_holdem.entities.PokerHand;

@Service
public class PokerHandService {

  public PokerHandResponse getWinner(PokerHandRequest pokerHandRequest) {
    String hand1Type = evaluateHand(pokerHandRequest.getHand1());
    String hand2Type = evaluateHand(pokerHandRequest.getHand2());

    int hand1Rank = rankHand(hand1Type);
    int hand2Rank = rankHand(hand2Type);

    return PokerHandResponse.builder()
        .winnerHand((hand1Rank > hand2Rank) ? "hand1" : "hand2")
        .compositionWinnerHand((hand1Rank > hand2Rank) ? compositionWinnerHand(pokerHandRequest.getHand1().getCards())
            : compositionWinnerHand(pokerHandRequest.getHand2().getCards()))
        .winnerHandType((hand1Rank > hand2Rank) ? hand1Type : hand2Type)
        .build();
  }

  public String evaluateHand(PokerHand hand) {
    List<Card> cards = hand.getCards();

    if (isRoyalFlush(cards))
      return "Royal Flush";
    if (isStraightFlush(cards))
      return "Straight Flush";
    if (isFourOfAKind(cards))
      return "Four of a Kind";
    if (isFullHouse(cards))
      return "Full House";
    if (isFlush(cards))
      return "Flush";

    return "High card";
  }

  private int rankHand(String handType) {
    switch (handType) {
      case "Royal Flush":
        return 10;
      case "Straight Flush":
        return 9;
      case "Four of a Kind":
        return 8;
      case "Full House":
        return 7;
      case "Flush":
        return 6;
      default:
        return 1;
    }
  }

  private boolean isRoyalFlush(List<Card> cards) {
    return isStraightFlush(cards) && cards.stream().anyMatch(c -> "A".equals(c.getValue()));
  }

  private boolean isStraightFlush(List<Card> cards) {
    return isFlush(cards) && isStraight(cards);
  }

  private boolean isFourOfAKind(List<Card> cards) {
    return hasSameRank(cards, 4);
  }

  private boolean isFullHouse(List<Card> cards) {
    return hasSameRank(cards, 3) && hasSameRank(cards, 2);
  }

  private boolean isFlush(List<Card> cards) {
    String suit = cards.get(0).getSuit();
    return cards.stream().allMatch(c -> Objects.equals(c.getSuit(), suit));
  }

  private boolean isStraight(List<Card> cards) {
    List<String> values = new ArrayList<>();
    for (Card card : cards) {
      values.add(card.getValue());
    }
    Collections.sort(values);

    for (int i = 0; i < values.size() - 1; i++) {
      if (!Objects.equals(values.get(i + 1), values.get(i) + 1)) {
        return false;
      }
    }
    return true;
  }

  private boolean hasSameRank(List<Card> cards, int count) {
    Map<String, Integer> rankCount = getValueCount(cards);
    return rankCount.values().stream().anyMatch(c -> c == count);
  }

  private Map<String, Integer> getValueCount(List<Card> cards) {
    Map<String, Integer> valueCount = new HashMap<>();
    for (Card card : cards) {
      valueCount.put(card.getValue(), valueCount.getOrDefault(card.getValue(), 0) + 1);
    }
    return valueCount;
  }

  private List<String> compositionWinnerHand(List<Card> cards) {
    List<String> composition = new ArrayList<>();
    String card;
    for (int i = 0; i < cards.size(); i++) {
      card = cards.get(i).getValue() + cards.get(i).getSuit();
      composition.add(card);
    }
    return composition;
  }
}
