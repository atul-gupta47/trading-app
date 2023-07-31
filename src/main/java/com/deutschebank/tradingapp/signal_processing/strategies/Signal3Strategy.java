package com.deutschebank.tradingapp.signal_processing.strategies;

import com.deutschebank.tradingalgo.Algo;
import com.deutschebank.tradingapp.signal_processing.SignalStrategy;

public class Signal3Strategy implements SignalStrategy {

	@Override
	public void process(Algo algo) {
		algo.setAlgoParam(1, 90);
		algo.setAlgoParam(2, 15);
		algo.performCalc();
		algo.submitToMarket();
	}

}
