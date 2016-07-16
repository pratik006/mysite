package com.prapps.app.trainapp.service;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.prapps.app.trainapp.dataaccess.ClassRepository;
import com.prapps.app.trainapp.dataaccess.StationRepository;
import com.prapps.app.trainapp.dataaccess.TrainRepository;
import com.prapps.app.trainapp.dataaccess.TrainRouteRepository;
import com.prapps.app.trainapp.dto.SearchRequest;
import com.prapps.app.trainapp.dto.Station;
import com.prapps.app.trainapp.dto.Train;
import com.prapps.app.trainapp.mapper.StationMapper;
import com.prapps.app.trainapp.mapper.TrainMapper;
import com.prapps.app.trainapp.persistence.TrainEntity;

@Service
public class TrainService {
	
	private StationRepository stationRepository;
	private TrainRepository trainRepository;
	private TrainRouteRepository trainRouteRepository;
	private TrainMapper trainMapper;
	private StationMapper stationMapper;
	
	@PersistenceContext
	 private EntityManager em;
	
	private Session getSession() {
		return em.unwrap(Session.class);
	}
	
	@Inject
	public TrainService(StationRepository stationRepository, TrainRouteRepository trainRouteRepository, 
			TrainRepository trainRepository, TrainMapper trainMapper, StationMapper stationMapper, ClassRepository classRepository) {
		this.stationRepository = stationRepository;
		this.trainRepository = trainRepository;
		this.trainRouteRepository = trainRouteRepository;
		this.trainMapper = trainMapper;
		this.stationMapper = stationMapper;
		
		
	}

	public List<Station> getMatchingStations(String match) {
		if (StringUtils.isNotBlank(match)) {
			return stationMapper.mapStations(stationRepository.findByNameContaining(match));
		}
		
		return stationMapper.mapStations(stationRepository.findAll());
	}
	
	public List<Train> searchTrains(SearchRequest request) {
		List<TrainEntity> trainEntities = null;
		if (request.getTypes().isEmpty()) {
			trainEntities = trainRepository.search(request.getFrom(), request.getTo());
		} else {
			trainEntities = trainRepository.search(request.getFrom(), request.getTo(), request.getTypes());
		}
		return trainMapper.mapTrains(trainEntities, true);
	}
	
	@Transactional
	public void updateTrainRoute(Train train) {
		TrainEntity trainEntity = null;
		if (!trainRepository.exists(train.getId())) {
			trainEntity = trainMapper.map(train);
			trainEntity = trainRepository.save(trainEntity);
		}
	}
	
	public String getHtml(String stationName, String type) {
		StringBuilder response = new StringBuilder();
		File file = null;
		String url = null;
		if (type != null && "route".equalsIgnoreCase(type)) {
			file = new File("/home/pratik/html-pages/routes/"+stationName+".html");
			url = "http://erail.in/"+stationName+"/route";
		} else {
			stationName = stationName.replaceAll(" ", "-");
			file = new File("/home/pratik/html-pages/"+stationName+".html");
			url = "http://erail.in/"+stationName+"-railway-station/";
		}
		
		
		if (file.exists()) {
			try {
				InputStream is = new FileInputStream(file);
				int read = -1;
				while((read = is.read()) != -1 ) {
					response.append((char)read);
				}
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
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
				
				
				System.out.println(file);
				if (!file.exists()) {
					file.createNewFile();
				}
				BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));
				os.write(response.toString().getBytes());
				os.close();
				
				}catch(Exception e) {
					e.printStackTrace();
				}
			
			
		}
		
		
		//print result
		return (response.toString());

	}
	
	
}
