package com.deutschebank.tradingapp.signal_processing.strategies;

import com.deutschebank.tradingalgo.Algo;
import com.deutschebank.tradingapp.signal_processing.SignalStrategy;

public class Signal2Strategy implements SignalStrategy {

	@Override
	public void process(Algo algo) {
		algo.reverse();
		algo.setAlgoParam(1, 80);
		algo.submitToMarket();
	}

}
