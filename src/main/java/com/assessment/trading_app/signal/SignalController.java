package com.assessment.trading_app.signal;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class SignalController {

	private SignalService signalService;

	@GetMapping("trading-app/v1/process-signal/{signalId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void processSignal(@PathVariable int signalId) {
		signalService.processSignal(signalId);
	}

}
