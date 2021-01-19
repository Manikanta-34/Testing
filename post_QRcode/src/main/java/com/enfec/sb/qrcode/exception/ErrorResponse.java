package com.enfec.sb.qrcode.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author VenkataManikanta
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

	private int errorCode;
	private String errorMessage;

}
