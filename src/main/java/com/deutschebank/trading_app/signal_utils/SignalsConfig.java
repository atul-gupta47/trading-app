package com.deutschebank.trading_app.signal_utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Getter
@Setter
@Builder
public class SignalsConfig {

	private List<SignalConfig> signals;

}
