package openones.oopms.entity;
// Generated 12:18:17 01-03-2012 by Hibernate Tools 3.2.1.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Defect generated by hbm2java
 */
public class Defect implements java.io.Serializable {

    /** . */
    private static final long serialVersionUID = 1L;
    private long defectId;
    private long projectId;
    private String createdBy;
    private Date createDate;
    private String updatedBy;
    private String title;
    private String assignedTo;
    private long dsId;
    private long wpId;
    private long defsId;
    private long moduleId;
    private long atId;
    private long dpId;
    private long dtId;
    private long qaId;
    private long processId;
    private long sdId;
    private long siId;
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
    private Set defectAttachments = new HashSet(0);

    public Defect() {
    }

    public Defect(long defectId, long projectId, String createdBy, Date createDate, String title, String description) {
        this.defectId = defectId;
        this.projectId = projectId;
        this.createdBy = createdBy;
        this.createDate = createDate;
        this.title = title;
        this.description = description;
    }
    public Defect(long defectId, long projectId, String createdBy, Date createDate, String updatedBy, String title,
            String assignedTo, long dsId, long wpId, long defsId, long moduleId, long atId, long dpId, long dtId,
            long qaId, long processId, long sdId, long siId, Date dueDate, Date closeDate, String description,
            String solution, String projectOrigin, String image, String reference, Date fixedDate,
            String causeAnalysis, String testCase, String defectOwner, Set defectAttachments) {
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
        this.defectAttachments = defectAttachments;
    }

    public long getDefectId() {
        return this.defectId;
    }

    public void setDefectId(long defectId) {
        this.defectId = defectId;
    }
    public long getProjectId() {
        return this.projectId;
    }

    public void setProjectId(long projectId) {
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
    public long getDsId() {
        return this.dsId;
    }

    public void setDsId(long dsId) {
        this.dsId = dsId;
    }
    public long getWpId() {
        return this.wpId;
    }

    public void setWpId(long wpId) {
        this.wpId = wpId;
    }
    public long getDefsId() {
        return this.defsId;
    }

    public void setDefsId(long defsId) {
        this.defsId = defsId;
    }
    public long getModuleId() {
        return this.moduleId;
    }

    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }
    public long getAtId() {
        return this.atId;
    }

    public void setAtId(long atId) {
        this.atId = atId;
    }
    public long getDpId() {
        return this.dpId;
    }

    public void setDpId(long dpId) {
        this.dpId = dpId;
    }
    public long getDtId() {
        return this.dtId;
    }

    public void setDtId(long dtId) {
        this.dtId = dtId;
    }
    public long getQaId() {
        return this.qaId;
    }

    public void setQaId(long qaId) {
        this.qaId = qaId;
    }
    public long getProcessId() {
        return this.processId;
    }

    public void setProcessId(long processId) {
        this.processId = processId;
    }
    public long getSdId() {
        return this.sdId;
    }

    public void setSdId(long sdId) {
        this.sdId = sdId;
    }
    public long getSiId() {
        return this.siId;
    }

    public void setSiId(long siId) {
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
    public Set getDefectAttachments() {
        return this.defectAttachments;
    }

    public void setDefectAttachments(Set defectAttachments) {
        this.defectAttachments = defectAttachments;
    }

}