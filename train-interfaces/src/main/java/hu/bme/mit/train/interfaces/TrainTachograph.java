package hu.bme.mit.train.interfaces;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;

public interface TrainTachograph {

	public void tick(TrainController controller, TrainUser user);


	public int getEntries();



}


