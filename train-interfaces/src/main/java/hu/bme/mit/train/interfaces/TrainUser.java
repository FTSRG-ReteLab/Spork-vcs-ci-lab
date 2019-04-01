package hu.bme.mit.train.interfaces;

public interface TrainUser {

	boolean getAlarmState();

	void setAlarmState(boolean alarmState);

	int getJoystickPosition();

	boolean getAlarmFlag();

	void overrideJoystickPosition(int joystickPosition);

}
