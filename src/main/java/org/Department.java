package org;
import java.util.UUID;

public class Department {
    private final String departmentName;
    private  final UUID departmentID;

    public Department(String departmentName) {
        UUID uniqueKey = UUID.randomUUID();
        this.departmentName = departmentName;
        this.departmentID = uniqueKey;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public UUID getDepartmentID() {
        return departmentID;
    }
    @Override
    public String toString() {
        return "Department{" +
                "departmentName='" + departmentName + '\'' +
                ", departmentID=" + departmentID +
                '}';
    }
}
