package main;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;

import model.TwitchModel;

public class TwitchCrawlerThread extends TimerTask
{

	private TwitchModel model;
	private String storage;
	private int number;

	public TwitchCrawlerThread(TwitchModel model, int number, String storage)
	{
		this.model = model;
		this.number = number;
		this.storage = storage;
	}

	@Override
	public void run()
	{
		System.out.println("Thread aufgerufen");

		// Get Informations about Twitch-Streams
		try
		{
			model.setStreamInformations(model.getParser()
					.getStreamInformations(
							model.getParser().getFavoriteStreams(number)));
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		String date = null;
		
		System.out.println("Daten erhalten");
		// Save Informations to File
		try
		{
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat dt = new SimpleDateFormat(
					"EEE.,d.MM.yyyy,HH.mm.ss");
			DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM);
			date = dt.format(cal.getTime());

			model.getParser()
					.saveStreamInformationsIntoFile(
							(storage),
							date, model.getStreamInformations());
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}

		System.out.println("Daten abgespeichert - " + date);
	}

}
