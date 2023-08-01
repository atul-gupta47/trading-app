package com.assessment.trading_app.signal;

import com.assessment.trading_algo.Algo;
import com.assessment.trading_app.signal_utils.SignalUtilityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignalService {

	private final Algo algo;

	private SignalUtilityService signalUtilityService;

	public void processSignal(int signalId) {
		signalUtilityService.initializeSignalStrategies();
		var strategy = signalUtilityService.getSignalStrategyBySignalId(signalId);
		strategy.process(algo); // Process the strategy using the provided Algo
		algo.doAlgo(); // Perform the Algo action
	}

}
