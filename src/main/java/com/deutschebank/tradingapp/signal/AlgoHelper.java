package com.deutschebank.tradingapp.signal;

import com.deutschebank.tradingalgo.Algo;
import com.deutschebank.tradingapp.signal_processing.SignalConfig;
import com.deutschebank.tradingapp.signal_processing.SignalStrategy;
import com.deutschebank.tradingapp.signal_processing.SignalStrategyDefault;
import com.deutschebank.tradingapp.signal_processing.SignalsConfig;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

@Component
@AllArgsConstructor
public class AlgoHelper {

	private final Algo algo;

	private final ResourceLoader resourceLoader;

	private final Map<Integer, SignalStrategy> signalStrategies;

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
			Class<?> clazz = Class
					.forName("com.deutschebank.tradingapp.signal_processing.strategies." + configClassName);
			return (SignalStrategy) clazz.getDeclaredConstructor().newInstance();
		}
		catch (Exception e) {
			return null;
		}
	}

	public void processSignal(int signal) {
		initializeSignalStrategies();
		SignalStrategy strategy = signalStrategies.getOrDefault(signal, new SignalStrategyDefault());
		strategy.process(algo);
		algo.doAlgo();
	}

}
