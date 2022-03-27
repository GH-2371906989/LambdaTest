package com.gu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {

	private int id;
	private String name;
	private int age;
	private double salary;
	private Status status;
	public enum Status {
		上班, 下班, 工作;
	}
}
