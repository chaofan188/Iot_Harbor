package com.cetc.iot.database.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cetc.iot.database.dao.PeInterfaceDao;
import com.cetc.iot.database.model.PeInterface;

@Repository
public class PeInterfaceDaoImpl implements PeInterfaceDao {

	private static Logger logger = Logger.getLogger(PeInterfaceDaoImpl.class
			.getName());

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// String interfaceId;//锟接匡拷ID
	// String interfaceName;//锟接匡拷锟斤拷锟�
	// String interfaceType; //锟接匡拷锟斤拷锟斤拷
	// int interfaceResponse = -1;//锟接匡拷锟斤拷应锟斤拷锟斤拷
	// int interfaceFashion = -1;//锟接口凤拷式
	// int interfaceChannel = -1;//锟接匡拷通锟斤拷
	// String templateId;//锟斤拷锟絧e模锟斤拷ID
	// String interfaceRemark;//锟接口憋拷注
	// String interfaceContent;//实锟绞接口ｏ拷锟斤拷锟斤拷值锟皆ｏ拷锟斤拷锟芥）
	// String parameterType;//锟接口诧拷锟斤拷锟斤拷锟酵ｏ拷锟斤拷锟斤拷裕锟斤拷锟斤拷锟斤拷锟�
	// String result;//锟斤拷锟截斤拷锟斤拷式
	// String resultType;//锟斤拷锟斤拷锟斤拷锟斤拷锟酵ｏ拷锟斤拷锟斤拷锟剿筹拷锟斤拷裕锟斤拷锟斤拷锟斤拷锟�
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	/*
	 * public void preOperation(PeInterface peInterface){ this.interfaceId =
	 * peInterface.getInterfaceId(); this.interfaceName =
	 * peInterface.getInterfaceName(); this.interfaceType =
	 * peInterface.getInterfaceType(); this.interfaceResponse =
	 * peInterface.getInterfaceResponse(); this.interfaceFashion =
	 * peInterface.getInterfaceFashion(); this.interfaceChannel =
	 * peInterface.getInterfaceChannel(); this.templateId =
	 * peInterface.getTemplateId(); this.interfaceRemark =
	 * peInterface.getInterfaceRemark(); this.interfaceContent =
	 * peInterface.getInterfaceContent(); this.parameterType =
	 * peInterface.getParameterType(); this.result = peInterface.getResult();
	 * this.resultType = peInterface.getResultType(); }
	 */

	public int queryNum(String template_id, PeInterface peInterface) {
		// TODO Auto-generated method stub
		String sqlStr = "select count(*) from iot_pe_template where 1=1 ";

		/*
		 * if (templateId != null) { sqlStr += " template_id = " + "'" +
		 * templateId + "'"; }
		 */
		if (peInterface.getInterfaceId() != null) {
			sqlStr += " template_id = " + "'" + peInterface.getInterfaceId()
					+ "'";
		}
		logger.info(sqlStr);
		return jdbcTemplate.queryForInt(sqlStr);
	}

