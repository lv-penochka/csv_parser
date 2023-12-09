package org;

import java.util.UUID;

public class Department{
    private  String departmentName;
    private  UUID departmentID;
    public Department(String departmentName, UUID departmentID) {
        this.departmentName = departmentName;
        this.departmentID = departmentID;
    }

    @Override
    public String toString() {
        return
                "departmentName=" + departmentName + '\'' +
                ", departmentID=" + departmentID
                ;
    }
}
