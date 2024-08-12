package com.poker.texas_holdem.dtos.response;

import java.util.List;

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
public class PokerHandResponse {
  private String winnerHand;
  private String winnerHandType;
  private List<String> compositionWinnerHand;
}
