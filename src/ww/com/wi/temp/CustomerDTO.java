package ww.com.wi.temp;

import java.util.Arrays;
import java.util.Date;

public class CustomerDTO {

    private String customerId;
    private String shortName;
    private String nic;
    private String mobile;
    private Date reqDate;
    private String fullName;
    private String address;
    private byte[] profilePicture;
    private String cgId;
    private String scId;
    private String createBy;
    private double openBlance;
    private String description;

    public CustomerDTO() {
    }

    public CustomerDTO(String customerId, String shortName, String nic, String mobile, Date reqDate, String fullName, String address, byte[] profilePicture, String cgId, String scId, String createBy, double openBlance, String description) {
        this.customerId = customerId;
        this.shortName = shortName;
        this.nic = nic;
        this.mobile = mobile;
        this.reqDate = reqDate;
        this.fullName = fullName;
        this.address = address;
        this.profilePicture = profilePicture;
        this.cgId = cgId;
        this.scId = scId;
        this.setCreateBy(createBy);
        this.setOpenBlance(openBlance);
        this.setDescription(description);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getReqDate() {
        return reqDate;
    }

    public void setReqDate(Date reqDate) {
        this.reqDate = reqDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getCgId() {
        return cgId;
    }

    public void setCgId(String cgId) {
        this.cgId = cgId;
    }

    public String getScId() {
        return scId;
    }

    public void setScId(String scId) {
        this.scId = scId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public double getOpenBlance() {
        return openBlance;
    }

    public void setOpenBlance(double openBlance) {
        this.openBlance = openBlance;
    }




    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerId='" + customerId + '\'' +
                ", shortName='" + shortName + '\'' +
                ", nic='" + nic + '\'' +
                ", mobile='" + mobile + '\'' +
                ", reqDate=" + reqDate +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", profilePicture=" + Arrays.toString(profilePicture) +
                ", cgId='" + cgId + '\'' +
                ", scId='" + scId + '\'' +
                ", createBy='" + getCreateBy() + '\'' +
                ", openBlance=" + getOpenBlance() +
                ", description='" + getDescription() + '\'' +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
