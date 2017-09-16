package com.avancepay.example.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.avancepay.example.dao.DeviceDao;
import com.avancepay.example.model.DeviceData;

@Component("deviceController")
public class DeviceController {
	
	@Autowired
	@Qualifier("deviceDao")
	private DeviceDao dao;
	
	
	public List<DeviceData> getDeviceData(Integer locationNumberBiggerThan, Integer locationNumberLessOrEqualThan, 
			String nameStartsWith, String nameEndsWith, String nameContains ) {
		List<DeviceData> deviceList = dao.getDaoDeviceData(locationNumberBiggerThan, locationNumberLessOrEqualThan,
				nameStartsWith, nameEndsWith, nameContains);
		return deviceList;
		//return new LinkedList<DeviceData>();
		/*if(title == null
				|| title.trim().length() == 0) {
			throw new IllegalArgumentException("Title parameter must be defined");
		 }
		JSONParser parser = new JSONParser();
		List<MovieTitleLocation> movieNameLocationList = new LinkedList<MovieTitleLocation>();

		try {
			Object obj = parser.parse(new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(env.getProperty(MOVIES_FILE_LOCATION)))));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray moviesList = (JSONArray) jsonObject.get("data");

			@SuppressWarnings("unchecked")
			Iterator<JSONArray> iterator = moviesList.iterator();
			while (iterator.hasNext()) {
				JSONArray jsonArray = (JSONArray) iterator.next();
				if(jsonArray != null){
					if(jsonArray.get(8) != null && title.trim().equals((String) jsonArray.get(8))){
						MovieTitleLocation movieData= new MovieTitleLocation();
						if(jsonArray.get(10) != null){
							movieData.setLocations((String) jsonArray.get(10));
						} else {
							movieData.setLocations("---");
						}
						movieData.setTitle((String) jsonArray.get(8));
						movieNameLocationList.add(movieData);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return movieNameLocationList;*/
	}

	@ExceptionHandler
	void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}

}
