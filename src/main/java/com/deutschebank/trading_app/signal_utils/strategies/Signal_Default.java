package com.deutschebank.trading_app.signal_utils.strategies;

import com.deutschebank.trading_algo.Algo;
import com.deutschebank.trading_app.signal_utils.SignalStrategy;

/**
 * Default implementation of the {@link SignalStrategy} interface. This strategy cancels
 * trades using the provided {@link Algo} instance.
 */
public class Signal_Default implements SignalStrategy {

	/**
	 * Process the signal using the given {@link Algo} instance.
	 * @param algo The {@link Algo} instance to use for processing the signal.
	 */
	@Override
	public void process(Algo algo) {
		algo.cancelTrades();
	}

}
