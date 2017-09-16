package com.avancepay.example.rest.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.avancepay.example.controllers.DeviceController;
import com.avancepay.example.model.DeviceData;

@RestController
public class RestHandler {

	@Autowired
	@Qualifier("deviceController")
	private DeviceController deviceController;
	
	@RequestMapping(value = "/DeviceService", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<DeviceData> getMovieDataFilterTitle(@RequestParam(value="locationNumberBiggerThan", required=false) Integer locationNumberBiggerThan,
			@RequestParam(value="locationNumberLessOrEqualThan", required=false) Integer locationNumberLessOrEqualThan,
			@RequestParam(value="nameStartsWith", required=false) String nameStartsWith,
			@RequestParam(value="nameEndsWith", required=false) String nameEndsWith,
			@RequestParam(value="nameContains", required=false) String nameContains) {
		return deviceController.getDeviceData(locationNumberBiggerThan, locationNumberLessOrEqualThan,
				nameStartsWith, nameEndsWith, nameContains);
	}
}
