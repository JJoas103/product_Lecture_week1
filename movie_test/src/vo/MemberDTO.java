package vo;

public class MemberDTO {
    int memberIdx;
    String memberId;
    String memberPass;
    String memberName;


    public MemberDTO() {
    }

    public MemberDTO(String memberId, String memberPass, String memberName) {
        this.memberId = memberId;
        this.memberPass = memberPass;
        this.memberName = memberName;
    }
    

    public int getMemberIdx() {
        return this.memberIdx;
    }

    public void setMemberIdx(int memberIdx) {
        this.memberIdx = memberIdx;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPass() {
        return this.memberPass;
    }

    public void setMemberPass(String memberPass) {
        this.memberPass = memberPass;
    }

    public String getMemberName() {
        return this.memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
