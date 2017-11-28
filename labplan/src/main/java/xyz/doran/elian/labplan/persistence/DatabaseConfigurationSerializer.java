package xyz.doran.elian.labplan.persistence;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class DatabaseConfigurationSerializer {
	public static void serializeToXML(DatabaseConfiguration config, String path)
			throws FileNotFoundException, IOException {
		FileOutputStream out = new FileOutputStream(path);
		
		XMLEncoder encoder = new XMLEncoder(out);
		encoder.writeObject(config);
		encoder.close();
		
		out.close();
	}
	
	public static DatabaseConfiguration deserializeFromXML(String path)
		throws FileNotFoundException, IOException {
		FileInputStream in = new FileInputStream(path);
		
		XMLDecoder decoder = new XMLDecoder(in);
		DatabaseConfiguration config = (DatabaseConfiguration)decoder.readObject();
		decoder.close();
		
		in.close();
		
		return config;
	}
}
