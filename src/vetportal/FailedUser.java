/**
 * File: FailedUser.java
 * Date: April 16, 2020
 * @Author: Rebekah Qu 
 * Main POC: Rebekah Qu 
 * Purpose: A class for users with failed login attempts 
 *
 */
package vetportal;

public class FailedUser {
    private String username;
    private Integer loginAttempts = 0;
    private long lockoutTime = 0;
    
    // Constructor
    public FailedUser(String username, Integer loginAttempts, long lockoutTime) {
        this.username = username;
        this.loginAttempts = loginAttempts;
        this.lockoutTime = lockoutTime;       
    }    
    
    // Setter methods    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setLoginAttempts(Integer loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    public void setLockoutTime(long lockoutTime) {
        this.lockoutTime = lockoutTime;
    }
    
    
    // Getter methods     
    public String getUsername() {
        return this.username;
    }
    
    public Integer getLoginAttempts() {
        return this.loginAttempts;
    }
    
    public long getLockoutTime() {
        return this.lockoutTime;
    } 
    
}
