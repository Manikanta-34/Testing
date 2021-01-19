package com.enfec.sb.qrcode.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.enfec.sb.qrcode.model.DeleteQRCode;
import com.enfec.sb.qrcode.model.QRCode;
import com.enfec.sb.qrcode.repository.QRCodeRepository;

/**
 * @author VenkataManikanta
 */
@Service

public class QRCodeService {

	@Autowired
	private QRCodeRepository qrCodeRepository;

	/**
	 * @description this method is used for Storing the QRCODE details into Map<K,V>
	 *              object
	 * 
	 * @param QRCode
	 * 
	 * @returns Integer Value
	 */
	public int saveQRCodeMap(QRCode qrCode) {
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("accnt_id", qrCode.getAccnt_id());
		param.put("serial_number", qrCode.getSerial_number());
		param.put("qrcode_id", qrCode.getQrcode_id());
		param.put("validity", qrCode.getValidity());
		param.put("qrcode_name", qrCode.getQrcode_name());
		// calling to repository method to save qrCode details
		return qrCodeRepository.saveQRCode_details(param);
	}

	/**
	 * @description this method is used for Storing the QRCODE details into Map<K,V>
	 *              object
	 * 
	 * @param QRCode
	 * 
	 * @returns Integer value
	 */
	public int deleletQRCodeMap(DeleteQRCode qrCode) {
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("accnt_id", qrCode.getAccnt_id());
		param.put("serial_number", qrCode.getSerial_number());
		param.put("qrcode_id", qrCode.getQrcode_id());
		// calling to repository method to delete qrCode details
		return qrCodeRepository.deleteQrCodeDetails(param);
	}

}
