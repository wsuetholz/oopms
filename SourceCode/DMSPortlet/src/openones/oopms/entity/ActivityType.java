package openones.oopms.entity;
// Generated 12:18:17 01-03-2012 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * ActivityType generated by hbm2java
 */
public class ActivityType  implements java.io.Serializable {


     private String code;
     private String name;
     private BigDecimal atId;
     private Byte disabled;
     private Set qcActivities = new HashSet(0);

    public ActivityType() {
    }

	
    public ActivityType(String code, String name) {
        this.code = code;
        this.name = name;
    }
    public ActivityType(String code, String name, BigDecimal atId, Byte disabled, Set qcActivities) {
       this.code = code;
       this.name = name;
       this.atId = atId;
       this.disabled = disabled;
       this.qcActivities = qcActivities;
    }
   
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getAtId() {
        return this.atId;
    }
    
    public void setAtId(BigDecimal atId) {
        this.atId = atId;
    }
    public Byte getDisabled() {
        return this.disabled;
    }
    
    public void setDisabled(Byte disabled) {
        this.disabled = disabled;
    }
    public Set getQcActivities() {
        return this.qcActivities;
    }
    
    public void setQcActivities(Set qcActivities) {
        this.qcActivities = qcActivities;
    }




}


