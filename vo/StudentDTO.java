package vo;

public class StudentDTO {
    int std_idx;
	String std_name;
	String std_id;
	String std_pass;

    public StudentDTO() {
    }

    public StudentDTO(String std_id, String std_pass, String std_name) {
        
        this.std_id = std_id;
        this.std_pass = std_pass;
        this.std_name = std_name;
    }

    public int getStd_idx() {
        return this.std_idx;
    }

    public void setStd_idx(int std_idx) {
        this.std_idx = std_idx;
    }

    public String getStd_name() {
        return this.std_name;
    }

    public void setStd_name(String std_name) {
        this.std_name = std_name;
    }

    public String getStd_id() {
        return this.std_id;
    }

    public void setStd_id(String std_id) {
        this.std_id = std_id;
    }

    public String getStd_pass() {
        return this.std_pass;
    }

    public void setStd_pass(String std_pass) {
        this.std_pass = std_pass;
    }

}
