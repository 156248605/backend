package com.elex.oa.entity.project;

public class WeeklyPlanDetail {  //项目周报详细内容
    private int id;
    private String issues; //计划事项
    private String involving; //涉及人员
    private String workHours; //预计工时
    private String deliverResult; //交付成果
    private String note; //备注
    private int relatedId; //相关id

    public WeeklyPlanDetail() {
    }

    public WeeklyPlanDetail(int id, String issues, String involving, String workHours, String deliverResult, String note, int relatedId) {
        this.id = id;
        this.issues = issues;
        this.involving = involving;
        this.workHours = workHours;
        this.deliverResult = deliverResult;
        this.note = note;
        this.relatedId = relatedId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIssues() {
        return issues;
    }

    public void setIssues(String issues) {
        this.issues = issues;
    }

    public String getInvolving() {
        return involving;
    }

    public void setInvolving(String involving) {
        this.involving = involving;
    }

    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }

    public String getDeliverResult() {
        return deliverResult;
    }

    public void setDeliverResult(String deliverResult) {
        this.deliverResult = deliverResult;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(int relatedId) {
        this.relatedId = relatedId;
    }

    @Override
    public String toString() {
        return "WeeklyPlanDetail{" +
                "id=" + id +
                ", issues='" + issues + '\'' +
                ", involving='" + involving + '\'' +
                ", workHours='" + workHours + '\'' +
                ", deliverResult='" + deliverResult + '\'' +
                ", note='" + note + '\'' +
                ", relatedId=" + relatedId +
                '}';
    }
}
