package com.deutschebank.trading_app.signal_utils;

import com.deutschebank.trading_app.signal_utils.strategies.SignalStrategyDefault;
import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

@Service
@AllArgsConstructor
public class SignalUtilityService {

	private final ResourceLoader resourceLoader;

	private final Map<Integer, SignalStrategy> signalStrategies;

	@PostConstruct
	private void initializeSignalStrategies() {
		// Read the configuration file and populate the signalStrategies map
		if (signalStrategies != null) {
			return;
		}
		try {
			Resource resource = resourceLoader.getResource("classpath:signals.json");
			try (Reader reader = new InputStreamReader(resource.getInputStream())) {
				Gson gson = new Gson();
				SignalsConfig signalsConfig = gson.fromJson(reader, SignalsConfig.class);
				for (SignalConfig signalConfig : signalsConfig.getSignals()) {
					var signalStrategy = createStrategyInstance(signalConfig.getAlgorithm());
					if (signalStrategy != null) {
						signalStrategies.put(signalConfig.getSignalId(), signalStrategy);
					}
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private SignalStrategy createStrategyInstance(String configClassName) {
		try {
			Class<?> clazz = Class.forName("com.deutschebank.tradingapp.signal_utils.strategies." + configClassName);
			return (SignalStrategy) clazz.getDeclaredConstructor().newInstance();
		}
		catch (Exception e) {
			return null;
		}
	}

	public SignalStrategy getSignalStrategyBySignalId(int signalId) {
		return signalStrategies.getOrDefault(signalId, new SignalStrategyDefault());
	}

}