	@Override
	public List<Map<String, Object>> query(PeInterface peInterface, int page,
			int size) {

		// preOperation(peInterface);
		String sqlStr = "select * from iot_pe_interface where";
		if (peInterface.getInterfaceId() != null) {
			sqlStr += " interface_id = " + "'" + peInterface.getInterfaceId()
					+ "'";
		}
		if (peInterface.getInterfaceName() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_name = " + "'"
						+ peInterface.getInterfaceName() + "'";
			} else {
				sqlStr += " and interface_name = " + "'"
						+ peInterface.getInterfaceName() + "'";
			}
		}
		if (peInterface.getInterfaceType() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_type = " + "'"
						+ peInterface.getInterfaceType() + "'";
			} else {
				sqlStr += " and interface_type = " + "'"
						+ peInterface.getInterfaceType() + "'";
			}
		}
		if (peInterface.getInterfaceResponse() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_response = " + "'"
						+ peInterface.getInterfaceResponse() + "'";
			} else {
				sqlStr += " and interface_response = " + "'"
						+ peInterface.getInterfaceResponse() + "'";
			}
		}
		if (peInterface.getInterfaceFashion() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_fashion = " + "'"
						+ peInterface.getInterfaceFashion() + "'";
			} else {
				sqlStr += " and interface_fashion = " + "'"
						+ peInterface.getInterfaceFashion() + "'";
			}
		}
		if (peInterface.getInterfaceChannel() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_channel = " + "'"
						+ peInterface.getInterfaceChannel() + "'";
			} else {
				sqlStr += " and interface_channel = " + "'"
						+ peInterface.getInterfaceChannel() + "'";
			}
		}
		if (peInterface.getTemplateId() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " template_id = " + "'" + peInterface.getTemplateId()
						+ "'";
			} else {
				sqlStr += " and template_id = " + "'"
						+ peInterface.getTemplateId() + "'";
			}
		}
		if (peInterface.getInterfaceRemark() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_remark = " + "'"
						+ peInterface.getInterfaceRemark() + "'";
			} else {
				sqlStr += " and interface_remark = " + "'"
						+ peInterface.getInterfaceRemark() + "'";
			}
		}
		if (peInterface.getInterfaceContent() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_content = " + "'"
						+ peInterface.getInterfaceContent() + "'";
			} else {
				sqlStr += " and interface_content = " + "'"
						+ peInterface.getInterfaceContent() + "'";
			}
		}
		if (peInterface.getParameterType() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " parameter_type = " + "'"
						+ peInterface.getParameterType() + "'";
			} else {
				sqlStr += " and parameter_type = " + "'"
						+ peInterface.getParameterType() + "'";
			}
		}
		if (peInterface.getResult() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " result = " + "'" + peInterface.getResult() + "'";
			} else {
				sqlStr += " and result = " + "'" + peInterface.getResult()
						+ "'";
			}
		}
		if (peInterface.getResultType() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " result_type = " + "'" + peInterface.getResultType()
						+ "'";
			} else {
				sqlStr += " and result_type = " + "'"
						+ peInterface.getResultType() + "'";
			}
		}
		if (page > 0 && size > 0) {
			sqlStr += " limit " + page + "," + size + "";
		}
		logger.info(sqlStr);
		list = jdbcTemplate.queryForList(sqlStr);
		logger.info(list);
		return list;
	}

	@Override
	public String delete(PeInterface peInterface) {
		// preOperation(peInterface);
		String sqlStr = "delete from iot_pe_interface where";
		if (peInterface.getInterfaceId() != null) {
			sqlStr += " interface_id = " + "'" + peInterface.getInterfaceId()
					+ "'";
		}
		if (peInterface.getInterfaceName() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_name = " + "'"
						+ peInterface.getInterfaceName() + "'";
			} else {
				sqlStr += " and interface_name = " + "'"
						+ peInterface.getInterfaceName() + "'";
			}
		}
		if (peInterface.getInterfaceType() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_type = " + "'"
						+ peInterface.getInterfaceType() + "'";
			} else {
				sqlStr += " and interface_type = " + "'"
						+ peInterface.getInterfaceType() + "'";
			}
		}
		if (peInterface.getInterfaceResponse() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_response = " + "'"
						+ peInterface.getInterfaceResponse() + "'";
			} else {
				sqlStr += " and interface_response = " + "'"
						+ peInterface.getInterfaceResponse() + "'";
			}
		}
		if (peInterface.getInterfaceFashion() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_fashion = " + "'"
						+ peInterface.getInterfaceFashion() + "'";
			} else {
				sqlStr += " and interface_fashion = " + "'"
						+ peInterface.getInterfaceFashion() + "'";
			}
		}
		if (peInterface.getInterfaceChannel() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_channel = " + "'"
						+ peInterface.getInterfaceChannel() + "'";
			} else {
				sqlStr += " and interface_channel = " + "'"
						+ peInterface.getInterfaceChannel() + "'";
			}
		}
		if (peInterface.getTemplateId() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " template_id = " + "'" + peInterface.getTemplateId()
						+ "'";
			} else {
				sqlStr += " and template_id = " + "'"
						+ peInterface.getTemplateId() + "'";
			}
		}
		if (peInterface.getInterfaceRemark() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_remark = " + "'"
						+ peInterface.getInterfaceRemark() + "'";
			} else {
				sqlStr += " and interface_remark = " + "'"
						+ peInterface.getInterfaceRemark() + "'";
			}
		}
		if (peInterface.getInterfaceContent() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_content = " + "'"
						+ peInterface.getInterfaceContent() + "'";
			} else {
				sqlStr += " and interface_content = " + "'"
						+ peInterface.getInterfaceContent() + "'";
			}
		}
		if (peInterface.getParameterType() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " parameter_type = " + "'"
						+ peInterface.getParameterType() + "'";
			} else {
				sqlStr += " and parameter_type = " + "'"
						+ peInterface.getParameterType() + "'";
			}
		}
		if (peInterface.getResult() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " result = " + "'" + peInterface.getResult() + "'";
			} else {
				sqlStr += " and result = " + "'" + peInterface.getResult()
						+ "'";
			}
		}
		if (peInterface.getResultType() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " result_type = " + "'" + peInterface.getResultType()
						+ "'";
			} else {
				sqlStr += " and result_type = " + "'"
						+ peInterface.getResultType() + "'";
			}
		}
		logger.info(sqlStr);
		try {
			jdbcTemplate.execute(sqlStr);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			return "fail";
		}
		return "success";

	}

	@Override
	public String add(PeInterface peInterface) {

		// preOperation(peInterface);
		String sqlStr = "";
		String sqlStr1 = "";
		String sqlStr2 = "";
		sqlStr1 = "insert into iot_pe_interface(";
		if (peInterface.getInterfaceId() != null) {

			sqlStr1 += "interface_id";
			sqlStr2 += "'" + peInterface.getInterfaceId() + "'";
		}

		if (peInterface.getInterfaceName() != null) {

			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "interface_name";
				sqlStr2 += "'" + peInterface.getInterfaceName() + "'";
			} else {
				sqlStr1 += ",interface_name";
				sqlStr2 += ",'" + peInterface.getInterfaceName() + "'";
			}

		}
		if (peInterface.getInterfaceType() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "interface_type";
				sqlStr2 += "'" + peInterface.getInterfaceType() + "'";
			} else {
				sqlStr1 += ",interface_type";
				sqlStr2 += ",'" + peInterface.getInterfaceType() + "'";
			}

		}
		if (peInterface.getInterfaceResponse() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "interface_response";
				sqlStr2 += "'" + peInterface.getInterfaceResponse() + "'";
			} else {
				sqlStr1 += ",interface_response";
				sqlStr2 += ",'" + peInterface.getInterfaceResponse() + "'";
			}

		}
		if (peInterface.getInterfaceFashion() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "interface_fashion";
				sqlStr2 += "'" + peInterface.getInterfaceFashion() + "'";
			} else {
				sqlStr1 += ",interface_fashion";
				sqlStr2 += ",'" + peInterface.getInterfaceFashion() + "'";
			}

		}
		if (peInterface.getInterfaceChannel() != -1) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "interface_channel";
				sqlStr2 += "'" + peInterface.getInterfaceChannel() + "'";
			} else {
				sqlStr1 += ",interface_channel";
				sqlStr2 += ",'" + peInterface.getInterfaceChannel() + "'";
			}

		}
		if (peInterface.getTemplateId() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "template_id";
				sqlStr2 += "'" + peInterface.getTemplateId() + "'";
			} else {
				sqlStr1 += ",template_id";
				sqlStr2 += ",'" + peInterface.getTemplateId() + "'";
			}

		}
		if (peInterface.getInterfaceRemark() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "interface_remark";
				sqlStr2 += "'" + peInterface.getInterfaceRemark() + "'";
			} else {
				sqlStr1 += ",interface_remark";
				sqlStr2 += ",'" + peInterface.getInterfaceRemark() + "'";
			}

		}

		if (peInterface.getInterfaceContent() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "interface_content";
				sqlStr2 += "'" + peInterface.getInterfaceContent() + "'";
			} else {
				sqlStr1 += ",interface_content";
				sqlStr2 += ",'" + peInterface.getInterfaceContent() + "'";
			}

		}

		if (peInterface.getParameterType() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "parameter_type";
				sqlStr2 += "'" + peInterface.getParameterType() + "'";
			} else {
				sqlStr1 += ",parameter_type";
				sqlStr2 += ",'" + peInterface.getParameterType() + "'";
			}

		}
		if (peInterface.getResult() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "result";
				sqlStr2 += "'" + peInterface.getResult() + "'";
			} else {
				sqlStr1 += ",result";
				sqlStr2 += ",'" + peInterface.getResult() + "'";
			}

		}
		if (peInterface.getResultType() != null) {
			char lastChar = sqlStr1.charAt(sqlStr1.length() - 1);
			if (lastChar == '(') {
				sqlStr1 += "result_type";
				sqlStr2 += "'" + peInterface.getResultType() + "'";
			} else {
				sqlStr1 += ",result_type";
				sqlStr2 += ",'" + peInterface.getResultType() + "'";
			}

		}
		sqlStr1 += ") values (";
		sqlStr2 += ")";
		sqlStr = sqlStr1 + sqlStr2;
		logger.info(sqlStr);
		try {
			jdbcTemplate.update(sqlStr);
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
			return "fail";
		}
		return "success";

	}

	@Override
	public String update(PeInterface peInterface) {

		// preOperation(peInterface);
		String sqlStr = "update iot_pe_interface set";
		/*
		 * if(interfaceId!=null){ sqlStr += " interface_id = "+"'"+interfaceId
		 * +"'"; }
		 */
		if (peInterface.getInterfaceName() != null) {

			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " interface_name = " + "'"
						+ peInterface.getInterfaceName() + "'";
			} else {
				sqlStr += " , interface_name = " + "'"
						+ peInterface.getInterfaceName() + "'";
			}
		}
		if (peInterface.getInterfaceType() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " interface_type = " + "'"
						+ peInterface.getInterfaceType() + "'";
			} else {
				sqlStr += " , interface_type = " + "'"
						+ peInterface.getInterfaceType() + "'";
			}
		}
		if (peInterface.getInterfaceResponse() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " interface_response = " + "'"
						+ peInterface.getInterfaceResponse() + "'";
			} else {
				sqlStr += " , interface_response = " + "'"
						+ peInterface.getInterfaceResponse() + "'";
			}
		}
		if (peInterface.getInterfaceFashion() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " interface_fashion = " + "'"
						+ peInterface.getInterfaceFashion() + "'";
			} else {
				sqlStr += " , interface_fashion = " + "'"
						+ peInterface.getInterfaceFashion() + "'";
			}
		}
		if (peInterface.getInterfaceChannel() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " interface_channel = " + "'"
						+ peInterface.getInterfaceChannel() + "'";
			} else {
				sqlStr += " , interface_channel = " + "'"
						+ peInterface.getInterfaceChannel() + "'";
			}
		}
		if (peInterface.getTemplateId() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " template_id = " + "'" + peInterface.getTemplateId()
						+ "'";
			} else {
				sqlStr += " , template_id = " + "'"
						+ peInterface.getTemplateId() + "'";
			}
		}
		if (peInterface.getInterfaceRemark() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " interface_remark = " + "'"
						+ peInterface.getInterfaceRemark() + "'";
			} else {
				sqlStr += " , interface_remark = " + "'"
						+ peInterface.getInterfaceRemark() + "'";
			}
		}
		if (peInterface.getInterfaceContent() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " interface_content = " + "'"
						+ peInterface.getInterfaceContent() + "'";
			} else {
				sqlStr += " , interface_content = " + "'"
						+ peInterface.getInterfaceContent() + "'";
			}
		}
		if (peInterface.getParameterType() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " parameter_type = " + "'"
						+ peInterface.getParameterType() + "'";
			} else {
				sqlStr += " , parameter_type = " + "'"
						+ peInterface.getParameterType() + "'";
			}
		}
		if (peInterface.getResult() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " result = " + "'" + peInterface.getResult() + "'";
			} else {
				sqlStr += " , result = " + "'" + peInterface.getResult() + "'";
			}
		}
		if (peInterface.getResultType() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 3);
			if (lastString.equals("set")) {
				sqlStr += " result_type = " + "'" + peInterface.getResultType()
						+ "'";
			} else {
				sqlStr += " , result_type = " + "'"
						+ peInterface.getResultType() + "'";
			}
		}
		sqlStr += " where interface_id = " + "'" + peInterface.getInterfaceId()
				+ "'";
		logger.info(sqlStr);
		try {
			jdbcTemplate.update(sqlStr);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			return "fail";
		}
		return "success";

	}

	@Override
	public List<Map<String, Object>> queryall(PeInterface peInterface) {

		// preOperation(peInterface);
		String sqlStr = "select * from iot_pe_interface where";
		if (peInterface.getInterfaceId() != null) {
			sqlStr += " interface_id = " + "'" + peInterface.getInterfaceId()
					+ "'";
		}
		if (peInterface.getInterfaceName() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_name = " + "'"
						+ peInterface.getInterfaceName() + "'";
			} else {
				sqlStr += " and interface_name = " + "'"
						+ peInterface.getInterfaceName() + "'";
			}
		}
		if (peInterface.getInterfaceType() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_type = " + "'"
						+ peInterface.getInterfaceType() + "'";
			} else {
				sqlStr += " and interface_type = " + "'"
						+ peInterface.getInterfaceType() + "'";
			}
		}
		if (peInterface.getInterfaceResponse() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_response = " + "'"
						+ peInterface.getInterfaceResponse() + "'";
			} else {
				sqlStr += " and interface_response = " + "'"
						+ peInterface.getInterfaceResponse() + "'";
			}
		}
		if (peInterface.getInterfaceFashion() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_fashion = " + "'"
						+ peInterface.getInterfaceFashion() + "'";
			} else {
				sqlStr += " and interface_fashion = " + "'"
						+ peInterface.getInterfaceFashion() + "'";
			}
		}
		if (peInterface.getInterfaceChannel() != -1) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_channel = " + "'"
						+ peInterface.getInterfaceChannel() + "'";
			} else {
				sqlStr += " and interface_channel = " + "'"
						+ peInterface.getInterfaceChannel() + "'";
			}
		}
		if (peInterface.getTemplateId() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " template_id = " + "'" + peInterface.getTemplateId()
						+ "'";
			} else {
				sqlStr += " and template_id = " + "'"
						+ peInterface.getTemplateId() + "'";
			}
		}
		if (peInterface.getInterfaceRemark() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_remark = " + "'"
						+ peInterface.getInterfaceRemark() + "'";
			} else {
				sqlStr += " and interface_remark = " + "'"
						+ peInterface.getInterfaceRemark() + "'";
			}
		}
		if (peInterface.getInterfaceContent() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " interface_content = " + "'"
						+ peInterface.getInterfaceContent() + "'";
			} else {
				sqlStr += " and interface_content = " + "'"
						+ peInterface.getInterfaceContent() + "'";
			}
		}
		if (peInterface.getParameterType() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " parameter_type = " + "'"
						+ peInterface.getParameterType() + "'";
			} else {
				sqlStr += " and parameter_type = " + "'"
						+ peInterface.getParameterType() + "'";
			}
		}
		if (peInterface.getResult() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " result = " + "'" + peInterface.getResult() + "'";
			} else {
				sqlStr += " and result = " + "'" + peInterface.getResult()
						+ "'";
			}
		}
		if (peInterface.getResultType() != null) {
			String lastString = sqlStr.substring(sqlStr.length() - 5);
			if (lastString.equals("where")) {
				sqlStr += " result_type = " + "'" + peInterface.getResultType()
						+ "'";
			} else {
				sqlStr += " and result_type = " + "'"
						+ peInterface.getResultType() + "'";
			}
		}
		logger.info(sqlStr);
		list = jdbcTemplate.queryForList(sqlStr);
		logger.info(list);
		return list;
	}

	/*
	 * @Override public String delete(PeInterface peInterface) {
	 * preOperation(peInterface); String sqlStr =
	 * "delete from iot_pe_interface where"; if (interfaceId != null) { sqlStr
	 * += " interface_id = " + "'" + interfaceId + "'"; } if (interfaceName !=
	 * null) { String lastString = sqlStr.substring(sqlStr.length() - 5); if
	 * (lastString.equals("where")) { sqlStr += " interface_name = " + "'" +
	 * interfaceName + "'"; } else { sqlStr += " and interface_name = " + "'" +
	 * interfaceName + "'"; } } if (interfaceType != null) { String lastString =
	 * sqlStr.substring(sqlStr.length() - 5); if (lastString.equals("where")) {
	 * sqlStr += " interface_type = " + "'" + interfaceType + "'"; } else {
	 * sqlStr += " and interface_type = " + "'" + interfaceType + "'"; } } if
	 * (interfaceResponse != -1) { String lastString =
	 * sqlStr.substring(sqlStr.length() - 5); if (lastString.equals("where")) {
	 * sqlStr += " interface_response = " + "'" + interfaceResponse + "'"; }
	 * else { sqlStr += " and interface_response = " + "'" + interfaceResponse +
	 * "'"; } } if (interfaceFashion != -1) { String lastString =
	 * sqlStr.substring(sqlStr.length() - 5); if (lastString.equals("where")) {
	 * sqlStr += " interface_fashion = " + "'" + interfaceFashion + "'"; } else
	 * { sqlStr += " and interface_fashion = " + "'" + interfaceFashion + "'"; }
	 * } if (interfaceChannel != -1) { String lastString =
	 * sqlStr.substring(sqlStr.length() - 5); if (lastString.equals("where")) {
	 * sqlStr += " interface_channel = " + "'" + interfaceChannel + "'"; } else
	 * { sqlStr += " and interface_channel = " + "'" + interfaceChannel + "'"; }
	 * } if (templateId != null) { String lastString =
	 * sqlStr.substring(sqlStr.length() - 5); if (lastString.equals("where")) {
	 * sqlStr += " template_id = " + "'" + templateId + "'"; } else { sqlStr +=
	 * " and template_id = " + "'" + templateId + "'"; } } if (interfaceRemark
	 * != null) { String lastString = sqlStr.substring(sqlStr.length() - 5); if
	 * (lastString.equals("where")) { sqlStr += " interface_remark = " + "'" +
	 * interfaceRemark + "'"; } else { sqlStr += " and interface_remark = " +
	 * "'" + interfaceRemark + "'"; } } if (interfaceContent != null) { String
	 * lastString = sqlStr.substring(sqlStr.length() - 5); if
	 * (lastString.equals("where")) { sqlStr += " interface_content = " + "'" +
	 * interfaceContent + "'"; } else { sqlStr += " and interface_content = " +
	 * "'" + interfaceContent + "'"; } } if (parameterType != null) { String
	 * lastString = sqlStr.substring(sqlStr.length() - 5); if
	 * (lastString.equals("where")) { sqlStr += " parameter_type = " + "'" +
	 * parameterType + "'"; } else { sqlStr += " and parameter_type = " + "'" +
	 * parameterType + "'"; } } if (result != null) { String lastString =
	 * sqlStr.substring(sqlStr.length() - 5); if (lastString.equals("where")) {
	 * sqlStr += " result = " + "'" + result + "'"; } else { sqlStr +=
	 * " and result = " + "'" + result + "'"; } } if (resultType != null) {
	 * String lastString = sqlStr.substring(sqlStr.length() - 5); if
	 * (lastString.equals("where")) { sqlStr += " result_type = " + "'" +
	 * resultType + "'"; } else { sqlStr += " and result_type = " + "'" +
	 * resultType + "'"; } } System.out.println(sqlStr); try {
	 * jdbcTemplate.execute(sqlStr); } catch (DataAccessException e) { // TODO
	 * Auto-generated catch block return "fail"; } return "success";
	 * 
	 * 
	 * 
	 * 
	 * }
	 */

}
