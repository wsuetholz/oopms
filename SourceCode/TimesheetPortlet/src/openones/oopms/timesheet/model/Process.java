package openones.oopms.timesheet.model;
// Generated Jul 9, 2012 10:09:15 PM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;

/**
 * Process generated by hbm2java
 */
public class Process  implements java.io.Serializable {


     private String code;
     private String name;
     private BigDecimal processId;
     private Byte disabled;
     private String appName;

    public Process() {
    }

	
    public Process(String code, String name) {
        this.code = code;
        this.name = name;
    }
    public Process(String code, String name, BigDecimal processId, Byte disabled, String appName) {
       this.code = code;
       this.name = name;
       this.processId = processId;
       this.disabled = disabled;
       this.appName = appName;
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
    public BigDecimal getProcessId() {
        return this.processId;
    }
    
    public void setProcessId(BigDecimal processId) {
        this.processId = processId;
    }
    public Byte getDisabled() {
        return this.disabled;
    }
    
    public void setDisabled(Byte disabled) {
        this.disabled = disabled;
    }
    public String getAppName() {
        return this.appName;
    }
    
    public void setAppName(String appName) {
        this.appName = appName;
    }




}


