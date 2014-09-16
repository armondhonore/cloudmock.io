package com.cloudmock.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CommonUtils
{
	public String getResponse(final String contentType, final String action) throws FileNotFoundException, IOException
	{
		File file = null;

		if (contentType.equalsIgnoreCase("application/x-www-form-urlencoded"))
		{
			file = new File("src" + File.separator + "main" + File.separator + "resources" + File.separator + "aws"
					+ File.separator + "XMLResponse" + File.separator + action + ".xml");

		}
		else if (contentType.equalsIgnoreCase("application/json"))
		{

			file = new File("src" + File.separator + "main" + File.separator + "resources" + File.separator + "aws"
					+ File.separator + "JSONResponse" + File.separator + action + ".json");
		}

		Reader reader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(reader);
		StringBuilder sbJSONCommand = new StringBuilder();

		String s = null;
		while ((s = bufferedReader.readLine()) != null)
		{
			sbJSONCommand.append(s);
			sbJSONCommand.append("\n");
		}
		bufferedReader.close();
		reader.close();
		return sbJSONCommand.toString();
	}

	public ArrayList<String> parseInstanceIds(Map<String, String[]> requestParams)
	{

		ArrayList<String> instanceIds = new ArrayList<String>();
		for (String key : requestParams.keySet())
		{
			if (key.matches("^InstanceId.([0-9]+)$"))
			{
				instanceIds.add(requestParams.get(key)[0]);
			}
		}
		return instanceIds;
	}
}
