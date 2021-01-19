package com.enfec.sb.qrcode.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enfec.sb.qrcode.model.DeleteQRCode;
import com.enfec.sb.qrcode.model.QRCode;
import com.enfec.sb.qrcode.model.ResponseObject;
import com.enfec.sb.qrcode.service.QRCodeService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author VenkataManikanta
 */
@RestController
@RequestMapping("/QRCode")
@Slf4j

public class QRCodeController {

	@Autowired
	private QRCodeService qrCodeService;

	private final String FAIL_SAVE_LOG = "QRCode Details failed to store : {} ";
	private final String SUCCESS_SAVE_TIME_LOG = "QRCode_id expiration time is updated for the accnt_id: {} ";
	private final String SUCCESS_SAVE_LOG = "QRCode Details has successfully stored for the accnt_id: {} ";
	private final String INVALID_ACCNT_ID_LOG = "Invalid Accnt_id ";
	private final String EXCEPTION_LOG = "Exception caught";
	private final String DELETE_FAILURE_LOG = "QRCode Details are not deleted for the accnt_id : {} ";
	private final String DELETE_SUCCESS_LOG = "QRCode Details has successfully deleted for the accnt_id: {} ";

	/**
	 * @description this method is used for Storing the QRCODE details into DB
	 * 
	 * @param QRCode class
	 * 
	 * @returns ResponseEntity<> value
	 */
	@PostMapping(value = "/save", produces = "application/json;charset=UTF-8")
	public ResponseEntity<Object> saveQrcodeDetails(@Valid @RequestBody QRCode qrCode) throws Exception {

		try {
			int affectedRow = qrCodeService.saveQRCodeMap(qrCode);
			if (affectedRow == 0) {
				log.info(FAIL_SAVE_LOG, qrCode.getAccnt_id());
				return new ResponseEntity<>(new ResponseObject("QRCode Details has failed to store"),
						HttpStatus.BAD_REQUEST);

			} else if (affectedRow == 2) {
				log.info(SUCCESS_SAVE_TIME_LOG, qrCode.getAccnt_id());
				return new ResponseEntity<>(new ResponseObject("QRCode_id expiration time is updated"), HttpStatus.OK);

			} else {
				log.info(SUCCESS_SAVE_LOG, qrCode.getAccnt_id());
				return new ResponseEntity<>(new ResponseObject("QRCode Details has successfully stored"),
						HttpStatus.OK);

			}
		} catch (DataIntegrityViolationException e) {
			log.error(INVALID_ACCNT_ID_LOG + qrCode.getAccnt_id());
			throw new DataIntegrityViolationException(INVALID_ACCNT_ID_LOG);

		} catch (Exception e) {
			log.error(EXCEPTION_LOG, e.getMessage());
			throw new Exception(EXCEPTION_LOG);

		}

	}

	/**
	 * @description this method is used for deleting the existing QRCODE details
	 *              from DB
	 * 
	 * @param QRCode class
	 * 
	 * @returns ResponseEntity<> value
	 */

	@DeleteMapping(value = "/delete_QRcode", produces = "application/json;charset=UTF-8")
	public ResponseEntity<Object> deleteQrCode(@Valid @RequestBody DeleteQRCode qrCode) throws Exception {

		try {
			int affectedRow = qrCodeService.deleletQRCodeMap(qrCode);
			if (affectedRow == 0) {
				log.info(DELETE_FAILURE_LOG, qrCode.getAccnt_id());
				return new ResponseEntity<>(new ResponseObject("QRCode Details are not deleted"),
						HttpStatus.BAD_REQUEST);

			} else {
				log.info(DELETE_SUCCESS_LOG, qrCode.getAccnt_id());
				return new ResponseEntity<>(new ResponseObject("QRCode Details has successfully deleted"),
						HttpStatus.OK);

			}
		} catch (Exception e) {
			log.error(EXCEPTION_LOG);
			throw new Exception(EXCEPTION_LOG);
		}

	}
}
