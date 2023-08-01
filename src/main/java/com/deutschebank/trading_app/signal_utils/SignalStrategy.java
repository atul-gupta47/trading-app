package com.deutschebank.trading_app.signal_utils;

import com.deutschebank.trading_algo.Algo;

public interface SignalStrategy {

	void process(Algo algo);

}
