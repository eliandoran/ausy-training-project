package xyz.doran.elian.labplan.persistence;

public class DatabaseConfiguration {
	private String connectionURL;
	private String userName;
	private String password;
	
	
	public DatabaseConfiguration() {
		
	}
	
	public DatabaseConfiguration(String connectionURL, String userName, String password) {
		this.connectionURL = connectionURL;
		this.userName = userName;
		this.password = password;
	}

	
	public String getConnectionURL() {
		return connectionURL;
	}

	public void setConnectionURL(String connectionURL) {
		this.connectionURL = connectionURL;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
