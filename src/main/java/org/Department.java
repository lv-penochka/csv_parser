package org;

import java.util.UUID;

public class Department extends Departments{
    private  String departmentName;
    private  UUID departmentID;
    public Department(String departmentName, UUID departmentID) {
        super(departmentName);
        this.departmentName = departmentName;
        this.departmentID = departmentID;
    }
    public Department(String departmentName) {
        super(departmentName);
    }
}
