package com.assessment;

import com.assessment.trading_algo.Algo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	public Algo algo() {
		return new Algo();
	}

}
