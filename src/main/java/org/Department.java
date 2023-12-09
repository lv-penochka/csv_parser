package org;

import java.util.UUID;
/**
 * The Department class represents a department with a unique ID and name.
 */
public class Department{
    private  String departmentName;
    private  UUID departmentID;
    /**
     * Constructs a new Department object with the given parameters.
     *
     * @param departmentName   the name of the department
     * @param departmentID     the unique ID of the department
     */
    public Department(String departmentName, UUID departmentID) {
        this.departmentName = departmentName;
        this.departmentID = departmentID;
    }
    /**
     * Get department`s name.
     *
     * @return the name of the department
     */
    public String getDepartmentName() {
        return departmentName;
    }
    /**
     * Get department`s unique ID.
     * @return the unique ID of the department
     */
    public UUID getDepartmentID() {
        return departmentID;
    }
    /**
     * Returns a string representation of the Department object.
     * @return a string representation of the Department object
     */
    @Override
    public String toString() {
        return
                "departmentName=" + departmentName + '\'' +
                ", departmentID=" + departmentID
                ;
    }
}
