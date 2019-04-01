package hu.bme.mit.train.controller;

import java.util.Date;
import java.lang.System;
import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController {

	private int timeUnit = 1 * 1000; // 2 seconds
	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	private int speedRate = 0;

	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}

		enforceSpeedLimit();
	}
	@Override
	public void setSuperSpeed(int speedRate) {
		this.speedRate = speedRate;
	}
	
	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}
	
	@Override
	public void updateReferenceSpeed() {
		while(true) {
			long start = System.currentTimeMillis();
			long elapsed = 0L;
			
			while (elapsed < timeUnit) {
				followSpeed();
				elapsed = (new Date()).getTime() - start;
			}
			
		}
	}
}
