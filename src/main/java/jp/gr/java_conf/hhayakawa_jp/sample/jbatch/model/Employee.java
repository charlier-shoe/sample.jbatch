/**
 *
 */
package jp.gr.java_conf.hhayakawa_jp.sample.jbatch.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author hiroshi.hayakawa@oracle.com
 *
 */
@Entity
@Table(name="EMPLOYEES")
@NamedQueries({
    @NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e"),
    @NamedQuery(name="Employee.deleteAll", query="DELETE FROM Employee e"),
})
public class Employee {

    private long employee_Id;
    private String first_Name;
    private String last_Name;
    private String email;

    public Employee() {
    }

    public Employee(
            long employeeId, String firstName, String lastName, String email) {
        this.employee_Id = employeeId;
        this.first_Name = firstName;
        this.last_Name = lastName;
        this.email = email;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Employee [employee_Id=" + employee_Id + ", first_Name=" + first_Name + ", last_Name=" + last_Name
                + ", email=" + email + "]";
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(long employeeId) {
        this.employee_Id = employeeId;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.first_Name = firstName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.last_Name = lastName;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the employeeId
     */
    @Id
    public long getEmployeeId() {
        return employee_Id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return first_Name;
    }

    /**
     * @return the lastName
     */

    public String getLastName() {
        return last_Name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
 }
