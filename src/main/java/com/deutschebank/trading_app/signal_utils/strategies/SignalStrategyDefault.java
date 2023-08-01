package com.deutschebank.trading_app.signal_utils.strategies;

import com.deutschebank.trading_algo.Algo;
import com.deutschebank.trading_app.signal_utils.SignalStrategy;

public class SignalStrategyDefault implements SignalStrategy {

	@Override
	public void process(Algo algo) {
		algo.cancelTrades();
	}

}
