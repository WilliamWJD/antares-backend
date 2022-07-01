package com.antares.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorMessage {
	private Integer status;
	private String message;
	private Long timeStamp;
	private String error;
}
