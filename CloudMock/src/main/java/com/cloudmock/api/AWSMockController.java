package com.cloudmock.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AWSMockController
{

	@RequestMapping("/aws/ec2/describeinstances")
	public String mockAWSCloud(HttpServletRequest request) throws IOException
	{
		Map<String, String[]> params = request.getParameterMap();

		/*
		 * TODO Request have the params present in command For eg: CLI command:
		 * aws ec2 describe-images --image-ids ami-xxxxx --endpoint-url
		 * http://127.0.1.1:8080/aws
		 * 
		 * Params in request are: ImageId.1 : ami-xxxxx Action : DescribeImages
		 * SignatureMethod : HmacSHA256 AWSAccessKeyId : AKIAIDGQBXU3XTSPTPOA
		 * SignatureVersion : 2 Version : 2013-10-15 Signature :
		 * olDIF1AjB06H6E7SlThtJC/6ojhWgBUtOmyUGZD+sqo= Timestamp :
		 * 2014-09-10T10:26:20.985215
		 * 
		 * 
		 * So the command can be formed using the params and can be used with
		 * CLIExecutor to process it on AWS server and get the response.
		 */
		for (String key : params.keySet())
		{
			System.out.println(key + " : " + params.get(key)[0]);
		}
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

		if (request.getContentType().equalsIgnoreCase("text/xml"))
		{
			// Sample response for POC
			return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<DescribeInstancesResponse xmlns=\"http://ec2.amazonaws.com/doc/2013-10-15/\">\n    <requestId>d34cd57e-34c4-480d-8178-54bd6bd04c2c</requestId>\n    <reservationSet>\n        <item>\n            <reservationId>r-a52e90d5</reservationId>\n            <ownerId>533286118017</ownerId>\n            <groupSet>\n                <item>\n                    <groupId>sg-54b0263c</groupId>\n                    <groupName>default</groupName>\n                </item>\n            </groupSet>\n            <instancesSet>\n                <item>\n                    <instanceId>i-c8a64a26</instanceId>\n                    <imageId>ami-7c807d14</imageId>\n                    <instanceState>\n                        <code>16</code>\n                        <name>running</name>\n                    </instanceState>\n                    <privateDnsName>ip-10-180-178-102.ec2.internal</privateDnsName>\n                    <dnsName>ec2-54-242-149-76.compute-1.amazonaws.com</dnsName>\n                    <reason/>\n                    <keyName>awslinux</keyName>\n                    <amiLaunchIndex>0</amiLaunchIndex>\n                    <productCodes/>\n                    <instanceType>t1.micro</instanceType>\n                    <launchTime>2014-09-08T08:06:12.000Z</launchTime>\n                    <placement>\n                        <availabilityZone>us-east-1a</availabilityZone>\n                        <groupName/>\n                        <tenancy>default</tenancy>\n                    </placement>\n                    <kernelId>aki-919dcaf8</kernelId>\n                    <monitoring>\n                        <state>disabled</state>\n                    </monitoring>\n                    <privateIpAddress>10.180.178.102</privateIpAddress>\n                    <ipAddress>54.242.149.76</ipAddress>\n                    <groupSet>\n                        <item>\n                            <groupId>sg-54b0263c</groupId>\n                            <groupName>default</groupName>\n                        </item>\n                    </groupSet>\n                    <architecture>x86_64</architecture>\n                    <rootDeviceType>ebs</rootDeviceType>\n                    <rootDeviceName>/dev/sda1</rootDeviceName>\n                    <blockDeviceMapping>\n                        <item>\n                            <deviceName>/dev/sda1</deviceName>\n                            <ebs>\n                                <volumeId>vol-ea2187a5</volumeId>\n                                <status>attached</status>\n                                <attachTime>2014-09-08T08:06:16.000Z</attachTime>\n                                <deleteOnTermination>true</deleteOnTermination>\n                            </ebs>\n                        </item>\n                    </blockDeviceMapping>\n                    <virtualizationType>paravirtual</virtualizationType>\n                    <clientToken/>\n                    <hypervisor>xen</hypervisor>\n                    <networkInterfaceSet/>\n                    <ebsOptimized>false</ebsOptimized>\n                </item>\n            </instancesSet>\n        </item>\n    </reservationSet>\n</DescribeInstancesResponse>";

		}
		else if (request.getContentType().equalsIgnoreCase("application/json"))
		{

			File file = new File("src" + File.separator + "main" + File.separator + "resources" + File.separator
					+ "JSONResponse" + File.separator + "describeInstances.json");

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

		return "Invalid Content";
	}
}