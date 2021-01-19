package com.enfec.sb.qrcode.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author VenkataManikanta
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteQRCode {

	@Min(message = "accnt_id is required", value = 1)
	private int accnt_id;

	@NotBlank(message = "serial_number must not be null")
	private String serial_number;

	@NotBlank(message = "qrcode_id must not be null")
	private String qrcode_id;

}
