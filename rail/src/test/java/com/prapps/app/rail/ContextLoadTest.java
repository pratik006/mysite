package com.prapps.app.rail;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.prapps.app.rail.dto.Train;
import com.prapps.app.rail.dto.TrainType;
import com.prapps.app.rail.repo.TrainRepo;
import com.prapps.app.rail.service.RailService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppCoreApplicationTestConfig.class})
@TestPropertySource(locations= {"classpath:/test-application.properties"})
@Transactional
public class ContextLoadTest {
	private static Logger LOG = LoggerFactory.getLogger(ContextLoadTest.class);
	@Autowired TrainRepo trainRepo;
	@Autowired RailService railService;
	
	@Test public void testLoadEnums() {
		Assert.assertEquals(TrainType.Special, trainRepo.findOne(841l).getType());
	}

	@Test public void testfindTrains() {
		List<Train> trains= railService.findTrains("SDAH", "BBT", null, null, TrainType.getSuburbanTrainTypes(), 1, 50);
		for (Train train : trains) {
			LOG.debug("train: "+train+"\t"+train.getRoutes().get(0).getDeparture()+train.getRoutes().get(1).getDeparture());
		}
		Assert.assertEquals(50, trains.size());
	}
	
	@Test public void testfindNearestRegion() {
		Assert.assertEquals(2, railService.getNearestRegion(22.51986383f, 88.37203592f).size());
		Assert.assertEquals("HWH-SDAH", railService.getNearestRegion(22.51986383f, 88.37203592f).iterator().next().getCode());
	}
	
}
