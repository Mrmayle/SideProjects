public class Email {
    private String firstname;
    private String lastname;
    private String password;
    private String department;
    private String alternateEmail;
    private int mailboxCapacity;

    public Email(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Email(String firstname, String lastname, String password, String department, String alternateEmail, int mailboxCapacity) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.department = department;
        this.alternateEmail = alternateEmail;
        this.mailboxCapacity = mailboxCapacity;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAlternateEmail() {
        return alternateEmail;
    }

    public void setAlternateEmail(String alternateEmail) {
        this.alternateEmail = alternateEmail;
    }

    public int getMailboxCapacity() {
        return mailboxCapacity;
    }

    public void setMailboxCapacity(int mailboxCapacity) {
        this.mailboxCapacity = mailboxCapacity;
    }
}
