package com.deutschebank.trading_app.signal_utils;

import com.deutschebank.trading_algo.Algo;
import com.deutschebank.trading_app.signal.SignalService;
import com.deutschebank.trading_app.signal_utils.strategies.Signal_1;
import com.deutschebank.trading_app.signal_utils.strategies.Signal_Default;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SignalUtilityServiceTest {

	@Mock
	private Algo mockAlgo;

	@Mock
	private SignalUtilityService signalUtilityService;

	@InjectMocks
	private SignalService signalService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

	}

	@Test
	void testProcessSignal_1_Success() {
		int signalId = 1;

		// Create a mock SignalStrategy
		SignalStrategy mockStrategy = Mockito.mock(Signal_1.class);

		// Mock the behavior of the SignalUtilityService
		when(signalUtilityService.getSignalStrategyBySignalId(signalId)).thenReturn(mockStrategy);

		// Call the method under test
		signalService.processSignal(signalId);

		// Verify that the SignalUtilityService methods were called as expected
		verify(signalUtilityService).initializeSignalStrategies();
		verify(signalUtilityService).getSignalStrategyBySignalId(signalId);

		// Verify that the Algo methods were called as expected
		verify(mockStrategy).process(any(Algo.class));
		verify(mockAlgo).doAlgo();
	}

	@Test
	void testProcessSignal_UnknownSignalId() {
		int unknownSignalId = 999;

		SignalStrategy mockDefaultStrategy = Mockito.mock(Signal_Default.class);

		Map<Integer, SignalStrategy> signalStrategies = new HashMap<>();
		signalStrategies.put(123, Mockito.mock(SignalStrategy.class));
		signalStrategies.put(456, Mockito.mock(SignalStrategy.class));
		signalStrategies.put(789, Mockito.mock(SignalStrategy.class));

		when(signalUtilityService.getSignalStrategyBySignalId(unknownSignalId)).thenReturn(mockDefaultStrategy);
		when(signalUtilityService.getSignalStrategies()).thenReturn(signalStrategies);

		// Call the method under test
		signalService.processSignal(unknownSignalId);

		// Verify that the SignalUtilityService methods were called as expected
		verify(signalUtilityService).initializeSignalStrategies();
		verify(signalUtilityService).getSignalStrategyBySignalId(unknownSignalId);

		// Verify that the default strategy was used for the unknown signalId
		verify(mockDefaultStrategy).process(any(Algo.class));
		verify(mockAlgo).doAlgo();
	}

}
