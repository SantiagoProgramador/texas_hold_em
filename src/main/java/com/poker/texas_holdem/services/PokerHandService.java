package com.poker.texas_holdem.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.poker.texas_holdem.dtos.response.PokerHandResponse;
import com.poker.texas_holdem.entities.Card;
import com.poker.texas_holdem.entities.PokerHand;
import com.poker.texas_holdem.helpers.CardMapper;

@Service
public class PokerHandService {

  public PokerHandResponse getWinner(List<Card> hand1Cards, List<Card> hand2Cards) {
    PokerHand hand1 = new PokerHand();
    PokerHand hand2 = new PokerHand();
    hand1.setCards(hand1Cards);
    hand2.setCards(hand2Cards);

    String hand1Type = evaluateHand(hand1);
    String hand2Type = evaluateHand(hand2);

    int hand1Rank = rankHand(hand1Type);
    int hand2Rank = rankHand(hand2Type);

    return PokerHandResponse.builder()
        .winnerHand((hand1Rank > hand2Rank) ? "hand1" : "hand2")
        .compositionWinnerHand((hand1Rank > hand2Rank) ? compositionWinnerHand(hand1.getCards(), hand1Type)
            : compositionWinnerHand(hand2.getCards(), hand2Type))
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
    return isStraightFlush(cards) && cards.stream().anyMatch(c -> c.getValue() == 14);
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
    List<Integer> values = new ArrayList<>();
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
    Map<Integer, Integer> rankCount = getValueCount(cards);
    return rankCount.values().stream().anyMatch(c -> c == count);
  }

  private Map<Integer, Integer> getValueCount(List<Card> cards) {
    Map<Integer, Integer> valueCount = new HashMap<>();
    for (Card card : cards) {
      valueCount.put(card.getValue(), valueCount.getOrDefault(card.getValue(), 0) + 1);
    }
    return valueCount;
  }

  private List<String> compositionWinnerHand(List<Card> cards, String handType) {
    List<String> composition = new ArrayList<>();

    switch (handType) {
      case "Royal Flush":
        String royalFlushSuit = CardMapper.getSuitName(cards.get(0).getSuit());
        composition.add(royalFlushSuit);
        break;
      case "Straight Flush":
        String straightFlushSuit = CardMapper.getSuitName(cards.get(0).getSuit());
        composition.add(straightFlushSuit);
        break;
      case "Flush":
        String flushSuit = CardMapper.getSuitName(cards.get(0).getSuit());
        composition.add(flushSuit);
        break;
      case "Four of a Kind":
        int fourOfAKindValue = cards.get(0).getValue();
        String fourOfAKindSymbol = CardMapper.getSymbolFromValue(fourOfAKindValue);
        composition.add(fourOfAKindSymbol);
        break;
      case "Full House":
        int fullHouseValue = cards.get(0).getValue();
        String fullHouseSymbol = CardMapper.getSymbolFromValue(fullHouseValue);
        composition.add(fullHouseSymbol);
        break;
      default:
        break;
    }
    for (Card c : cards) {
      String valueSymbol = CardMapper.getSymbolFromValue(c.getValue());
      String suitSymbol = CardMapper.getSuit(c.getSuit());
      String card = valueSymbol + suitSymbol;
      composition.add(card);
    }

    return composition;
  }
}
