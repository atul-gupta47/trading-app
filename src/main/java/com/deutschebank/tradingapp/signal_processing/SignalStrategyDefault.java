package com.deutschebank.tradingapp.signal_processing;

import com.deutschebank.tradingalgo.Algo;

public class SignalStrategyDefault implements SignalStrategy {

	@Override
	public void process(Algo algo) {
		algo.cancelTrades();
	}

}
