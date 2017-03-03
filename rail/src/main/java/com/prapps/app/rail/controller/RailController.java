package com.prapps.app.rail.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prapps.app.core.util.PrincipalHelper;
import com.prapps.app.core.util.time.TimeUtil;
import com.prapps.app.rail.dto.SearchType;
import com.prapps.app.rail.dto.Station;
import com.prapps.app.rail.dto.Train;
import com.prapps.app.rail.dto.TrainType;
import com.prapps.app.rail.service.RailService;

@Controller
@RequestMapping("/rest/rail")
public class RailController {
	
	@Autowired PrincipalHelper helper;
	@Autowired RailService railService;
	@Autowired TimeUtil timeUtil;
	
	@RequestMapping(value = "/hyd-mmts/stations", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Collection<Station> getStations() {
		return railService.getStations(SearchType.HYD_MMTS);
	}
	
	@RequestMapping(value = "/hyd-mmts/findTrains", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Collection<Train> findTrains(@RequestParam("from") String from, 
			@RequestParam("to") String to,
			@RequestParam(name = "nextHourCount", required = false) Integer nextHourCount,
			@RequestParam(name = "page", defaultValue = "1") int page, 
			@RequestParam(name="size", defaultValue="10") int pageSize) {
		
		if (nextHourCount != null) {
			Calendar start = timeUtil.getCurrentTimeIst();
			Calendar end = (Calendar) start.clone();
			end.add(Calendar.HOUR, nextHourCount);
			return railService.findTrains(from, to, start, end, TrainType.MMTS, page, pageSize);
		}
		return railService.findTrains(from, to, null, null, TrainType.MMTS, page, pageSize);
	}
	
	@RequestMapping(value = "/hyd-mmts/findNearestStations", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Collection<Station> getNearestStation(@RequestParam("lat") float lat, @RequestParam("lon") float lon) {
		return railService.getNearestStations(lat, lon);
	}
	
	@RequestMapping(value = "/all-stations", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Collection<Station> getAllStations() {
		return railService.getStations(SearchType.ALL);
	}
	
	@RequestMapping(value = "/update", method = {RequestMethod.POST}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody String updateRoute(@RequestBody Train train) {
		railService.updateTrainRoute(train);
		return "success";
	}
	
	private static String BASE_LOCAL_URL="/home/pratik/git/openshift/apps/app-ui/src/test/pages/routes/";
	@RequestMapping(value = "/uri", method=RequestMethod.GET)
	public @ResponseBody String getUri(@RequestParam("path") String path) throws Exception {
			StringBuilder response = new StringBuilder();
			File file = null;
			String url = null;
			file = new File(BASE_LOCAL_URL+path.toLowerCase()+".html");
			url = "http://erail.in/"+path;
			
			if (!file.exists()) {
				URL obj = new URL(url);
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("User-Agent", "Mozilla/5.0");

				BufferedReader in = new BufferedReader(
				        new InputStreamReader(con.getInputStream()));
				String inputLine;

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				
				
				file.createNewFile();
				BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));
				os.write(response.toString().getBytes());
				os.close();
				
			}
			
			
			InputStream is = new FileInputStream(file);
			int read = -1;
			while((read = is.read()) != -1 ) {
				response.append((char)read);
			}
			is.close();
			
			
			return (response.toString());

	}
}
