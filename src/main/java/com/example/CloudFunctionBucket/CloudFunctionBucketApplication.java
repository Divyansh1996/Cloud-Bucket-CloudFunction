package com.example.CloudFunctionBucket;


import com.google.cloud.functions.CloudEventsFunction;
import com.google.events.cloud.storage.v1.StorageObjectData;
import com.google.protobuf.util.JsonFormat;
import io.cloudevents.CloudEvent;

import java.util.logging.Logger;

public class CloudFunctionBucketApplication implements CloudEventsFunction {

	private final Logger logger = Logger.getLogger(CloudFunctionBucketApplication.class.getName());
	@Override
	public void accept(CloudEvent cloudEvent) throws Exception {
		String str = new String(cloudEvent.getData().toBytes());
		logger.info("Cloud String "+str);

		StorageObjectData.Builder builder = StorageObjectData.newBuilder();
		JsonFormat.parser().merge(str, builder);

		StorageObjectData data = builder.build();

		logger.info("Bucket Name: "+data.getBucket());
		logger.info("File Name: "+data.getName());
		logger.info("Date: "+data.getStorageClass());
	}
}
