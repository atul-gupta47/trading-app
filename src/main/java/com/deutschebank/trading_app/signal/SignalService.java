package com.deutschebank.trading_app.signal;

import com.deutschebank.trading_algo.Algo;
import com.deutschebank.trading_app.signal_utils.SignalUtilityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignalService {

	private final Algo algo;

	private SignalUtilityService signalUtilityService;

	public void processSignal(int signalId) {
		var strategy = signalUtilityService.getSignalStrategyBySignalId(signalId);
		strategy.process(algo);
		algo.doAlgo();
	}

}
