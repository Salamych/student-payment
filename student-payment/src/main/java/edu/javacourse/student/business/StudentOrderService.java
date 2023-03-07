package edu.javacourse.student.business;

import edu.javacourse.student.dao.StreetRepository;
import edu.javacourse.student.dao.StudentOrderRepository;
import edu.javacourse.student.domain.Address;
import edu.javacourse.student.domain.Adult;
import edu.javacourse.student.domain.Person;
import edu.javacourse.student.domain.Street;
import edu.javacourse.student.domain.StudentOrder;
import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentOrderService {
    
    private final static Logger LOG = LoggerFactory.getLogger(StudentOrderService.class);
    
    @Autowired
    private StudentOrderRepository dao;
    @Autowired
    private StreetRepository daoStreet;
    
    @Transactional
    public void testSave(){
    StudentOrder so = new StudentOrder();
    so.setHusband(buildPerson(false));
    so.setWife(buildPerson(true));
    dao.save(so);
    }
    @Transactional
    public void testGet(){
        List<StudentOrder> sos = dao.findAll();
        LOG.info(sos.get(0).getWife().getGivenName());
    }
    private Adult buildPerson(boolean wife){
        Adult p = new Adult();
        p.setDateOfBirth(LocalDate.now());
        
        Address a = new Address();
        a.setPostCode("190000");
        a.setBuilding("21");
        a.setExtension("B");
        a.setApartment("199");
        Street one = daoStreet.getReferenceById(1L);
        a.setStreet(one);
        p.setAddress(a);
        if(wife){
        p.setSurName("Рюрик");
        p.setGivenName("Марфа");
        p.setPatronymic("Васильевна");
        p.setPassportSeria("WIFE_S");
        p.setPassportNumber("WIFE_N");
        p.setIssueDate(LocalDate.now());
        }else{
        p.setSurName("Рюрик");
        p.setGivenName("Иван");
        p.setPatronymic("Васильевич");
        p.setPassportSeria("HUSBAND_S");
        p.setPassportNumber("HUSBAND_N");
        p.setIssueDate(LocalDate.now());
        }
        
        return p;
    }
}
