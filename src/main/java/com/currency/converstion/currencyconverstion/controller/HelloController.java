package com.currency.converstion.currencyconverstion.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.currency.converstion.currencyconverstion.model.CurrencyRequest;
import com.currency.converstion.currencyconverstion.service.CurrencyConvertor;

@RequestMapping("/rest/hello")
@RestController
public class HelloController {

	@Autowired
	private CurrencyConvertor currencyConvertor;

	// Required no authentication can be accessed directly
	@GetMapping("/all")
	public String hello() {
		return "Hello Client";
	}

	// MAIN A.P.I FOR CURRENCY CONVERSTION NOT REQUIRED FOR AUTHENTICATION
	// BUT RECOMMENDED TO GET AUTHENTICATED BY BELOW A.P.I
	@RequestMapping(value = "/converstion", method = RequestMethod.POST)
	public String converstion(@RequestBody @Valid CurrencyRequest currencyRequest) {

		String fromCurrencyCode = currencyRequest.getFromCurrencyCode();
		String toCurrencyCode = currencyRequest.getToCurrencyCode();
		int amount = currencyRequest.getAmountEnter();
		double conversionRate = CurrencyConvertor.convert(fromCurrencyCode, toCurrencyCode);

		return "Hi, The " + amount + " " + fromCurrencyCode + " is equivalent to " + (conversionRate * amount) + " "
				+ toCurrencyCode + " today.";

	}

	// Hit the localhost U.R.L for authentication
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/secured/all")
	public String securedHello() {
		return "Secured Hello";
	}

}
