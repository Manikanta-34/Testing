package com.enfec.sb.qrcode.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

/**
 * @author VenkataManikanta
 */
@Transactional
@Component
@Slf4j
public class QRCodeRepositoryImpl implements QRCodeRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private int affectedRow;

	private final String MESSAGE_MAP_SAVE_LOG = "Creating QRCode Details : {} ";
	private final String MESSAGE_MAP_DELETE_LOG = "Deleting QRCode Details : {} ";
	private final String MESSAGE_MAP_UPDATE_LOG = "Updating QRCode Details : {} ";

	/**
	 * @description this method is used for Storing the QRCODE details into DB AND
	 *              if QRcode details found in DB then it act as a Updating time for
	 *              current QRcode details
	 * 
	 * @param Map<K,V> object taken from the service class
	 * 
	 * @returns Integer value
	 */

	@Override
	public int saveQRCode_details(Map<String, Object> param) {
		SqlParameterSource parameterSource = new MapSqlParameterSource(param);
		log.info(MESSAGE_MAP_SAVE_LOG, parameterSource);
		affectedRow = namedParameterJdbcTemplate.queryForObject(check_QRCode_id, parameterSource, Integer.class);
		if (affectedRow == 0) {
			affectedRow = namedParameterJdbcTemplate.update(save_QRCode_Details, parameterSource);
			return affectedRow;
		} else {
			// if QRCODE_ID is exists in db then updating the expiration time.

			int extending_time_days = (int) param.get("validity");

			param.put("validity", extending_time_days);

			SqlParameterSource parameterSourceNew = new MapSqlParameterSource(param);
			log.info(MESSAGE_MAP_UPDATE_LOG, parameterSource);
			affectedRow = namedParameterJdbcTemplate.update(update_Expired_Time, parameterSourceNew);
			return affectedRow = 2;
		}

	}

	/**
	 * @description this method is used for Deleting the QRCODE details from the DB
	 * 
	 * @param Map<K,V> Object taken from the service class
	 * 
	 * @returns Integer Value
	 */
	@Override
	public int deleteQrCodeDetails(Map<String, Object> param) {

		SqlParameterSource parameterSource = new MapSqlParameterSource(param);
		log.info(MESSAGE_MAP_DELETE_LOG, parameterSource);
		affectedRow = namedParameterJdbcTemplate.update(delete_QRCODE, parameterSource);
		return affectedRow;
	}

}
