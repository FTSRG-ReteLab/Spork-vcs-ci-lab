package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;
import java.lang.Thread;

public class TrainControllerImpl extends Thread implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	private int speedRate = 0;


	public TrainControllerImpl() {
		start();
	}


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
	public void run() {
		try {
			while (true) {
				Thread.sleep(1001);
				followSpeed();
			}
		} catch (InterruptedException e) {
			System.out.println("rip");
			Thread.currentThread().interrupt();
		}
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

}
