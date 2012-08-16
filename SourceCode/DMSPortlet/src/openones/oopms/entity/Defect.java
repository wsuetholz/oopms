package openones.oopms.entity;
// Generated Jul 6, 2012 7:55:26 PM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.Date;

/**
 * Defect generated by hbm2java
 */
public class Defect  implements java.io.Serializable {


     private BigDecimal defectId;
     private BigDecimal projectId;
     private String createdBy;
     private Date createDate;
     private String updatedBy;
     private String title;
     private String assignedTo;
     private BigDecimal dsId;
     private BigDecimal wpId;
     private BigDecimal defsId;
     private BigDecimal moduleId;
     private BigDecimal atId;
     private BigDecimal dpId;
     private BigDecimal dtId;
     private BigDecimal qaId;
     private BigDecimal processId;
     private BigDecimal sdId;
     private BigDecimal siId;
     private Date dueDate;
     private Date closeDate;
     private String description;
     private String solution;
     private String projectOrigin;
     private String image;
     private String reference;
     private Date fixedDate;
     private String causeAnalysis;
     private String testCase;
     private String defectOwner;
     private String fixedDateString;
     private String dueDateString;
     private String status;
     private String severity;
     private String priority;
     private String assignedToString;
     private String createByString;
     
    public Defect() {
    }

	
    public Defect(BigDecimal defectId, BigDecimal projectId, String createdBy, Date createDate, String title, String description) {
        this.defectId = defectId;
        this.projectId = projectId;
        this.createdBy = createdBy;
        this.createDate = createDate;
        this.title = title;
        this.description = description;
    }
    public Defect(BigDecimal defectId, BigDecimal projectId, String createdBy, Date createDate, String updatedBy, String title, String assignedTo, BigDecimal dsId, BigDecimal wpId, BigDecimal defsId, BigDecimal moduleId, BigDecimal atId, BigDecimal dpId, BigDecimal dtId, BigDecimal qaId, BigDecimal processId, BigDecimal sdId, BigDecimal siId, Date dueDate, Date closeDate, String description, String solution, String projectOrigin, String image, String reference, Date fixedDate, String causeAnalysis, String testCase, String defectOwner) {
       this.defectId = defectId;
       this.projectId = projectId;
       this.createdBy = createdBy;
       this.createDate = createDate;
       this.updatedBy = updatedBy;
       this.title = title;
       this.assignedTo = assignedTo;
       this.dsId = dsId;
       this.wpId = wpId;
       this.defsId = defsId;
       this.moduleId = moduleId;
       this.atId = atId;
       this.dpId = dpId;
       this.dtId = dtId;
       this.qaId = qaId;
       this.processId = processId;
       this.sdId = sdId;
       this.siId = siId;
       this.dueDate = dueDate;
       this.closeDate = closeDate;
       this.description = description;
       this.solution = solution;
       this.projectOrigin = projectOrigin;
       this.image = image;
       this.reference = reference;
       this.fixedDate = fixedDate;
       this.causeAnalysis = causeAnalysis;
       this.testCase = testCase;
       this.defectOwner = defectOwner;
    }
   
    public BigDecimal getDefectId() {
        return this.defectId;
    }
    
    public void setDefectId(BigDecimal defectId) {
        this.defectId = defectId;
    }
    public BigDecimal getProjectId() {
        return this.projectId;
    }
    
    public void setProjectId(BigDecimal projectId) {
        this.projectId = projectId;
    }
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getUpdatedBy() {
        return this.updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAssignedTo() {
        return this.assignedTo;
    }
    
    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }
    public BigDecimal getDsId() {
        return this.dsId;
    }
    
    public void setDsId(BigDecimal dsId) {
        this.dsId = dsId;
    }
    public BigDecimal getWpId() {
        return this.wpId;
    }
    
    public void setWpId(BigDecimal wpId) {
        this.wpId = wpId;
    }
    public BigDecimal getDefsId() {
        return this.defsId;
    }
    
    public void setDefsId(BigDecimal defsId) {
        this.defsId = defsId;
    }
    public BigDecimal getModuleId() {
        return this.moduleId;
    }
    
    public void setModuleId(BigDecimal moduleId) {
        this.moduleId = moduleId;
    }
    public BigDecimal getAtId() {
        return this.atId;
    }
    
    public void setAtId(BigDecimal atId) {
        this.atId = atId;
    }
    public BigDecimal getDpId() {
        return this.dpId;
    }
    
    public void setDpId(BigDecimal dpId) {
        this.dpId = dpId;
    }
    public BigDecimal getDtId() {
        return this.dtId;
    }
    
    public void setDtId(BigDecimal dtId) {
        this.dtId = dtId;
    }
    public BigDecimal getQaId() {
        return this.qaId;
    }
    
    public void setQaId(BigDecimal qaId) {
        this.qaId = qaId;
    }
    public BigDecimal getProcessId() {
        return this.processId;
    }
    
    public void setProcessId(BigDecimal processId) {
        this.processId = processId;
    }
    public BigDecimal getSdId() {
        return this.sdId;
    }
    
    public void setSdId(BigDecimal sdId) {
        this.sdId = sdId;
    }
    public BigDecimal getSiId() {
        return this.siId;
    }
    
    public void setSiId(BigDecimal siId) {
        this.siId = siId;
    }
    public Date getDueDate() {
        return this.dueDate;
    }
    
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public Date getCloseDate() {
        return this.closeDate;
    }
    
    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getSolution() {
        return this.solution;
    }
    
    public void setSolution(String solution) {
        this.solution = solution;
    }
    public String getProjectOrigin() {
        return this.projectOrigin;
    }
    
    public void setProjectOrigin(String projectOrigin) {
        this.projectOrigin = projectOrigin;
    }
    public String getImage() {
        return this.image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    public String getReference() {
        return this.reference;
    }
    
    public void setReference(String reference) {
        this.reference = reference;
    }
    public Date getFixedDate() {
        return this.fixedDate;
    }
    
    public void setFixedDate(Date fixedDate) {
        this.fixedDate = fixedDate;
    }
    public String getCauseAnalysis() {
        return this.causeAnalysis;
    }
    
    public void setCauseAnalysis(String causeAnalysis) {
        this.causeAnalysis = causeAnalysis;
    }
    public String getTestCase() {
        return this.testCase;
    }
    
    public void setTestCase(String testCase) {
        this.testCase = testCase;
    }
    public String getDefectOwner() {
        return this.defectOwner;
    }
    
    public void setDefectOwner(String defectOwner) {
        this.defectOwner = defectOwner;
    }


    public String getFixedDateString() {
        return fixedDateString;
    }


    public void setFixedDateString(String fixedDateString) {
        this.fixedDateString = fixedDateString;
    }


    public String getDueDateString() {
        return dueDateString;
    }


    public void setDueDateString(String dueDateString) {
        this.dueDateString = dueDateString;
    }


    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }


    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * @return the severity
     */
    public String getSeverity() {
        return severity;
    }


    /**
     * @param severity the severity to set
     */
    public void setSeverity(String severity) {
        this.severity = severity;
    }


    /**
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }


    /**
     * @param priority the priority to set
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }


    /**
     * @return the assignedToString
     */
    public String getAssignedToString() {
        return assignedToString;
    }


    /**
     * @param assignedToString the assignedToString to set
     */
    public void setAssignedToString(String assignedToString) {
        this.assignedToString = assignedToString;
    }


    /**
     * @return the createByString
     */
    public String getCreateByString() {
        return createByString;
    }


    /**
     * @param createByString the createByString to set
     */
    public void setCreateByString(String createByString) {
        this.createByString = createByString;
    }

    


}


