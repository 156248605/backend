package com.elex.oa.entity.archive;

public class ArchiveDown {
    private int id;
    private String archiveName; //文档名称
    private String taddy; //上传人
    private String postTime; //上传时间

    public ArchiveDown() {
    }

    public ArchiveDown(int id, String archiveName, String taddy, String postTime) {
        this.id = id;
        this.archiveName = archiveName;
        this.taddy = taddy;
        this.postTime = postTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "ArchiveDown{" +
                "id=" + id +
                ", archiveName='" + archiveName + '\'' +
                ", taddy='" + taddy + '\'' +
                ", postTime='" + postTime + '\'' +
                '}';
    }
}
