package vo;

public class ProfDTO {
    int prof_idx;
	String prof_name;
	String prof_id;
	String prof_pass;
    

    public ProfDTO() {
    }

    public ProfDTO(String prof_id, String prof_pass, String prof_name) {
        this.prof_id = prof_id;
        this.prof_pass = prof_pass;
        this.prof_name = prof_name;
    }

    public int getProf_idx() {
        return this.prof_idx;
    }

    public void setProf_idx(int prof_idx) {
        this.prof_idx = prof_idx;
    }

    public String getProf_name() {
        return this.prof_name;
    }

    public void setProf_name(String prof_name) {
        this.prof_name = prof_name;
    }

    public String getProf_id() {
        return this.prof_id;
    }

    public void setProf_id(String prof_id) {
        this.prof_id = prof_id;
    }

    public String getProf_pass() {
        return this.prof_pass;
    }

    public void setProf_pass(String prof_pass) {
        this.prof_pass = prof_pass;
    }
    
}
