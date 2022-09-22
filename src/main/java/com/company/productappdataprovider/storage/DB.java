
package com.company.productappdataprovider.storage;

import one.microstream.enterprise.afs.aws.s3.types.S3Connector;
import one.microstream.enterprise.afs.blobstore.types.BlobStoreFileSystem;
import one.microstream.storage.embedded.types.EmbeddedStorage;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;
import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;


public final class DB
{
	public static EmbeddedStorageManager	storageManager;
	public final static DataRoot			root	= new DataRoot();
	
	static
	{
		System.setProperty("aws.accessKeyId", "");
		System.setProperty("aws.secretAccessKey", "");
		
		SystemPropertyCredentialsProvider credentialsProvider = SystemPropertyCredentialsProvider.create();
		
		S3Client s3 = S3Client.builder().region(Region.EU_CENTRAL_1).credentialsProvider(credentialsProvider).build();
		
		BlobStoreFileSystem fileSystem = BlobStoreFileSystem.New(
			S3Connector.Caching(s3));
		
		String S3bucketname = "jcon22-workshop-bucket";
		storageManager = EmbeddedStorage.start(root, fileSystem.ensureDirectoryPath(S3bucketname));			
	}
	
}
