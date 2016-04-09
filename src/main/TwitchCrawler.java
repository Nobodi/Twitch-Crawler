package main;

import java.io.File;
import java.util.Timer;

import javax.swing.JFrame;

import model.TwitchModel;

public class TwitchCrawler extends JFrame
{
	private static final long serialVersionUID = 1L;

	public static void main(String[] args)
	{
		// Storage Location. Example: "C:\\Results\\"
		String storage = "C:\\";
		// Number of Streams (between 25 and 100)
		int number = 25;
		// Time that runs out before restart the Thread
		long time = 3600000;

		try
		{
			if (!args[0].isEmpty())
			{
				storage = args[0];
			}

			if (!args[1].isEmpty())
			{
				time = Long.parseLong(args[1]);
			}

			if (!args[2].isEmpty())
			{
				number = Integer.parseInt(args[2]);
			}
		} catch (NumberFormatException e)
		{
			System.err
					.println("Fehler bei der Angabe der Parameter. Erlaubt sind nur Zahlen. "
							+ "Parameter 1: Zeit in Millisekunden; Parameter 2: Anzahl Streams (25 - 100).");
			return;
		}

		// Start scheduled Thread

		File stats = new File(storage);

		if (!storage.equals("") && stats.exists())
		{
			Timer timer = new Timer();
			TwitchCrawlerThread t = new TwitchCrawlerThread(new TwitchModel(),
					number, storage);
			// Restart Thread every hour
			timer.scheduleAtFixedRate(t, 0, time);
		} else
		{
			System.out.println("Fehler: Speicherort nicht angegeben!");
		}
	}
}
