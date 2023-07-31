package com.deutschebank.tradingapp.signal;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class SignalController {

	private SignalService signalService;

	// TODO: revisit the api requirement and make necessary changes
	@PostMapping("/process_signal")
	public ResponseEntity<String> processSignal(@RequestBody int signal) {
		try {
			String result = signalService.processSignal(signal);
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>("Error processing signal: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
