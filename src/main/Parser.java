package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Twitch_Information;

public class Parser
{
	private final String USER_AGENT = "Mozilla/5.0";

	// Get Informations about the 25 Top Twitch Streams
	// return: JSONArray with Information about the Top Streams
	public JSONArray getFavoriteStreams(int number) throws Exception
	{
		//URL to get Informations about Twitch Top Streams
		URL url = new URL("https://api.twitch.tv/kraken/streams?limit=" + number);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null)
		{
			response.append(inputLine);
		}
		in.close();

		//Get Informations about the Streams from the JSON-Format
		JSONObject obj = new JSONObject(response.toString());
		JSONArray arr = obj.getJSONArray("streams");
		return arr;
	}

	// Save important Stream Informations
	// return: ArrayList with filtered Informations about the Top Streams
	public ArrayList<Twitch_Information> getStreamInformations(
			JSONArray streams) throws Exception
	{
		ArrayList<Twitch_Information> arr_twitch = new ArrayList<Twitch_Information>(
				25);
		for (int i = 0; i < streams.length(); i++)
		{
			Twitch_Information twitch = new model.Twitch_Information();
			JSONObject singleStream = streams.getJSONObject(i);
			// StreamID
			twitch.setStreamID(singleStream.getLong("_id"));
			// GameName
			twitch.setGameName(singleStream.getString("game"));
			// Viewers
			twitch.setViewers(singleStream.getInt("viewers"));
			// Created at
			twitch.setCreatedAt(singleStream.getString("created_at"));
			// Video Height
			twitch.setVideo_height(singleStream.getInt("video_height"));
			// Average Frames per Second
			twitch.setAverage_fps(singleStream.getDouble("average_fps"));

			JSONObject streamChannel = streams.getJSONObject(i).getJSONObject(
					"channel");
			// Broadcaster Language
			// Not every Stream has a Broadcaster Language JSON-Object
			try
			{
				twitch.setBroadcaster_language(streamChannel
						.getString("broadcaster_language"));
			} catch (JSONException e)
			{
				twitch.setBroadcaster_language(null);
			}
			// Partner Flag
			// Not every Stream has a Partner Flag JSON-Object
			try
			{
				twitch.setPartner(streamChannel.getBoolean("partner"));
			} catch (JSONException e)
			{
				twitch.setPartner(false);
			}
			// Channel ID
			twitch.setChannelID(streamChannel.getLong("_id"));
			// Channel Name
			twitch.setChannelName(streamChannel.getString("name"));
			// Delay
			// In some Streams the Delay is set to null and that isn't an
			// int-value
			try
			{
				twitch.setDelay(streamChannel.getInt("delay"));
			} catch (JSONException e)
			{
				twitch.setDelay(-1);
			}

			// Save Information in Array
			arr_twitch.add(twitch);
		}
		return arr_twitch;
	}

	//Save Stream-Informations into File
	public void saveStreamInformationsIntoFile(String path, String fileName,
			ArrayList<Twitch_Information> streamInformations)
			throws IOException
	{
		File file = new File(path + fileName);

		FileWriter writer = new FileWriter(file);

		for (int i = 0; i < streamInformations.size(); i++)
		{
			writer.write(streamInformations.get(i).toString());
			writer.write(System.getProperty("line.separator"));
			writer.flush();
		}
		writer.close();
	}
}
