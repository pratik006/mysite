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
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prapps.app.core.util.time.TimeUtil;
import com.prapps.app.rail.dto.Region;
import com.prapps.app.rail.dto.ResponseDetail;
import com.prapps.app.rail.dto.Station;
import com.prapps.app.rail.dto.Train;
import com.prapps.app.rail.dto.TrainType;
import com.prapps.app.rail.service.RailService;
import com.prapps.app.rail.type.SuburbanRegionType;

@Controller
@RequestMapping("/rest/rail")
public class RailController {

	@Autowired RailService railService;
	@Autowired TimeUtil timeUtil;

	@RequestMapping(value = "/stations", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Collection<Station> getStations(@RequestParam("region") String region) {
		SuburbanRegionType type = SuburbanRegionType.getByCode(region);
		return railService.getStations(type);
	}

	@RequestMapping(value = "/findTrains", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Page<Train> findTrains(@RequestParam("region") SuburbanRegionType region,
			@RequestParam("from") String from,
			@RequestParam("to") String to,
			@RequestParam(name = "nextHourCount", required = false) Integer nextHourCount,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name="size", defaultValue="10") int pageSize) {
		Page<Train> trains;
		if (nextHourCount != null) {
			Calendar start = timeUtil.getCurrentTimeIst();
			Calendar end = (Calendar) start.clone();
			end.add(Calendar.HOUR, nextHourCount);
			trains = railService.findTrains(from, to, start, end, TrainType.getSuburbanTrainTypes(), page, pageSize, ResponseDetail.ALL);
		}
		trains = railService.findTrains(from, to, null, null, TrainType.getSuburbanTrainTypes(), page, pageSize, ResponseDetail.ALL);
		return trains;
	}

	@RequestMapping(value = "/findNearestStations", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Collection<Station> getNearestStation(@RequestParam("region") SuburbanRegionType region,
			@RequestParam("lat") double lat, @RequestParam("lon") double lon) {
		return railService.getNearestStations(region, lat, lon);
	}

	@RequestMapping(value = "/findRegion", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Set<Region> getRegion(@RequestParam(name="lat", required=false) Double lat,
			@RequestParam(name = "lon", required = false) Double lon) {
		return lat!=null&&lon!=null?railService.getNearestRegion(lat, lon):railService.getAllRegions();
	}

	@RequestMapping(value = "/all-stations", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Collection<Station> getAllStations() {
		return railService.getStations(SuburbanRegionType.ALL);
	}

	@RequestMapping(value = "/update", method = {RequestMethod.POST}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody String updateRoute(@RequestBody Train train) {
		railService.updateTrainRoute(train);
		return "success";
	}

	private static String BASE_LOCAL_URL="/home/pratik/html-pages/routes/";
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
