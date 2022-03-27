package com.gu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//交易员类
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Trader {

	private String name;
	private String city;

}
