package uni.system.model;

public class Department {
    private String departmentName;
    private String departmentCode;
    public Department(String departmentName, String departmentCode) {
        setDepartmentName(departmentName);
        setDepartmentCode(departmentCode);
    }
    public void setDepartmentName(String departmentName) {
        if(departmentName == null || departmentName.trim().isEmpty()){
            throw new IllegalArgumentException("Department name must not be blank.");
        }
        this.departmentName = departmentName;
    }
    public void setDepartmentCode(String departmentCode) {
        if(departmentCode == null || departmentCode.trim().isEmpty()){
            throw new IllegalArgumentException("Department code must not be blank.");
        }
        this.departmentCode = departmentCode;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public String getDepartmentCode() {
        return departmentCode;
    }
    @Override
    public String toString() {
        return  departmentName + "(" + departmentCode + ")";
    }


    
}
