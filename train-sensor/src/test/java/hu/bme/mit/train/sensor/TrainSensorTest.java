package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.sensor.TrainSensorImpl;

import hu.bme.mit.train.controller.TrainControllerImpl;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.user.TrainUserImpl;

import static org.mockito.Mockito.*;

public class TrainSensorTest {


	TrainSensor sensor;
	TrainController mockController;
	TrainUser mockUser;



    @Before
    public void before() {


	mockController = mock(TrainControllerImpl.class);
	mockUser = mock(TrainUserImpl.class);


	// TrainSensorImpl(controller, user);
	sensor = new TrainSensorImpl(mockController, mockUser);


    }

    @Test
    public void test_Sensor_HigherThanRefenreceSpeed() {

		when(mockController.getReferenceSpeed()).thenReturn(50);

		sensor.overrideSpeedLimit(55);


		Assert.assertFalse(mockUser.getAlarmState());


    }

    @Test
    public void test_Sensor_LowerThan0() {

		sensor.overrideSpeedLimit(-42);

		verify(mockUser,atLeastOnce()).setAlarmState(true);

    }

    @Test
    public void test_Sensor_HigherThan500() {

		sensor.overrideSpeedLimit(501);


		verify(mockUser,atLeastOnce()).setAlarmState(true);

    }

    @Test
    public void test_Sensor_LessThanReference50Percent() {


		when(mockController.getReferenceSpeed()).thenReturn(150);

		sensor.overrideSpeedLimit(45);


		verify(mockUser,atLeastOnce()).setAlarmState(true);

    }
}
