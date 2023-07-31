package com.deutschebank.tradingapp.signal;

import com.deutschebank.tradingalgo.Algo;
import com.deutschebank.tradingapp.signal_processing.*;
import com.deutschebank.tradingapp.signal_processing.strategies.Signal1Strategy;
import com.deutschebank.tradingapp.signal_processing.strategies.Signal2Strategy;
import com.deutschebank.tradingapp.signal_processing.strategies.Signal3Strategy;
import org.springframework.stereotype.Service;

@Service
public class AlgoHelper {

	private final Algo algo;

	public AlgoHelper() {
		this.algo = new Algo();
	}

	public void processSignal(int signal) {
		SignalStrategy strategy;
		switch (signal) {
			case 1 -> {
				strategy = new Signal1Strategy();
			}
			case 2 -> {
				strategy = new Signal2Strategy();
			}
			case 3 -> {
				strategy = new Signal3Strategy();
			}
			default -> {
				strategy = new SignalStrategyDefault();
			}
		}
		strategy.process(algo);
		algo.doAlgo();
	}

}
