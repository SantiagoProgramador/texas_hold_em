package com.poker.texas_holdem.dtos.request;

import com.poker.texas_holdem.entities.PokerHand;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class PokerHandRequest {
  @NotEmpty
  PokerHand hand1;
  @NotEmpty
  PokerHand hand2;
}
