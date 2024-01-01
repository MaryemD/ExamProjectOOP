
public class User {
	private int userId;
	private String userName, password, email;
	private boolean isLoggedIn;
	private static int lastUserId = 0;
	
	public User(int userId, String userName, String password, String email) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		lastUserId++;
	}
	
	public static int getLastUserId() {
		return lastUserId;
	}
	
	public int getUserId() {
		return this.userId;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void setUserName(String userName) {
        this.userName = userName;
    }
	
	public void setEmail(String email) {
        this.email = email;
    }
	
	// authentification
	
	public boolean LogIn(String userName, String password) {
		this.isLoggedIn = this.userName.equals(userName) && this.password.equals(password);
		if (this.isLoggedIn) {
			//System.out.println("Logged In Successfully!");
		}else {
			System.out.println("Login failed. Invalid credentials.");
		}
		return this.isLoggedIn;
	}
	
	public void LogOut() {
		this.isLoggedIn = false;
	}
	
}
