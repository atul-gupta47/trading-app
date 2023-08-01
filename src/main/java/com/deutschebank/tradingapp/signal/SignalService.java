package com.deutschebank.tradingapp.signal;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignalService {

	private AlgoHelper algoHelper;

	public void processSignal(int signal) {
		algoHelper.processSignal(signal);
	}

}
