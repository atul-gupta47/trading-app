package com.deutschebank.tradingapp.signal;

import com.deutschebank.tradingalgo.Algo;
import org.springframework.stereotype.Service;

@Service
public class AlgoHelper {

	private final Algo algo;

	public AlgoHelper() {
		this.algo = new Algo();
	}

	public void processSignal(int signal) {
		switch (signal) {
			case 1 -> {
				algo.setUp();
				algo.setAlgoParam(1, 60);
				algo.performCalc();
				algo.submitToMarket();
			}
			case 2 -> {
				algo.reverse();
				algo.setAlgoParam(1, 80);
				algo.submitToMarket();
			}
			case 3 -> {
				algo.setAlgoParam(1, 90);
				algo.setAlgoParam(2, 15);
				algo.performCalc();
				algo.submitToMarket();
			}
			default -> algo.cancelTrades();
		}
		algo.doAlgo();
	}

}
