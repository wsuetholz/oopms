package openones.oopms.entity;
// Generated 12:18:17 01-03-2012 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.Date;

/**
 * Requirements generated by hbm2java
 */
public class Requirements  implements java.io.Serializable {


     private BigDecimal requirementId;
     private Project project;
     private BigDecimal prevProjectId;
     private BigDecimal moduleId;
     private BigDecimal type;
     private BigDecimal reqSize;
     private String srs;
     private String dd;
     private String testcase;
     private String releaseNote;
     private BigDecimal effort;
     private BigDecimal elapsedDay;
     private Date receivedDate;
     private Date responseDate;
     private Date createDate;
     private Date committedDate;
     private Date designedDate;
     private Date codedDate;
     private Date testedDate;
     private Date deployedDate;
     private Date acceptedDate;
     private Date cancelledDate;
     private String requirement;
     private String codeModule;

    public Requirements() {
    }

	
    public Requirements(BigDecimal requirementId, Project project) {
        this.requirementId = requirementId;
        this.project = project;
    }
    public Requirements(BigDecimal requirementId, Project project, BigDecimal prevProjectId, BigDecimal moduleId, BigDecimal type, BigDecimal reqSize, String srs, String dd, String testcase, String releaseNote, BigDecimal effort, BigDecimal elapsedDay, Date receivedDate, Date responseDate, Date createDate, Date committedDate, Date designedDate, Date codedDate, Date testedDate, Date deployedDate, Date acceptedDate, Date cancelledDate, String requirement, String codeModule) {
       this.requirementId = requirementId;
       this.project = project;
       this.prevProjectId = prevProjectId;
       this.moduleId = moduleId;
       this.type = type;
       this.reqSize = reqSize;
       this.srs = srs;
       this.dd = dd;
       this.testcase = testcase;
       this.releaseNote = releaseNote;
       this.effort = effort;
       this.elapsedDay = elapsedDay;
       this.receivedDate = receivedDate;
       this.responseDate = responseDate;
       this.createDate = createDate;
       this.committedDate = committedDate;
       this.designedDate = designedDate;
       this.codedDate = codedDate;
       this.testedDate = testedDate;
       this.deployedDate = deployedDate;
       this.acceptedDate = acceptedDate;
       this.cancelledDate = cancelledDate;
       this.requirement = requirement;
       this.codeModule = codeModule;
    }
   
    public BigDecimal getRequirementId() {
        return this.requirementId;
    }
    
    public void setRequirementId(BigDecimal requirementId) {
        this.requirementId = requirementId;
    }
    public Project getProject() {
        return this.project;
    }
    
    public void setProject(Project project) {
        this.project = project;
    }
    public BigDecimal getPrevProjectId() {
        return this.prevProjectId;
    }
    
    public void setPrevProjectId(BigDecimal prevProjectId) {
        this.prevProjectId = prevProjectId;
    }
    public BigDecimal getModuleId() {
        return this.moduleId;
    }
    
    public void setModuleId(BigDecimal moduleId) {
        this.moduleId = moduleId;
    }
    public BigDecimal getType() {
        return this.type;
    }
    
    public void setType(BigDecimal type) {
        this.type = type;
    }
    public BigDecimal getReqSize() {
        return this.reqSize;
    }
    
    public void setReqSize(BigDecimal reqSize) {
        this.reqSize = reqSize;
    }
    public String getSrs() {
        return this.srs;
    }
    
    public void setSrs(String srs) {
        this.srs = srs;
    }
    public String getDd() {
        return this.dd;
    }
    
    public void setDd(String dd) {
        this.dd = dd;
    }
    public String getTestcase() {
        return this.testcase;
    }
    
    public void setTestcase(String testcase) {
        this.testcase = testcase;
    }
    public String getReleaseNote() {
        return this.releaseNote;
    }
    
    public void setReleaseNote(String releaseNote) {
        this.releaseNote = releaseNote;
    }
    public BigDecimal getEffort() {
        return this.effort;
    }
    
    public void setEffort(BigDecimal effort) {
        this.effort = effort;
    }
    public BigDecimal getElapsedDay() {
        return this.elapsedDay;
    }
    
    public void setElapsedDay(BigDecimal elapsedDay) {
        this.elapsedDay = elapsedDay;
    }
    public Date getReceivedDate() {
        return this.receivedDate;
    }
    
    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }
    public Date getResponseDate() {
        return this.responseDate;
    }
    
    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }
    public Date getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Date getCommittedDate() {
        return this.committedDate;
    }
    
    public void setCommittedDate(Date committedDate) {
        this.committedDate = committedDate;
    }
    public Date getDesignedDate() {
        return this.designedDate;
    }
    
    public void setDesignedDate(Date designedDate) {
        this.designedDate = designedDate;
    }
    public Date getCodedDate() {
        return this.codedDate;
    }
    
    public void setCodedDate(Date codedDate) {
        this.codedDate = codedDate;
    }
    public Date getTestedDate() {
        return this.testedDate;
    }
    
    public void setTestedDate(Date testedDate) {
        this.testedDate = testedDate;
    }
    public Date getDeployedDate() {
        return this.deployedDate;
    }
    
    public void setDeployedDate(Date deployedDate) {
        this.deployedDate = deployedDate;
    }
    public Date getAcceptedDate() {
        return this.acceptedDate;
    }
    
    public void setAcceptedDate(Date acceptedDate) {
        this.acceptedDate = acceptedDate;
    }
    public Date getCancelledDate() {
        return this.cancelledDate;
    }
    
    public void setCancelledDate(Date cancelledDate) {
        this.cancelledDate = cancelledDate;
    }
    public String getRequirement() {
        return this.requirement;
    }
    
    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }
    public String getCodeModule() {
        return this.codeModule;
    }
    
    public void setCodeModule(String codeModule) {
        this.codeModule = codeModule;
    }




}

