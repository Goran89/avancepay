package com.avancepay.example.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.avancepay.example.model.DeviceData;
import com.avancepay.example.util.HibernateUtil;

@Component("deviceDao")
public class DeviceDao {
	
	public List<DeviceData> getDaoDeviceData(Integer locationNumberBiggerThan, Integer locationNumberLessOrEqualThan,
			String nameStartsWith, String nameEndsWith, String nameContains) {
		List<DeviceData> deviceList = new LinkedList<DeviceData>();
		// dynamic query creation
		StringBuilder query = new StringBuilder();
		query.append(createDynamicQuery(locationNumberBiggerThan, locationNumberLessOrEqualThan, nameStartsWith,
				nameEndsWith, nameContains));
		// execute Query
		Session session = HibernateUtil.getSessionFactory().openSession();
		SQLQuery sqlQuery = session.createSQLQuery(query.toString());
		@SuppressWarnings("rawtypes")
		List listResult = sqlQuery.list();
		for(int i = 0; i < listResult.size(); i++){
			Object[] devRes = (Object[]) listResult.get(i);
			DeviceData dev = new DeviceData();
			dev.setId(((BigInteger)devRes[0]).intValue());
			dev.setInsertedDateTime((Date)devRes[1]);
			dev.setName((String)devRes[3]);
			dev.setLocationNumber((Integer)devRes[2]);
			deviceList.add(dev);
		}
		return deviceList;
	}

	private StringBuilder createDynamicQuery(Integer locationNumberBiggerThan, Integer locationNumberLessOrEqualThan,
			String nameStartsWith, String nameEndsWith, String nameContains) {
		boolean isDynamicCreated = false;
		StringBuilder query = new StringBuilder("SELECT * FROM DEVICE");
		if(locationNumberBiggerThan != null || locationNumberLessOrEqualThan != null
			|| nameStartsWith != null || nameEndsWith != null || nameContains != null) {
			query.append(" WHERE ");
			isDynamicCreated = true;
		}
		if(locationNumberBiggerThan != null){
			query.append(" LOCATIONNUMBER  > '" + locationNumberBiggerThan + "' AND ");
		}
		if(locationNumberLessOrEqualThan != null){
			query.append(" LOCATIONNUMBER  <= '" + locationNumberLessOrEqualThan + "' AND ");
		}
		if(nameStartsWith != null){
			query.append(" NAME LIKE '" + nameStartsWith + "%' AND ");
		}
		if(nameEndsWith != null){
			query.append(" NAME LIKE '%" + nameEndsWith + "' AND ");
		}
		if(nameContains != null){
			query.append(" NAME LIKE '%" + nameContains + "%' AND ");
		}
		if(isDynamicCreated){
			return new StringBuilder(query.substring(0, query.length() - 5));
		} else {
			return query;
		}
	}

}
