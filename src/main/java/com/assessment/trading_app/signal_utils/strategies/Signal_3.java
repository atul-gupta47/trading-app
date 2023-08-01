package com.assessment.trading_app.signal_utils.strategies;

import com.assessment.trading_algo.Algo;
import com.assessment.trading_app.signal_utils.SignalStrategy;

public class Signal_3 implements SignalStrategy {

	@Override
	public void process(Algo algo) {
		algo.setAlgoParam(1, 90);
		algo.setAlgoParam(2, 15);
		algo.performCalc();
		algo.submitToMarket();
	}

}
