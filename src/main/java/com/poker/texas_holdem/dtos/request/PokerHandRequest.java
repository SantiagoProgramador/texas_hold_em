package com.poker.texas_holdem.dtos.request;

import com.poker.texas_holdem.entities.PokerHand;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PokerHandRequest {
  @NotNull(message = "Hand 1 can't be null")
  PokerHand hand1;
  @NotNull(message = "Hand 2 can't be null")
  PokerHand hand2;
}
