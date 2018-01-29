package com.currency.converstion.currencyconverstion.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.currency.converstion.currencyconverstion.model.CurrencyConverstionResponse;
import com.google.gson.Gson;


@Service
public class CurrencyConvertor {

	private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.LogManager
			.getLogger(CurrencyConvertor.class);

	// API Provider URL
	private static final String API_PROVDIER = "http://api.fixer.io/";

	public static double convert(String fromCurrencyCode, String toCurrencyCode) {

		if ((fromCurrencyCode != null && !fromCurrencyCode.isEmpty())
				&& (toCurrencyCode != null && !toCurrencyCode.isEmpty())) {

			CurrencyConverstionResponse response = getResponse(API_PROVDIER + "/latest?base=" + fromCurrencyCode);

			if (response != null) {

				String rate = response.getRates().get(toCurrencyCode);

				double conversionRate = Double.valueOf((rate != null) ? rate : "0.0");

				return conversionRate;
			}

		}

		return 0.0;
	}

	// Method to get the response from API
	private static CurrencyConverstionResponse getResponse(String strUrl) {

		CurrencyConverstionResponse response = null;

		Gson gson = new Gson();
		StringBuffer sb = new StringBuffer();

		if (strUrl == null || strUrl.isEmpty()) {

			LOGGER.error("Application error");
			return null;
		}

		URL url;
		try {
			url = new URL(strUrl);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			InputStream stream = connection.getInputStream();

			int data = stream.read();

			while (data != -1) {

				sb.append((char) data);

				data = stream.read();
			}

			stream.close();

			response = gson.fromJson(sb.toString(), CurrencyConverstionResponse.class);

		} catch (MalformedURLException e) {

			LOGGER.error(e.getMessage());
			e.printStackTrace();

		} catch (IOException e) {

			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}

		return response;

	}

}
