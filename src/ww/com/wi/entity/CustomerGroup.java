package ww.com.wi.entity;

import javax.persistence.*;

@Entity
public class CustomerGroup implements SuperEntity {

    @Id
    private String id;
    @Column(nullable = false, length = 50)
    private
    String groupName;
    @Column(nullable = false, length = 50)
    private
    String createBy;

    public CustomerGroup() {
    }

    public CustomerGroup(String id, String groupName, String createBy) {
        this.setId(id);
        this.setGroupName(groupName);
        this.setCreateBy(createBy);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public String toString() {
        return "CustomerGroup{" +
                "id='" + id + '\'' +
                ", groupName='" + groupName + '\'' +
                ", createBy='" + createBy + '\'' +
                '}';
    }
}
