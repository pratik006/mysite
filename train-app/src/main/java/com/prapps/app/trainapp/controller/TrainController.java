package com.prapps.app.trainapp.controller;

import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prapps.app.trainapp.dto.SearchRequest;
import com.prapps.app.trainapp.dto.Station;
import com.prapps.app.trainapp.dto.Train;
import com.prapps.app.trainapp.dto.TrainStation;
import com.prapps.app.trainapp.service.TrainService;

@Controller
@ControllerAdvice
@RequestMapping("/rest/train")
public class TrainController {

	private TrainService trainService;
	
	@Inject
	public TrainController(TrainService trainService) {
		this.trainService = trainService;
	}
	
	@RequestMapping(value = "/stations", method = RequestMethod.GET)
	public @ResponseBody Collection<Station> getMatchedStations(@RequestParam(name = "query") String match) {
		if (StringUtils.isBlank(match) || match.length() < 3) {
			return Collections.emptyList();
		}
		
		return trainService.getMatchingStations(match);
	}
	
	@RequestMapping(value = "/search", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Collection<Train> searchTrains(@RequestBody SearchRequest request) {
		return trainService.searchTrains(request);
	}
	
	@RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody String updateRoute(@RequestBody TrainStation trainStation) {
		trainService.updateTrainRoute(trainStation);
		return "success";
	}
}
