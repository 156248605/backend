package com.elex.oa.entity.archive;

public class ArchiveDownQuery { //文档信息查询时所用
    private String archiveName; //文档名称
    private String taddy; //上传人
    private String postTime; //上传时间
    private String postTimeA;
    private String postTimeB;


    public ArchiveDownQuery() {
    }

    public ArchiveDownQuery(String archiveName, String taddy, String postTime, String postTimeA, String postTimeB) {
        this.archiveName = archiveName;
        this.taddy = taddy;
        this.postTime = postTime;
        this.postTimeA = postTimeA;
        this.postTimeB = postTimeB;
    }

    public String getArchiveName() {
        return archiveName;
    }

    public void setArchiveName(String archiveName) {
        this.archiveName = archiveName;
    }

    public String getTaddy() {
        return taddy;
    }

    public void setTaddy(String taddy) {
        this.taddy = taddy;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getPostTimeA() {
        return postTimeA;
    }

    public void setPostTimeA(String postTimeA) {
        this.postTimeA = postTimeA;
    }

    public String getPostTimeB() {
        return postTimeB;
    }

    public void setPostTimeB(String postTimeB) {
        this.postTimeB = postTimeB;
    }

    @Override
    public String toString() {
        return "ArchiveDownQuery{" +
                "archiveName='" + archiveName + '\'' +
                ", taddy='" + taddy + '\'' +
                ", postTime='" + postTime + '\'' +
                ", postTimeA='" + postTimeA + '\'' +
                ", postTimeB='" + postTimeB + '\'' +
                '}';
    }
}
