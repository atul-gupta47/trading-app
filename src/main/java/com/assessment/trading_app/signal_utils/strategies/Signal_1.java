package com.assessment.trading_app.signal_utils.strategies;

import com.assessment.trading_algo.Algo;
import com.assessment.trading_app.signal_utils.SignalStrategy;

public class Signal_1 implements SignalStrategy {

	@Override
	public void process(Algo algo) {
		algo.setUp();
		algo.setAlgoParam(1, 60);
		algo.performCalc();
		algo.submitToMarket();
	}

}
