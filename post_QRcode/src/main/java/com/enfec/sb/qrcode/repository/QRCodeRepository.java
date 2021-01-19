package com.enfec.sb.qrcode.repository;

import java.util.Map;

public interface QRCodeRepository {
	final String save_QRCode_Details = "INSERT INTO QRCODE(ACCNT_ID,SERIAL_NUMBER,QRCODE_NAME,QRCODE_ID,VALIDITY) "
			+ " VALUES(:accnt_id,:serial_number,:qrcode_name,:qrcode_id,:validity)";

	final String check_QRCode_id = "SELECT COUNT(*) FROM QRCODE where QRCODE_ID=:qrcode_id";

	final String update_Expired_Time = "UPDATE QRCODE SET VALIDITY=:validity WHERE ACCNT_ID=:accnt_id AND SERIAL_NUMBER="
			+ " :serial_number AND QRCODE_NAME=:qrcode_name AND QRCODE_ID=:qrcode_id";

	final String delete_QRCODE = "DELETE FROM QRCODE WHERE ACCNT_ID=:accnt_id AND SERIAL_NUMBER=:serial_number AND"
			+ " QRCODE_ID=:qrcode_id";

	public int saveQRCode_details(Map<String, Object> param);

	public int deleteQrCodeDetails(Map<String, Object> param);

}
