package com.assessment.trading_app.signal;

import com.assessment.trading_algo.Algo;
import com.assessment.trading_app.signal_utils.SignalStrategy;
import com.assessment.trading_app.signal_utils.SignalUtilityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class SignalServiceTest {

	@Mock
	private Algo mockAlgo;

	@InjectMocks
	private SignalService signalService;

	@Mock
	private SignalUtilityService signalUtilityService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void test_processSignal_shouldWorks() {
		int signalId = 123;

		// Create a mock SignalStrategy
		SignalStrategy mockStrategy = Mockito.mock(SignalStrategy.class);

		// Mock the behavior of the SignalUtilityService
		Mockito.when(signalUtilityService.getSignalStrategyBySignalId(signalId)).thenReturn(mockStrategy);

		// Call the method under test
		signalService.processSignal(signalId);

		// Verify that the SignalUtilityService methods were called as expected
		Mockito.verify(signalUtilityService).initializeSignalStrategies();
		Mockito.verify(signalUtilityService).getSignalStrategyBySignalId(signalId);

		// Verify that the Algo methods were called as expected
		Mockito.verify(mockStrategy).process(mockAlgo);
		Mockito.verify(mockAlgo).doAlgo();
	}

}
