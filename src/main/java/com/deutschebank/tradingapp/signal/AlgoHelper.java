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
		algo.doAlgo();
	}

}
