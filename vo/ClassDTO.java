package vo;

public class ClassDTO {
    
    int class_idx;
	String class_name;
	int class_time;
	int class_count;
    int prof_idx;

    
    
    public ClassDTO() {
    }
    
    public ClassDTO(String class_name, int class_time, int class_count, int prof_idx) {
        this.class_name = class_name;
        this.class_time = class_time;
        this.class_count = class_count;
        this.prof_idx = prof_idx;
    }
    
    public int getClass_idx() {
        return this.class_idx;
    }
    
    public void setClass_idx(int class_idx) {
        this.class_idx = class_idx;
    }
    
    public String getClass_name() {
        return this.class_name;
    }
    
    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }
    
    public int getClass_time() {
        return this.class_time;
    }
    
    public void setClass_time(int class_time) {
        this.class_time = class_time;
    }
    
    public int getClass_count() {
        return this.class_count;
    }
    
    public void setClass_count(int class_count) {
        this.class_count = class_count;
    }
    public int getProf_idx() {
        return this.prof_idx;
    }
    
    public void setProf_idx(int prof_idx) {
        this.prof_idx = prof_idx;
    }

    @Override
    public String toString() {
        return "{" +
        " class_idx='" + getClass_idx() + "'" +
        ", class_name='" + getClass_name() + "'" +
        ", class_time='" + getClass_time() + "'" +
        ", class_count='" + getClass_count() + "'" +
        "}";
    }
    
}
