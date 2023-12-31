package com.assessment.trading_app.signal_utils.strategies;

import com.assessment.trading_algo.Algo;
import com.assessment.trading_app.signal_utils.SignalStrategy;

public class Signal_2 implements SignalStrategy {

	@Override
	public void process(Algo algo) {
		algo.reverse();
		algo.setAlgoParam(1, 80);
		algo.submitToMarket();
	}

}
