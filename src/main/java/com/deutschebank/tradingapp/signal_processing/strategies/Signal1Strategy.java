package com.deutschebank.tradingapp.signal_processing.strategies;

import com.deutschebank.tradingalgo.Algo;
import com.deutschebank.tradingapp.signal_processing.SignalStrategy;

public class Signal1Strategy implements SignalStrategy {

	@Override
	public void process(Algo algo) {
		algo.setUp();
		algo.setAlgoParam(1, 60);
		algo.performCalc();
		algo.submitToMarket();
	}

}
