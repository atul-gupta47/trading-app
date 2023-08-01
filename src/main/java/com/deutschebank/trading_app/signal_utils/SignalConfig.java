package com.deutschebank.trading_app.signal_utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignalConfig {

	private int signalId;

	private String algorithm;

}
