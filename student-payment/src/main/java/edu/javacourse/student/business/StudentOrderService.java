package edu.javacourse.student.business;

import edu.javacourse.student.dao.PassportOfficeRepository;
import edu.javacourse.student.dao.RegisterOfficeRepository;
import edu.javacourse.student.dao.StreetRepository;
import edu.javacourse.student.dao.StudentOrderChildRepository;
import edu.javacourse.student.dao.StudentOrderRepository;
import edu.javacourse.student.dao.StudentOrderStatusRepository;
import edu.javacourse.student.dao.UniversityRepository;
import edu.javacourse.student.domain.Address;
import edu.javacourse.student.domain.Adult;
import edu.javacourse.student.domain.Child;
import edu.javacourse.student.domain.Person;
import edu.javacourse.student.domain.Street;
import edu.javacourse.student.domain.StudentOrder;
import edu.javacourse.student.domain.StudentOrderChild;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private StudentOrderChildRepository daoChild;
    @Autowired
    private StreetRepository daoStreet;
    @Autowired
    private StudentOrderStatusRepository daoStatus;
    @Autowired
    private PassportOfficeRepository daoPassport;
    @Autowired
    private RegisterOfficeRepository daoRegisterOffice;
    @Autowired
    private UniversityRepository daoUniversity;
    
    @Transactional
    public void testSave(){
    StudentOrder so = new StudentOrder();
    
    so.setStudentOrderDate(LocalDateTime.now());
    so.setStatus(daoStatus.getReferenceById(1L));
    
    so.setHusband(buildPerson(false));
    so.setWife(buildPerson(true));
    
    so.setCertificateNumber("CERTIFICATE");
    so.setRegisterOffice(daoRegisterOffice.getReferenceById(1L));
    so.setMarriageDate(LocalDate.now());
    
    dao.save(so);
    
    StudentOrderChild soc = buildChild(so);
    daoChild.save(soc);
    }
    @Transactional
    public void testGet(){
        List<StudentOrder> sos = dao.findAll();
        LOG.info(sos.get(0).getWife().getGivenName());
        LOG.info(sos.get(0).getChildren().get(0).getChild().getGivenName());
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
        p.setSurName("??????????");
        p.setGivenName("??????????");
        p.setPatronymic("????????????????????");
        p.setPassportSeria("WIFE_S");
        p.setPassportNumber("WIFE_N");
        p.setPassportOffice(daoPassport.getReferenceById(1L));
        p.setIssueDate(LocalDate.now());
        p.setStudentNumber("12345");
        p.setUniversity(daoUniversity.getReferenceById(1L));
        }else{
        p.setSurName("??????????");
        p.setGivenName("????????");
        p.setPatronymic("????????????????????");
        p.setPassportSeria("HUSBAND_S");
        p.setPassportNumber("HUSBAND_N");
        p.setPassportOffice(daoPassport.getReferenceById(1L));
        p.setIssueDate(LocalDate.now());
        p.setStudentNumber("6789");
        p.setUniversity(daoUniversity.getReferenceById(1L));
        }
        
        return p;
    }
    private StudentOrderChild buildChild(StudentOrder so){
        StudentOrderChild p = new StudentOrderChild();
        p.setStudentOrder(so);

        Child c = new Child();
        c.setDateOfBirth(LocalDate.now());
        c.setSurName("??????????");
        c.setGivenName("??????????????");
        c.setPatronymic("????????????????");
        
        c.setCertificateDate(LocalDate.now());
        c.setCertificateNumber("BIRTH N");
        c.setRegisterOffice(daoRegisterOffice.getReferenceById(1L));
        
        Address a = new Address();
        a.setPostCode("190000");
        a.setBuilding("21");
        a.setExtension("B");
        a.setApartment("199");
        Street one = daoStreet.getReferenceById(1L);
        a.setStreet(one);
        c.setAddress(a);
        
        p.setChild(c);
        
        return p;
    }
}
