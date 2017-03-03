package com.prapps.apps.rail;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.prapps.app.rail.dto.TrainType;
import com.prapps.app.rail.repo.TrainRepo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppCoreApplicationTestConfig.class})
@TestPropertySource(locations= {"classpath:/test-application.properties"})
public class ContextLoadTest {
	@Autowired TrainRepo trainRepo;
	
	@Test public void testLoadEnums() {
		Assert.assertEquals(TrainType.Special, trainRepo.findOne(841l).getType());
	}
	
}
