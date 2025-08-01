import java.util.Scanner;

public class Email {
    private String firstname;
    private String lastname;
    private String password;
    private String department;
    private String email;
    private String alternateEmail;
    private int mailboxCapacity = 500;
    private int defaultPasswordLength = 8;
    private String companySuffix = "mvmindustries";

    public Email(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        System.out.println("Email created: " + this.firstname + " " + this.lastname);
        // setting department
        this.department = setEmailDepartment();
        System.out.println("Department: " + this.department);
        // set random password
        this.password = randomPassword(defaultPasswordLength);
        System.out.println("Your temporary password is: " + this.password);
        //create email
        email = firstname.toLowerCase() + "." + lastname.toLowerCase() + "@" + department + "." + companySuffix + ".com";
        System.out.println("Your new company email address is: " + email);
    }

    public Email(String firstname, String lastname, String password, String department, String alternateEmail, int mailboxCapacity) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.department = department;
        this.alternateEmail = alternateEmail;
        this.mailboxCapacity = mailboxCapacity;
    }

    private String setEmailDepartment(){
        System.out.println("Select Department Code\n1 Sales\n2 Development\n3 Accounting\n4 HR\n5 DevOps\n0 for None \nEnter Selection:");
        Scanner in = new Scanner(System.in);
        int depChoice = in.nextInt();

        if (depChoice == 1){
            return "sales";
        } else if (depChoice == 2) {
            return "development";
        } else if (depChoice == 3) {
            return "accounting";
        } else if (depChoice == 4) {
            return "hr";
        } else if (depChoice == 5) {
            return "devops";
        } else
            return "";

    }

    private String randomPassword(int length) {
        String passwordSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$";
        char[] password = new char[length];
        for(int i=0; i<length; i++) {
            int rando = (int) (Math.random() * passwordSet.length());
            password[i] = passwordSet.charAt(rando);
        }
        return new String(password);
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
