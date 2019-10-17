package uorocketry.basestation;

import java.util.ArrayList;

public class DataHandler {
	
	final int TIMESTAMP = 0;
	final int ALTITUDE = 1;
	final int LATITUDE = 2;
	final int LONGITUDE = 3;
	final int PITCH = 4;
	final int ROLL = 5;
	final int YAW = 6;
	final int ACCELX = 7;
	final int ACCELY = 8;
	final int ACCELZ = 9;
	final int VELOCITY = 20;
	final int BRAKE_PERCENTAGE = 11;
	final int ACTUAL_BRAKE_VALUE = 12;
	final int GPS_FIX = 13;
	final int GPS_FIX_QUALITY = 14;
	
	Data[] data = new Data[15];
	
	public String getFormattedData(ArrayList<String> labels) {
		String text = "<html>";
		
		for (int i = 0; i < data.length; i++) {
			text += labels.get(i) + ": " + data[i].getFormattedString() + "<br>";
		}
		
		return text + "</html>";
	}
	
	public void set(int index, String currentData) {
		
//		if (index == LATITUDE || index == LONGITUDE) {
//			float minutes = Float.parseFloat(currentData.substring(0, currentData.indexOf(".") - 2));
//			float degrees = Float.parseFloat(currentData.replace(minutes + "", ""));
//			
//			data[index] = new Data(degrees, minutes);
//		}
		
		float floatData = -1;
		
		try {
			floatData = Float.parseFloat(currentData);
		} catch (NumberFormatException e) {
			if (currentData.equals("ovf")) {
				//ovf means overflow
				floatData = Float.MAX_VALUE;
			} else {
				System.err.println("Number conversion failed for '" + currentData + "', -1 being used instead");
			}
		}
		
		data[index] = new Data(floatData);
	}
}
