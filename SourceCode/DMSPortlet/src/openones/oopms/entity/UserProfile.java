package openones.oopms.entity;
// Generated 12:18:17 01-03-2012 by Hibernate Tools 3.2.1.GA



/**
 * UserProfile generated by hbm2java
 */
public class UserProfile  implements java.io.Serializable {


     private String userId;
     private String name;
     private String groupName;
     private String designation;
     private String status;
     private String password;

    public UserProfile() {
    }

	
    public UserProfile(String userId, String name, String groupName) {
        this.userId = userId;
        this.name = name;
        this.groupName = groupName;
    }
    public UserProfile(String userId, String name, String groupName, String designation, String status, String password) {
       this.userId = userId;
       this.name = name;
       this.groupName = groupName;
       this.designation = designation;
       this.status = status;
       this.password = password;
    }
   
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getGroupName() {
        return this.groupName;
    }
    
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public String getDesignation() {
        return this.designation;
    }
    
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }




}


