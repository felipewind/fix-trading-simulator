package com.helesto.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class NegotiationDto {

    public NegotiationDto() {
    }

    public NegotiationDto(boolean automaticTrade, int automaticTradeSeconds) {
        this.automaticTrade = automaticTrade;
        this.automaticTradeSeconds = automaticTradeSeconds;
    }

    @Schema(defaultValue = "false")
    private boolean automaticTrade;

    private int automaticTradeSeconds;

    public boolean isAutomaticTrade() {
        return automaticTrade;
    }

    public void setAutomaticTrade(boolean automaticTrade) {
        this.automaticTrade = automaticTrade;
    }

    public int getAutomaticTradeSeconds() {
        return automaticTradeSeconds;
    }

    public void setAutomaticTradeSeconds(int automaticTradeSeconds) {
        this.automaticTradeSeconds = automaticTradeSeconds;
    }

}
