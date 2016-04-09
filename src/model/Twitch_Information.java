package model;

public class Twitch_Information
{
	private long streamID, channelID;
	private String gameName, channelName, createdAt, broadcaster_language;
	private int viewers, video_height, delay;
	private double average_fps;
	private boolean partner;

	public Twitch_Information(long streamID, long channelID, String gameName,
			String channelName, String createdAt)
	{
		this.streamID = streamID;
		this.channelID = channelID;
		this.gameName = gameName;
		this.channelName = channelName;
		this.createdAt = createdAt;
	}

	public Twitch_Information()
	{

	}

	public long getStreamID()
	{
		return streamID;
	}

	public void setStreamID(long streamID)
	{
		this.streamID = streamID;
	}

	public long getChannelID()
	{
		return channelID;
	}

	public void setChannelID(long channelID)
	{
		this.channelID = channelID;
	}

	public String getGameName()
	{
		return gameName;
	}

	public void setGameName(String gameName)
	{
		this.gameName = gameName;
	}

	public String getChannelName()
	{
		return channelName;
	}

	public void setChannelName(String channelName)
	{
		this.channelName = channelName;
	}

	public String getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(String createdAt)
	{
		this.createdAt = createdAt;
	}

	public String getBroadcaster_language()
	{
		return broadcaster_language;
	}

	public void setBroadcaster_language(String broadcaster_language)
	{
		this.broadcaster_language = broadcaster_language;
	}

	public int getViewers()
	{
		return viewers;
	}

	public void setViewers(int viewers)
	{
		this.viewers = viewers;
	}

	public int getVideo_height()
	{
		return video_height;
	}

	public void setVideo_height(int video_height)
	{
		this.video_height = video_height;
	}

	public int getDelay()
	{
		return delay;
	}

	public void setDelay(int delay)
	{
		this.delay = delay;
	}

	public double getAverage_fps()
	{
		return average_fps;
	}

	public void setAverage_fps(double average_fps)
	{
		this.average_fps = average_fps;
	}

	public boolean isPartner()
	{
		return partner;
	}

	public void setPartner(boolean partner)
	{
		this.partner = partner;
	}

	@Override
	public String toString()
	{
		String delimiter = ",";
		return streamID + delimiter + gameName + delimiter + viewers + delimiter + createdAt
				+ delimiter + video_height + delimiter + average_fps + delimiter
				+ broadcaster_language + delimiter + partner + delimiter + channelID + delimiter
				+ channelName + delimiter + delay;
	}

	public void print()
	{
		System.out.println("Stream-ID: " + streamID);
		System.out.println("Game Name: " + gameName);
		System.out.println("Viewers: " + viewers);
		System.out.println("Created at: " + createdAt);
		System.out.println("Video Height: " + video_height);
		System.out.println("Average Frames per Second: " + average_fps);
		System.out.println("Broadcaster Language: " + broadcaster_language);
		System.out.println("Partner: " + partner);
		System.out.println("Channel-ID: " + channelID);
		System.out.println("Channel Name: " + channelName);
		if (delay == -1)
		{
			System.out.println("Delay: null");
		} else
		{
			System.out.println("Delay: " + delay);
		}

	}

}
