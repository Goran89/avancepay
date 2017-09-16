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
		String query = "SELECT * FROM DEVICE";
		// dynamic query creation
		boolean isDynamicCreated = false;
		if(locationNumberBiggerThan != null || locationNumberLessOrEqualThan != null
			|| nameStartsWith != null || nameEndsWith != null || nameContains != null) {
			query = query + " WHERE ";
			isDynamicCreated = true;
		}
		if(locationNumberBiggerThan != null){
			query = query + " LOCATIONNUMBER  > '" + locationNumberBiggerThan + "' AND ";
		}
		if(locationNumberLessOrEqualThan != null){
			query = query + " LOCATIONNUMBER  <= '" + locationNumberLessOrEqualThan + "' AND ";
		}
		if(nameStartsWith != null){
			query = query + " NAME LIKE '" + nameStartsWith + "%' AND ";
		}
		if(nameEndsWith != null){
			query = query + " NAME LIKE '%" + nameEndsWith + "' AND ";
		}
		if(nameContains != null){
			query = query + " NAME LIKE '%" + nameContains + "%' AND ";
		}
		if(isDynamicCreated){
			query = query.substring(0, query.length() - 5);
		}
		// execute Query
		Session session = HibernateUtil.getSessionFactory().openSession();
		SQLQuery sqlQuery = session.createSQLQuery(query);
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
		/*Iterator resultIterator = queryResult.iterate();
		while(resultIterator.hasNext()){
			DeviceData devRes = (DeviceData) resultIterator.next();
			DeviceData dev = new DeviceData();
			dev.setId(devRes.getId());
			deviceList.add(dev);
		}*/
		return deviceList;
//		return new LinkedList<DeviceData>();
	}

}
