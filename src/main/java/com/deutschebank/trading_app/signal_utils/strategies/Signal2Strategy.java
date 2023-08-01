package com.deutschebank.trading_app.signal_utils.strategies;

import com.deutschebank.trading_algo.Algo;
import com.deutschebank.trading_app.signal_utils.SignalStrategy;

public class Signal2Strategy implements SignalStrategy {

	@Override
	public void process(Algo algo) {
		algo.reverse();
		algo.setAlgoParam(1, 80);
		algo.submitToMarket();
	}

}
