package com.currency.converstion.currencyconverstion.model;

public class CurrencyRequest {

	private String fromCurrencyCode;
	private int amountEnter;
	private String toCurrencyCode;

	public String getFromCurrencyCode() {
		return fromCurrencyCode;
	}

	public void setFromCurrencyCode(String fromCurrencyCode) {
		this.fromCurrencyCode = fromCurrencyCode;
	}

	public int getAmountEnter() {
		return amountEnter;
	}

	public void setAmountEnter(int amountEnter) {
		this.amountEnter = amountEnter;
	}

	public String getToCurrencyCode() {
		return toCurrencyCode;
	}

	public void setToCurrencyCode(String toCurrencyCode) {
		this.toCurrencyCode = toCurrencyCode;
	}

}
