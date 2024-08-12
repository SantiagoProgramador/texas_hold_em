package com.poker.texas_holdem.dtos.request;

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
  String hand1;
  @NotNull(message = "Hand 2 can't be null")
  String hand2;
}
