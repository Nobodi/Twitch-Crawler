package model;

import java.util.ArrayList;

import main.Parser;

public class TwitchModel
{
	private ArrayList<Twitch_Information> streamInformations;
	private Parser parser;

	public TwitchModel()
	{
		parser = new Parser();
	}

	public ArrayList<Twitch_Information> getStreamInformations()
	{
		return streamInformations;
	}

	public void setStreamInformations(
			ArrayList<Twitch_Information> streamInformations)
	{
		this.streamInformations = streamInformations;
	}

	public Parser getParser()
	{
		return parser;
	}

}
