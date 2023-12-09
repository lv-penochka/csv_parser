package org;
import java.util.*;
import java.util.stream.Collectors;

public class Departments {
    Map<String, UUID> departmentPair;

    public Departments(String departmentName) {
        departmentPair = new HashMap<>();
        if (!departmentPair.containsKey(departmentName)){
            UUID uniqueKey = UUID.randomUUID();
            departmentPair.put(departmentName,uniqueKey);
        }
        else {
        };
    }

    public static Departments getDepartment(String departmentName){
        return  new Departments(departmentName);
    }

//    @Override
//    public String toString() {
//        return "Department{" +
//                "departmentName='" + departmentName + '\'' +
//                ", departmentID=" + departmentID +
//                '}';
//    }
}
