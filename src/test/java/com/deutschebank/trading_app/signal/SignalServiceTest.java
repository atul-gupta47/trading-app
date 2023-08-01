package com.deutschebank.trading_app.signal;

import com.deutschebank.trading_algo.Algo;
import com.deutschebank.trading_app.signal_utils.SignalStrategy;
import com.deutschebank.trading_app.signal_utils.SignalUtilityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class SignalServiceTest {

	@Mock
	private Algo mockAlgo;

	@Mock
	private SignalUtilityService mockSignalUtilityService;

	@InjectMocks
	private SignalService signalService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testProcessSignal() {
		int signalId = 123; // Replace with the signalId you want to test

		// Create a mock SignalStrategy
		SignalStrategy mockStrategy = Mockito.mock(SignalStrategy.class);

		// Mock the behavior of the SignalUtilityService
		Mockito.when(mockSignalUtilityService.getSignalStrategyBySignalId(signalId)).thenReturn(mockStrategy);

		// Call the method under test
		signalService.processSignal(signalId);

		// Verify that the SignalUtilityService methods were called as expected
		Mockito.verify(mockSignalUtilityService).initializeSignalStrategies();
		Mockito.verify(mockSignalUtilityService).getSignalStrategyBySignalId(signalId);

		// Verify that the Algo methods were called as expected
		Mockito.verify(mockStrategy).process(mockAlgo);
		Mockito.verify(mockAlgo).doAlgo();
	}

}
