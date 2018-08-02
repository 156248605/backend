package com.elex.oa.entity.project;

public class MileStone {
    private int id;
    private String projectCode;
    private String projectManager;
    private String departmentManager;
    private String leaderShip;
    private String generalManager;

    public MileStone() {
    }

    public MileStone(int id, String projectCode, String projectManager, String departmentManager, String leaderShip, String generalManager) {
        this.id = id;
        this.projectCode = projectCode;
        this.projectManager = projectManager;
        this.departmentManager = departmentManager;
        this.leaderShip = leaderShip;
        this.generalManager = generalManager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getDepartmentManager() {
        return departmentManager;
    }

    public void setDepartmentManager(String departmentManager) {
        this.departmentManager = departmentManager;
    }

    public String getLeaderShip() {
        return leaderShip;
    }

    public void setLeaderShip(String leaderShip) {
        this.leaderShip = leaderShip;
    }

    public String getGeneralManager() {
        return generalManager;
    }

    public void setGeneralManager(String generalManager) {
        this.generalManager = generalManager;
    }

    @Override
    public String toString() {
        return "MileStone{" +
                "id=" + id +
                ", projectCode='" + projectCode + '\'' +
                ", projectManager='" + projectManager + '\'' +
                ", departmentManager='" + departmentManager + '\'' +
                ", leaderShip='" + leaderShip + '\'' +
                ", generalManager='" + generalManager + '\'' +
                '}';
    }
}
