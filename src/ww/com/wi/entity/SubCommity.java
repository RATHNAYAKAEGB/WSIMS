package ww.com.wi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SubCommity implements SuperEntity{
    @Id
    private String subCId;
    @Column(nullable = false, length = 50)
    private String sname;
    @Column(nullable = false, length = 50)
    private String createBy;

    public SubCommity() {
    }

    public SubCommity(String subCId, String sname, String createBy) {
        this.setSubCId(subCId);
        this.setSname(sname);
        this.setCreateBy(createBy);
    }


    public String getSubCId() {
        return subCId;
    }

    public void setSubCId(String subCId) {
        this.subCId = subCId;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public String toString() {
        return "SubCommity{" +
                "subCId='" + subCId + '\'' +
                ", sname='" + sname + '\'' +
                ", createBy='" + createBy + '\'' +
                '}';
    }
}
