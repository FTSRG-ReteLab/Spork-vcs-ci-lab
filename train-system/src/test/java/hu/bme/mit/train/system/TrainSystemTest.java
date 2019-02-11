package hu.bme.mit.train.system;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.interfaces.TrainTachograph;
import hu.bme.mit.train.system.TrainSystem;

public class TrainSystemTest {

	TrainController controller;
	TrainSensor sensor;
	TrainUser user;
	TrainTachograph tachograph;

	@Before
	public void before() {
		TrainSystem system = new TrainSystem();
		controller = system.getController();
		sensor = system.getSensor();
		user = system.getUser();

		tachograph = system.getTachograph();

		sensor.overrideSpeedLimit(50);
	}

	@Test
	public void setSpeedLimit() {
		sensor.overrideSpeedLimit(100);
		Assert.assertEquals(100, sensor.getSpeedLimit());
	}

	@Test
	public void testTachograph() {
		Assert.assertEquals(0,tachograph.getEntries());

		tachograph.tick(controller,user);
		tachograph.tick(controller,user);
		tachograph.tick(controller,user);

		Assert.assertNotEquals(0,tachograph.getEntries());

	}
	
	@Test
	public void OverridingJoystickPosition_IncreasesReferenceSpeed() {
		sensor.overrideSpeedLimit(10000000);

		Assert.assertEquals(0, controller.getReferenceSpeed());

		user.overrideJoystickPosition(5000000);

		controller.followSpeed();
		Assert.assertEquals(5000000, controller.getReferenceSpeed());
		controller.followSpeed();
		Assert.assertEquals(10000000, controller.getReferenceSpeed());
		controller.followSpeed();
		Assert.assertEquals(10000000, controller.getReferenceSpeed());
	}

	@Test
	public void OverridingJoystickPositionToNegative_SetsReferenceSpeedToZero() {
		user.overrideJoystickPosition(4);
		controller.followSpeed();
		user.overrideJoystickPosition(-5);
		controller.followSpeed();
		Assert.assertEquals(0, controller.getReferenceSpeed());
	}

	
}
