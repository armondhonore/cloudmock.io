package com.cloudmock.api.aws.ec2;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudmock.util.CommonUtils;

@RestController
public class DescribeInstancesController
{

	@Autowired
	CommonUtils commonUtils;

	@RequestMapping("/aws/ec2/describeinstances")
	public String mockAWSCloud(@Valid CommonRequestParams params,
			@RequestHeader(value = "content-type") String contentType) throws IOException
	{

		/*
		 * TODO Request have the params present in command For eg: CLI command:
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

		return commonUtils.getResponse(contentType, params.getAction().toLowerCase());
	}

	@RequestMapping("/aws/ec2/runinstances")
	public String runInstancesMock(@Valid CommonRequestParams params,
			@RequestParam(value = "MinCount", required = true) Integer minCount, @RequestParam(value = "MaxCount",
					required = true) Integer maxCount, @RequestHeader(value = "content-type") String contentType)
			throws IOException
	{
		System.out.println(params);
		return commonUtils.getResponse(contentType, params.getAction().toLowerCase());
	}

	@RequestMapping("/aws/ec2/describeinstancestatus")
	public String describeInstanceStatusMock(@Valid CommonRequestParams params,
			@RequestHeader(value = "content-type") String contentType) throws IOException
	{
		System.out.println(params);
		return commonUtils.getResponse(contentType, params.getAction().toLowerCase());
	}

	@RequestMapping("/aws/ec2/getconsoleoutput")
	public String getConsoleOutputMock(@Valid CommonRequestParams params, @RequestParam(required = true,
			value = "InstanceId") String instanceId, @RequestHeader(value = "content-type") String contentType,
			HttpServletRequest request) throws IOException
	{
		System.out.println(params);
		return commonUtils.getResponse(contentType, params.getAction().toLowerCase());
	}

	@RequestMapping("/aws/ec2/getpassworddata")
	public String getPasswordDataMock(@Valid CommonRequestParams params, @RequestParam(required = true,
			value = "InstanceId") String instanceId, @RequestHeader(value = "content-type") String contentType)
			throws IOException
	{
		System.out.println(params);
		return commonUtils.getResponse(contentType, params.getAction().toLowerCase());
	}

	// TODO Need to check to map all instance IDs to String array
	@RequestMapping("/aws/ec2/terminateinstances")
	public String terminateinstances(@Valid CommonRequestParams params, @RequestParam(required = false,
			value = "InstanceId.1") String[] instanceId, @RequestHeader(value = "content-type") String contentType)
			throws IOException
	{
		System.out.println(params);
		return commonUtils.getResponse(contentType, params.getAction().toLowerCase());
	}
	//
	// Map<String, String[]> map = request.getParameterMap();
	//
	// for (String key : map.keySet())
	// {
	// System.out.println(key + "::" + map.get(key));
	// }

}
