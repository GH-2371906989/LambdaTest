package com.gu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//交易类
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction {

	private Trader trader;
	private int year;
	private int value;

}
