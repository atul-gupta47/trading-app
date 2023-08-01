package com.assessment.trading_app.signal_utils;

import com.assessment.trading_algo.Algo;

public interface SignalStrategy {

	void process(Algo algo);

}
