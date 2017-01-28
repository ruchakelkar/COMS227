package hw1;

/**
 * Model of a radio.
 * @author RuchaKelkar
 *
 */

public class Radio 
{
	/**
	 * The increment by which the volume increases.
	 */
	public static final double VOLUME_INCREMENT = 0.16;
	
	/**
	 * The number of total stations.
	 */
	private int numOfStations;
	
	/**
	 * The current station number the Radio is set at. 
	 */
	private int currentStationNum;
	
	/**
	 * The current volume the Radio is set at.
	 */
	private double currentVolume; 
	
	/**
	 * The minimum frequency value the Radio can take on.
	 */
	private double minFrequency;
	
	/**
	 * The maximum frequency value the Radio can take on.
	 */
	private double maxFrequency;
	
	/**
	 * The frequency intervals between station numbers.
	 */
	private int interval;
	
	/**
	 * The frequency value the Radio is currently at.
	 */
	private double broadcastFrequency;
	
	
	/**
	 * Constructs a Radio with the given range of frequencies and the given number of stations.
	 * @param givenMinFrequency
	 * 		the lowest frequency for this Radio 
	 * @param givenMaxFrequency
	 * 		the highest frequency for this Radio
	 * @param givenNumStations
	 * 		the number of stations for this Radio
	 */
	
	public Radio(double givenMinFrequency, double givenMaxFrequency, int givenNumStations)
	{
		minFrequency = givenMinFrequency;
		maxFrequency = givenMaxFrequency;
		numOfStations = givenNumStations;
		interval = (int) ((maxFrequency-minFrequency)/numOfStations);
		broadcastFrequency = (currentStationNum * interval) + minFrequency + (interval/2);
	}
	
	/**
	 * Returns the broadcast frequency for the current station.
	 * @return
	 * 		the current station's broadcast frequency
	 */
	
	public double getFrequency()
	{
		return broadcastFrequency;
	}
	
	/**
	 * Returns the current station number.
	 * @return
	 * 		the current station number
	 */
	
	public int getStationNumber()
	{
		return currentStationNum;
	}
	
	/**
	 * Returns the current volume.
	 * @return
	 * 		the current volume
	 */
	
	public double getVolume()
	{
		return currentVolume;
	}
	
	/**
	 * Sets the current station number to the one that corresponds to the broadcast frequency that is closest to the given double value (within range, otherwise will set to min/max value.)
	 * @param frequency
	 * 		the current frequency the radio is at
	 */
	
	public void setStationFromFrequency(double frequency)
	{ 
		for (int i = 1; i <= numOfStations; ++i)
		{
			if((frequency - minFrequency) <= i * interval)
			{
				currentStationNum = i - 1; 
				break; 
			}
			
		}	
		
		if(frequency > maxFrequency)
		{
			currentStationNum = numOfStations - 1;
			broadcastFrequency = (currentStationNum * interval) + minFrequency + (interval/2);		
			return;
		}
		
		broadcastFrequency = (currentStationNum * interval) + minFrequency + (interval/2);		
	}
	
	/**
	 * Sets the station to the given station number. If the number exceeds the range, it wraps around and sets to a valid station number.  
	 * @param stationNumber
	 * 		the station number that the radio will be set to
	 */
	
	public void setStationNumber(int stationNumber)
	{
		int maxStationNum = numOfStations - 1;
		currentStationNum = Math.max(stationNumber, 0);
		currentStationNum = Math.min(currentStationNum, maxStationNum);
		broadcastFrequency = (currentStationNum * interval) + minFrequency + (interval/2);
		
	}
	
	
	/**
	 * Sets the radio station down by 1, wrapping around if it goes below the number 0 station. 
	 */
	
	public void stationDown()
	{
		currentStationNum = ((currentStationNum - 1) + numOfStations) % numOfStations;
		broadcastFrequency = (currentStationNum * interval) + minFrequency + (interval/2);
	}
	
	/**
	 * Sets the radio station up by 1, wrapping around if it goes above the highest defined station.
	 */
	
	public void stationUp()
	{
		currentStationNum = (currentStationNum + 1) % numOfStations;
		broadcastFrequency = (currentStationNum * interval) + minFrequency + (interval/2);
		
	}
	
	/**
	 * Decreases the volume by VOLUME_INCREMENT, not going below 0.0.
	 */
	
	public void volumeDown()
	{ 
		currentVolume = Math.max(currentVolume - VOLUME_INCREMENT, 0.0);
		
	}
	
	/**
	 * Increases the volume by VOLUME_INCREMENT, not going above 1.0. 
	 */
	
	public void volumeUp()
	{
		currentVolume = Math.min(currentVolume + VOLUME_INCREMENT, 1.0);
	}
	
}
