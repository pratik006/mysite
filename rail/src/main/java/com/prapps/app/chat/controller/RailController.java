package com.prapps.app.chat.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prapps.app.chat.service.RailService;
import com.prapps.app.core.util.PrincipalHelper;
import com.prapps.app.rail.dto.SearchType;
import com.prapps.app.rail.dto.Station;
import com.prapps.app.rail.dto.Train;
import com.prapps.app.rail.dto.TrainType;

@Controller
@RequestMapping("/rest/rail")
public class RailController {
	
	@Autowired PrincipalHelper helper;
	@Autowired RailService railService;
	
	@RequestMapping(value = "/hyd-mmts/stations", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Collection<Station> getStations() {
		return railService.getStations(SearchType.HYD_MMTS);
	}
	
	@RequestMapping(value = "/hyd-mmts/findTrains", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Collection<Train> findTrains(@RequestParam("from") String from, @RequestParam("to") String to, 
			@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name="size", defaultValue="10") int pageSize) {
		return railService.findTrains(from, to,TrainType.MMTS, page, pageSize);
	}
	
	@RequestMapping(value = "/hyd-mmts/findNearestStations", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Collection<Station> getNearestStation(@RequestParam("lat") float lat, @RequestParam("lon") float lon) {
		return railService.getNearestStations(lat, lon);
	}
}
