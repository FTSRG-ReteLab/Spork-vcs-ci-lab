package hu.bme.mit.train.tachograph;

import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;
import hu.bme.mit.train.interfaces.TrainTachograph;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;


public class TrainTachographImpl implements TrainTachograph {

	private Table<Integer,Integer,Integer> data = TreeBasedTable.create();
	private int tick_count = 0;


	@Override
	public void tick(TrainController controller, TrainUser user) {

		data.put(tick_count,user.getJoystickPosition(),controller.getReferenceSpeed());

		tick_count++;
	}


	@Override
	public int getEntries() {

		return data.size();

	}



}
