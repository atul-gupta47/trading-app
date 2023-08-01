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

/**
 * Service class responsible for managing and providing signal strategies.
 */
@Service
@AllArgsConstructor
public class SignalUtilityService {

	private static final String STRATEGY_CLASS_NAME_PREFIX = "com.deutschebank.tradingapp.signal_utils.strategies.";

	private static final String STRATEGY_INSTANTIATION_ERROR_TEMPLATE = "Unable to instantiate Signal strategy class for signalId: %s";

	private final ResourceLoader resourceLoader;

	private final Map<Integer, SignalStrategy> signalStrategies;

	/**
	 * Initialize the signalStrategies map with SignalStrategy instances from the
	 * signals.json configuration file. If the map is already initialized, this method
	 * will return without re-initializing it.
	 */
	@PostConstruct
	private void initializeSignalStrategies() {
		if (signalStrategies != null && !signalStrategies.isEmpty()) {
			// If the map is already initialized, return (prevents re-initialization)
			return;
		}
		try {
			Resource resource = resourceLoader.getResource("classpath:signals.json");
			try (Reader reader = new InputStreamReader(resource.getInputStream())) {
				Gson gson = new Gson();
				SignalsConfig signalsConfig = gson.fromJson(reader, SignalsConfig.class);
				for (SignalConfig signalConfig : signalsConfig.getSignals()) {
					SignalStrategy signalStrategy = createStrategyInstance(signalConfig.getAlgorithm());
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

	/**
	 * Helper method to create an instance of a SignalStrategy based on the configuration
	 * class name.
	 * @param configClassName The fully qualified name of the SignalStrategy configuration
	 * class.
	 * @return An instance of SignalStrategy if successful, or throw exception if the instantiation
	 * failed.
	 */
	private SignalStrategy createStrategyInstance(String configClassName) {
		try {
			Class<?> clazz = Class.forName(STRATEGY_CLASS_NAME_PREFIX + configClassName);
			return (SignalStrategy) clazz.getDeclaredConstructor().newInstance();
		}
		catch (Exception e) {
			throw new SignalStrategyException(String.format(STRATEGY_INSTANTIATION_ERROR_TEMPLATE, configClassName));
		}
	}

	public SignalStrategy getSignalStrategyBySignalId(int signalId) {
		return signalStrategies.getOrDefault(signalId, new SignalStrategyDefault());
	}

}
