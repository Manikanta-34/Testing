package com.enfec.sb.qrcode.controller.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.enfec.sb.qrcode.model.DeleteQRCode;
import com.enfec.sb.qrcode.model.QRCode;

import com.enfec.sb.qrcode.service.QRCodeService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author VenkataManikanta
 */
@WebMvcTest
@RunWith(SpringRunner.class)
public class ControllerTest {

	@Autowired
	private MockMvc mockMvc;
	QRCode qrcode;
	DeleteQRCode deleteQrcode;
	@MockBean
	private QRCodeService qrCodeService;

	private static ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setupForSave() {
		qrcode = new QRCode();
		qrcode.setAccnt_id(20001);
		qrcode.setSerial_number("serail123");
		qrcode.setQrcode_id("sample12343");
		qrcode.setQrcode_name("qwerty");
		qrcode.setValidity(1432123452);
	}

	@Before
	public void setupForDelete() {
		deleteQrcode = new DeleteQRCode();
		deleteQrcode.setAccnt_id(20001);
		deleteQrcode.setSerial_number("serail123");
		deleteQrcode.setQrcode_id("sample12343");

	}

	/*
	 * @description this method is used for testing success case
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void saveQRCodeDetailsSuccess() throws Exception {
		when(qrCodeService.saveQRCodeMap(qrcode)).thenReturn(1);
		String json = mapper.writeValueAsString(qrcode);
		this.mockMvc.perform(post("/QRCode/save").content(json).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}
	/*
	 * @description this method is used for testing Failure case
	 * 
	 * @throws Exception
	 * 
	 */

	@Test
	public void saveQRCodeDetailsFail() throws Exception {
		when(qrCodeService.saveQRCodeMap(qrcode)).thenReturn(0);
		String json = mapper.writeValueAsString(qrcode);
		this.mockMvc.perform(post("/QRCode/save").content(json).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}

	/*
	 * @description this method is used for testing success case
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void deleteQRCodeDetailsSuccess() throws Exception {
		when(qrCodeService.deleletQRCodeMap(deleteQrcode)).thenReturn(1);
		String json = mapper.writeValueAsString(deleteQrcode);
		this.mockMvc.perform(delete("/QRCode/delete_QRcode").content(json).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	/*
	 * @description this method is used for testing Failure case
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void deleteQRCodeDetailsFail() throws Exception {
		when(qrCodeService.deleletQRCodeMap(deleteQrcode)).thenReturn(0);
		String json = mapper.writeValueAsString(deleteQrcode);
		this.mockMvc.perform(delete("/QRCode/delete_QRcode").content(json).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

	}
}
