package com.poker.texas_holdem.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "PokerTexasHolderOnboardTest", version = "1.0", description = "API that compares pairs of poker hands and indicates which one is the winner, what type of hand he won with and what cards made him win."))
public class OpenApiConfig {

}
