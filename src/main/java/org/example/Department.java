package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@EqualsAndHashCode
public class Department {

    private String departmentId;
    private String departmentName;
    private static int nextId = 1;

    public Department(String departmentName) {
        if(validateDepartmentName(departmentName)) {
            this.departmentName = departmentName;
            this.departmentId = "D" + String.format("0%d", nextId++);
        } else {
            this.departmentName = null;
            this.departmentId = null;
        }
    }

    /**
     * checks if the department name is valid
     * @param departmentName
     * @return return false if it contains numbers and true if it contains only space and letters
     */
    public static boolean validateDepartmentName(String departmentName) {
        if (departmentName == null) {
            return false;
        }

        for (int i = 0; i < departmentName.length(); i++) {
            char c = departmentName.charAt(i);
            if (!(('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z') || c == ' ')) {
                return false;
            }
        }
        return true;
    }
}
