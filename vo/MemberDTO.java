package vo;

import main.Test2;

public class MemberDTO {
    
    int member_idx;
    String member_id;
    String member_pass;
    String member_address;
    String member_phone;
    int member_money;


    public MemberDTO(){}
    public MemberDTO(String member_id, String member_pass, String member_address, String member_phone, int member_money) {
        this.member_id = member_id;
        this.member_pass = member_pass;
        this.member_address = member_address;
        this.member_phone = member_phone;
        this.member_money = member_money;
    }

    public int getMember_idx() {
        return this.member_idx;
    }

    public void setMember_idx(int member_idx) {
        this.member_idx = member_idx;
    }

    public String getMember_id() {
        return this.member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_pass() {
        return this.member_pass;
    }

    public void setMember_pass(String member_pass) {
        this.member_pass = member_pass;
    }

    public String getMember_address() {
        return this.member_address;
    }

    public void setMember_address(String member_address) {
        this.member_address = member_address;
    }

    public String getMember_phone() {
        return this.member_phone;
    }

    public void setMember_phone(String member_phone) {
        this.member_phone = member_phone;
    }
    public void setMember_money(int member_money) {
        this.member_money = member_money;
    }
    public int getMember_money(){
        return this.member_money;
    }

    @Override
    public String toString() {
        return "{" +
            " member_idx='" + getMember_idx() + "'" +
            ", member_id='" + getMember_id() + "'" +
            ", member_pass='" + getMember_pass() + "'" +
            ", member_address='" + getMember_address() + "'" +
            ", member_phone='" + getMember_phone() + "'" +
            ", member_money='" + getMember_money() + "'" +
            "}";
    }


}
