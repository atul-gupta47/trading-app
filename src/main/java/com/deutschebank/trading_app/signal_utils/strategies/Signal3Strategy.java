package com.deutschebank.trading_app.signal_utils.strategies;

import com.deutschebank.trading_algo.Algo;
import com.deutschebank.trading_app.signal_utils.SignalStrategy;

public class Signal3Strategy implements SignalStrategy {

	@Override
	public void process(Algo algo) {
		algo.setAlgoParam(1, 90);
		algo.setAlgoParam(2, 15);
		algo.performCalc();
		algo.submitToMarket();
	}

}
