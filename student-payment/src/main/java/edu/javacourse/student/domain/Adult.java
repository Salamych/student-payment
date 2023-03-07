package edu.javacourse.student.domain;

import java.time.LocalDate;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Adult extends Person{
    private String passportSeria;
    private String passportNumber;
    private LocalDate issueDate;

    public String getPassportSeria() {
        return passportSeria;
    }

    public void setPassportSeria(String passportSeria) {
        this.passportSeria = passportSeria;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
    
}
