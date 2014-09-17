package com.cloudmock.api.aws.ec2;

import javax.validation.constraints.NotNull;

public class CommonRequestParams
{

	@NotNull
	String signature;
	@NotNull
	String version;
	@NotNull
	String aWSAccessKeyId;
	@NotNull
	String signatureMethod;
	@NotNull
	Integer signatureVersion;
	@NotNull
	String timestamp;
	@NotNull
	String action;

	public String getSignature()
	{
		return signature;
	}

	public void setSignature(String signature)
	{
		this.signature = signature;
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public String getSignatureMethod()
	{
		return signatureMethod;
	}

	public String getaWSAccessKeyId()
	{
		return aWSAccessKeyId;
	}

	public void setaWSAccessKeyId(String aWSAccessKeyId)
	{
		this.aWSAccessKeyId = aWSAccessKeyId;
	}

	public void setSignatureMethod(String signatureMethod)
	{
		this.signatureMethod = signatureMethod;
	}

	public String getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(String timestamp)
	{
		this.timestamp = timestamp;
	}

	public Integer getSignatureVersion()
	{
		return signatureVersion;
	}

	public void setSignatureVersion(Integer signatureVersion)
	{
		this.signatureVersion = signatureVersion;
	}

	public String getAction()
	{
		return action;
	}

	public void setAction(String action)
	{
		this.action = action;
	}

	@Override
	public String toString()
	{
		return "RequestParams [signature=" + signature + ", version=" + version + ", aWSAccessKeyId=" + aWSAccessKeyId
				+ ", signatureMethod=" + signatureMethod + ", signatureVersion=" + signatureVersion + ", timestamp="
				+ timestamp + ", action=" + action + "]";
	}
}
