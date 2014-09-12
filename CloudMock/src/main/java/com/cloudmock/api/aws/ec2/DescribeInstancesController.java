package com.cloudmock.api.aws.ec2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DescribeInstancesController
{

	@RequestMapping("/aws/ec2/describeinstances")
	public String mockAWSCloud(@RequestParam(value = "Signature", required = false) String signature, @RequestParam(
			value = "Version", required = false) String version, @RequestParam(value = "AWSAccessKeyId",
			required = false) String awsAccessKeyId,
			@RequestParam(value = "SignatureMethod", required = false) String signatureMethod, @RequestParam(
					value = "Timestamp", required = false) String timeStamp, @RequestParam(value = "Action",
					required = false) String action, @RequestHeader(value = "content-type") String contentType)
			throws IOException
	{

		/*
		 * TODO Request have the params present in command For eg: CLI command:
		 * aws ec2 describe-images --image-ids ami-xxxxx --endpoint-url
		 * http://127.0.1.1:8080/aws
		 * 
		 * Params in request are: ImageId.1 : ami-xxxxx Action : DescribeImages
		 * SignatureMethod : HmacSHA256 AWSAccessKeyId : AKIXU3XTSPTPOA
		 * SignatureVersion : 2 Version : 2013-10-15 Signature :
		 * olDIF1AjWgBUtOmyUGZD+sqo= Timestamp : 2014-09-10T10:26:20.985215
		 * 
		 * 
		 * So the command can be formed using the params and can be used with
		 * CLIExecutor to process it on AWS server and get the response.
		 */

		/*
		 * TODO 1. Need to set content type for the response.
		 * 
		 * 2. Using CLI use debug mode with any command then it will show the
		 * response XML which is returned by AWS APIs.
		 * 
		 * 3. When JSON returned in response with response content type
		 * application/json
		 * 
		 * String mockResponse =
		 * "{\"Images\": [{\"ImageId\": \"ami-xxxxx\",\"RootDeviceName\": \"/dev/sda1\",\"ImageType\": \"machine\",\"Description\": \"Amazon Linux AMI x86_64 PV EBS\",\"ImageOwnerAlias\": \"amazon\",\"Public\": true, \"RootDeviceType\": \"ebs\", \"State\":\"available\"}]}\""
		 * ; reponse.setContentType("application/json");
		 * 
		 * then the CLI client shows: Not well-formed(invalid token): line 1,
		 * column 0
		 * 
		 * 4. Issue is to create the XML response which can be parsed by CLI
		 * client.
		 */

		return getResponse(contentType, action);
	}

	private String getResponse(String contentType, String action) throws FileNotFoundException, IOException
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
		return sbJSONCommand.toString();
	}

	@RequestMapping("/aws/ec2/runinstances")
	public String runInstancesMock(@RequestParam(value = "Signature", required = false) String signature,
			@RequestParam(value = "Version", required = false) String version, @RequestParam(value = "AWSAccessKeyId",
					required = false) String awsAccessKeyId,
			@RequestParam(value = "SignatureMethod", required = false) String signatureMethod, @RequestParam(
					value = "MinCount", required = false) Integer minCount, @RequestParam(value = "MaxCount",
					required = false) Integer maxCount,
			@RequestParam(value = "SignatureVersion", required = false) Integer signatureVersion, @RequestParam(
					value = "Timestamp", required = false) String timeStamp, @RequestParam(value = "Action",
					required = false) String action, @RequestHeader(value = "content-type") String contentType)
			throws IOException
	{

		return getResponse(contentType, action.toLowerCase());

	}
}