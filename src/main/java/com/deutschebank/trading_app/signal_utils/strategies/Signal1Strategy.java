package com.deutschebank.trading_app.signal_utils.strategies;

import com.deutschebank.trading_algo.Algo;
import com.deutschebank.trading_app.signal_utils.SignalStrategy;

public class Signal1Strategy implements SignalStrategy {

	@Override
	public void process(Algo algo) {
		algo.setUp();
		algo.setAlgoParam(1, 60);
		algo.performCalc();
		algo.submitToMarket();
	}

}
