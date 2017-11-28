package xyz.doran.elian.labplan.junit;

import static org.junit.Assert.*;
import org.junit.*;
import junit.framework.Assert;

import java.io.File;

import xyz.doran.elian.labplan.persistence.DatabaseConfiguration;
import xyz.doran.elian.labplan.persistence.DatabaseConfigurationSerializer;

public class DatabaseConfigurationSerializerTests {
	DatabaseConfiguration referenceConfig;
	String temporaryFilePath;
	File temporaryFile;
	
	@BeforeClass
	public void setUp() throws Exception {
		referenceConfig = new DatabaseConfiguration();
		referenceConfig.setConnectionURL("jdbc:mysql://localhost:3306/testdb");
		referenceConfig.setUserName("root");
		referenceConfig.setPassword("Development.");
		
		temporaryFile = File.createTempFile("labplan-serialization-", ".xml");
		temporaryFilePath = temporaryFile.getAbsolutePath();
	}

	@AfterClass
	public void tearDown() throws Exception {
	}

	@Test
	public void testSerialization() throws Exception {
		DatabaseConfigurationSerializer.serializeToXML(referenceConfig, temporaryFilePath);
	}

	@Test
	public void testDeserialization() throws Exception {
		DatabaseConfiguration config;
		config = DatabaseConfigurationSerializer.deserializeFromXML(temporaryFilePath);
		
		Assert.assertEquals(referenceConfig.getConnectionURL(), config.getConnectionURL());
		Assert.assertEquals(referenceConfig.getUserName(), config.getUserName());
		Assert.assertEquals(referenceConfig.getPassword(), config.getPassword());
	}
}
