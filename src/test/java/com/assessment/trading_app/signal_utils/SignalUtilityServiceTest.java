package com.assessment.trading_app.signal_utils;

import com.assessment.trading_app.signal_utils.strategies.Signal_Default;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SignalUtilityServiceTest {

	@InjectMocks
	SignalUtilityService signalUtilityService;

	@BeforeEach
	void setUp() {
		signalUtilityService = new SignalUtilityService(new HashMap<>());
	}

	@Test
	void initializeSignalStrategies() {
		signalUtilityService.initializeSignalStrategies();
		var actual = signalUtilityService.getSignalStrategies();
		assertEquals(List.of(1, 2, 3), actual.keySet().stream().toList());
	}

	@Test
	void getAllStrategyClassNames() {
		var expected = List.of("Signal_1", "Signal_2", "Signal_3");
		var actual = signalUtilityService.getAllStrategyClassNames(SignalUtilityService.STRATEGY_PACKAGE_NAME);
		Collections.sort(actual);
		assertEquals(expected, actual);
	}

	@Test
	void getSignalStrategyBySignalId_returns_defaultStrategy() {
		var actual = signalUtilityService.getSignalStrategyBySignalId(9);
		assertTrue(actual instanceof Signal_Default);
	}

}