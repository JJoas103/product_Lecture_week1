package vo;

public class CustomerDTO {
    int custid;
    String custname;
    String address;
    String phone;

    public CustomerDTO(){}

    public CustomerDTO(String custname, String address, String phone) {
        this.custname = custname;
        this.address = address;
        this.phone = phone;
    }

    public int getCustid() {
        return this.custid;
    }

    public void setCustid(int custid) {
        this.custid = custid;
    }

    public String getCustname() {
        return this.custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "{" +
            " custid='" + getCustid() + "'" +
            ", custname='" + getCustname() + "'" +
            ", address='" + getAddress() + "'" +
            ", phone='" + getPhone() + "'" +
            "}";
    }

}
