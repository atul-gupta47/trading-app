package com.assessment.trading_app.signal_utils;

import com.assessment.trading_app.signal_utils.strategies.Signal_Default;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Service class responsible for managing and providing signal strategies.
 */
@Service
@AllArgsConstructor
@Slf4j
public class SignalUtilityService {

	public static final String STRATEGY_PACKAGE_NAME = "com.assessment.trading_app.signal_utils.strategies";

	private static final String DEFAULT_SIGNAL_STRATEGY_CLASS_NAME = STRATEGY_PACKAGE_NAME + ".Signal_Default";

	private static final String STRATEGY_INSTANTIATION_ERROR_TEMPLATE = "Unable to instantiate Signal strategy class for signalId: %s";

	private static final String SIGNAL_STRATEGY_CLASS_NAME_SEPARATOR = "_";

	private Map<Integer, SignalStrategy> signalStrategies;

	/**
	 * Initialize the signalStrategies map with SignalStrategy instances from the
	 * signals.json configuration file. If the map is already initialized, this method
	 * will return without re-initializing it.
	 */
	public void initializeSignalStrategies() {
		if (signalStrategies != null && !signalStrategies.isEmpty()) {
			// If the map is already initialized, return (prevents re-initialization)
			return;
		}
		try {
			SignalsConfig signalsConfig = classNameToSignalConfig(getAllStrategyClassNames(STRATEGY_PACKAGE_NAME));
			for (SignalConfig signalConfig : signalsConfig.getSignals()) {
				SignalStrategy signalStrategy = createStrategyInstance(signalConfig.getAlgorithm());
				signalStrategies.put(signalConfig.getSignalId(), signalStrategy);
			}
			log.info("Signal strategies initialized");
		}
		catch (NullPointerException e) {
			log.error("Null pointer exception {}", e.getMessage());
		}
	}

	/**
	 * Helper method to create an instance of a SignalStrategy based on the configuration
	 * class name.
	 * @param configClassName The fully qualified name of the SignalStrategy configuration
	 * class.
	 * @return An instance of SignalStrategy if successful, or throw exception if the
	 * instantiation failed.
	 */
	private SignalStrategy createStrategyInstance(String configClassName) {
		try {
			log.info("Create strategy instance for class " + configClassName);
			Class<?> clazz = Class.forName(STRATEGY_PACKAGE_NAME + "." + configClassName);
			return (SignalStrategy) clazz.getDeclaredConstructor().newInstance();
		}
		catch (Exception e) {
			throw new SignalStrategyException(String.format(STRATEGY_INSTANTIATION_ERROR_TEMPLATE, configClassName));
		}
	}

	/**
	 * Utility class to retrieve all strategy class names within the specified package.
	 * @param packageName The package name to scan for strategy classes.
	 * @return A list of fully qualified class names for all strategy classes found in the
	 * package.
	 */
	public List<String> getAllStrategyClassNames(String packageName) {
		List<String> classNames = new ArrayList<>();
		String packagePath = packageName.replace('.', File.separatorChar);

		// Get the class loader
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		// Get the package resource path
		URL packageUrl = classLoader.getResource(packagePath);
		if (packageUrl != null) {
			File packageDirectory = new File(packageUrl.getFile());
			if (packageDirectory.isDirectory()) {
				// Iterate through files in the package directory
				File[] files = packageDirectory.listFiles();
				if (files != null) {
					for (File file : files) {
						if (file.isFile() && file.getName().endsWith(".class")) {
							// Extract class name from file name and add to the list
							String className = packageName + '.'
									+ file.getName().substring(0, file.getName().length() - 6);
							if (!DEFAULT_SIGNAL_STRATEGY_CLASS_NAME.equals(className)) {
								classNames.add(className.split(STRATEGY_PACKAGE_NAME + ".")[1]);
							}
						}
					}
				}
			}
		}

		return classNames;
	}

	/**
	 * Converts a list of class names into a SignalsConfig object by extracting signal IDs
	 * and algorithms from the class names.
	 * @param classNames A list of fully qualified class names representing strategy
	 * classes.
	 * @return A SignalsConfig object containing SignalConfig instances for each valid
	 * class name.
	 */
	private SignalsConfig classNameToSignalConfig(List<String> classNames) {
		var signals = classNames.stream().map(className -> {
			String[] parts = className.split(SIGNAL_STRATEGY_CLASS_NAME_SEPARATOR);
			if (parts.length == 2) {
				String algorithm = parts[0]; // should be string "Signal"
				int signalId = Integer.parseInt(parts[1]); // should be signalId: integer
				return SignalConfig.builder().signalId(signalId)
						.algorithm(buildSignalStrategyClassName(algorithm, signalId)).build();
			}
			else {
				log.debug("Invalid class name format: " + className);
				return null;
			}
		}).filter(Objects::nonNull).toList();
		return SignalsConfig.builder().signals(signals).build();
	}

	private String buildSignalStrategyClassName(String algorithm, int signalId) {
		return algorithm + "_" + signalId;
	}

	public SignalStrategy getSignalStrategyBySignalId(int signalId) {
		return signalStrategies.getOrDefault(signalId, new Signal_Default());
	}

	public Map<Integer, SignalStrategy> getSignalStrategies() {
		return signalStrategies;
	}

}
