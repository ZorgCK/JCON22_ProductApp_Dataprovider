
package com.company.productappdataprovider.storage;


import one.microstream.storage.embedded.configuration.types.EmbeddedStorageConfiguration;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;

public final class DB
{
	public static DataRoot               root           = new DataRoot();
	public static EmbeddedStorageManager storageManager = EmbeddedStorageConfiguration.Builder()
		.setStorageDirectoryInUserHome("microstream-data/ProductAppDataprovider")
		.setChannelCount(4)
		.createEmbeddedStorageFoundation()
		.createEmbeddedStorageManager(DB.root)
		.start();
	
}
