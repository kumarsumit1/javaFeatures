package com.wipro.sas.sqlpprc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.sample.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sqlproc.engine.SqlEngineFactory;
import org.sqlproc.engine.SqlFeature;
import org.sqlproc.engine.SqlSession;
import org.sqlproc.engine.SqlSessionFactory;
import org.sqlproc.engine.impl.SqlStandardControl;
import org.sqlproc.engine.jdbc.JdbcEngineFactory;
import org.sqlproc.engine.jdbc.JdbcSessionFactory;
import org.sqlproc.engine.util.DDLLoader;
import com.wipro.sas.sqlpprc.dao.ContactDao;
import com.wipro.sas.sqlpprc.dao.FunctionsDao;
import com.wipro.sas.sqlpprc.dao.PersonDao;
import com.wipro.sas.sqlpprc.dao.ProceduresDao;
import com.wipro.sas.sqlpprc.model.AnHourBefore;
import com.wipro.sas.sqlpprc.model.Contact;
import com.wipro.sas.sqlpprc.model.NewPerson;
import com.wipro.sas.sqlpprc.model.NewPersonRetRs;
import com.wipro.sas.sqlpprc.model.Person;

public class Main {

    private static final Driver JDBC_DRIVER = new org.hsqldb.jdbcDriver();
    private static final String DB_URL = "jdbc:hsqldb:mem:sqlproc";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";
    private static final String DB_TYPE = SqlFeature.HSQLDB;
    private static final String DB_DDL = "hsqldb.ddl";
    private static final String[] DB_CLEAR = null;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Connection connection;
    private SqlSessionFactory sessionFactory;
    private SqlEngineFactory sqlFactory;
    private List<String> ddls;

    static {
        try {
            DriverManager.registerDriver(JDBC_DRIVER);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Main() throws SQLException {
        JdbcEngineFactory factory = new JdbcEngineFactory();
        factory.setMetaFilesNames("statements.qry");
        factory.setFilter(DB_TYPE);
        this.sqlFactory = factory;

        ddls = DDLLoader.getDDLs(this.getClass(), DB_DDL);
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        sessionFactory = new JdbcSessionFactory(connection);

        contactDao = new ContactDao(sqlFactory, sessionFactory);
        personDao = new PersonDao(sqlFactory, sessionFactory);
        functionsDao = new FunctionsDao(sqlFactory, sessionFactory);
        proceduresDao = new ProceduresDao(sqlFactory, sessionFactory);
    }

    public void setupDb() throws SQLException {
        SqlSession sqlSession = sessionFactory.getSqlSession();
        sqlSession.executeBatch((DB_CLEAR != null) ? DB_CLEAR : ddls.toArray(new String[0]));
    }

    private ContactDao contactDao;
    private PersonDao personDao;
    private FunctionsDao functionsDao;
    private ProceduresDao proceduresDao;

    public Person insertPersonContacts(Person person, Contact... contacts) {
        Person p = getPersonDao().insert(person);
        for (Contact contact : contacts) {
            Contact c = getContactDao().insert(contact._setPerson(p));
            p.getContacts().add(c);
        }
        return p;
    }

    public static void main(String[] args) throws Exception {
        Person person, p;
        Contact contact, c;
        int count;

        List<Person> list;
        List<Contact> listc;
        Main main = new Main();
        main.setupDb();

        // insert
        Person jan = main.insertPersonContacts(new Person("Jan", "Jánský"), new Contact()._setAddress("Jan address 1")
                ._setPhoneNumber("111-222-3333"));
        Person janik = main.insertPersonContacts(new Person("Janík", "Janíček"),
                new Contact()._setAddress("Janik address 1"));
        Person honza = main.insertPersonContacts(new Person("Honza", "Honzovský"),
                new Contact()._setAddress("Honza address 1"), new Contact()._setAddress("Honza address 2"));
        Person honzik = main.insertPersonContacts(new Person("Honzik", "Honzíček"));
        Person andrej = main.insertPersonContacts(new Person("Andrej", "Andrejček")._setSsn("123456789"), new Contact()
                ._setAddress("Andrej address 1")._setPhoneNumber("444-555-6666"));

        // update
        person = new Person();
        person.setId(andrej.getId());
        person.setFirstName("Andrejík");
        count = main.getPersonDao().update(person);
        Assert.assertEquals(1, count);

        // get & update person with null values
        person = new Person();
        person.setId(andrej.getId());
        p = main.getPersonDao().get(person);
        Assert.assertNotNull(p);
        Assert.assertEquals("Andrejík", p.getFirstName());
        Assert.assertEquals("Andrejček", p.getLastName());
        Assert.assertEquals("123456789", p.getSsn());
        Assert.assertTrue(p.getContacts().size() == 0);

        person = new Person();
        person.setId(andrej.getId());
        person.setFirstName("Andrioša");
        person.setNull(Person.Attribute.ssn);
        count = main.getPersonDao().update(person);
        Assert.assertEquals(1, count);

        // get person with associations
        person = new Person();
        person.setId(andrej.getId());
        person.setInit(Person.Association.contacts.name());
        p = main.getPersonDao().get(person);
        Assert.assertNotNull(p);
        Assert.assertEquals("Andrioša", p.getFirstName());
        Assert.assertEquals("Andrejček", p.getLastName());
        Assert.assertNull(p.getSsn());
        Assert.assertEquals(1, p.getContacts().size());
        Assert.assertEquals("Andrej address 1", p.getContacts().get(0).getAddress());
        Assert.assertEquals("444-555-6666", p.getContacts().get(0).getPhoneNumber());

        // list people with associations
        list = main.getPersonDao().list(null);
        Assert.assertEquals(5, list.size());
        person = new Person();
        person.setFirstName("XXX");
        list = main.getPersonDao().list(person);
        Assert.assertEquals(0, list.size());
        person.setFirstName("Jan");
        person.setInit(Person.Association.contacts.name());
        list = main.getPersonDao().list(person);
        person = new Person();
        person.setInit(Person.Association.contacts.name());
        list = main.getPersonDao().list(person, new SqlStandardControl().setDescOrder(Person.ORDER_BY_ID));
        Assert.assertEquals(5, list.size());
        Assert.assertEquals("Honzíček", list.get(1).getLastName());
        list = main.getPersonDao().list(person, new SqlStandardControl().setAscOrder(Person.ORDER_BY_LAST_NAME));
        Assert.assertEquals(5, list.size());
        Assert.assertEquals("Honzovský", list.get(1).getLastName());
        person = new Person();
        list = main.getPersonDao().list(person,
                new SqlStandardControl().setAscOrder(Person.ORDER_BY_LAST_NAME).setMaxResults(2));
        Assert.assertEquals(2, list.size());

        // count
        count = main.getPersonDao().count(null);
        Assert.assertEquals(5, count);
        person = new Person();
        person.setFirstName("Jan");
        count = main.getPersonDao().count(person);
        Assert.assertEquals(2, count);

        // operators
        contact = new Contact();
        contact.setPhoneNumber("444-555-6666");
        listc = main.getContactDao().list(contact);
        Assert.assertEquals(1, listc.size());
        Assert.assertEquals("444-555-6666", listc.get(0).getPhoneNumber());
        contact.setOp("<>", Contact.OpAttribute.phoneNumber);
        listc = main.getContactDao().list(contact);
        Assert.assertEquals(1, listc.size());
        Assert.assertEquals("111-222-3333", listc.get(0).getPhoneNumber());
        contact = new Contact();
        contact.setNullOp(Contact.OpAttribute.phoneNumber);
        count = main.getContactDao().count(contact);
        Assert.assertEquals(3, count);

        // delete
        count = main.getPersonDao().delete(jan);
        Assert.assertEquals(1, count);

        // function
        AnHourBefore anHourBefore = new AnHourBefore();
        anHourBefore.setT(new java.sql.Timestamp(new Date().getTime()));
        java.sql.Timestamp result = main.getFunctionsDao().anHourBefore(anHourBefore);
        Assert.assertNotNull(result);

        // procedures
        NewPerson newPerson = new NewPerson();
        newPerson.setFirstName("Maruska");
        newPerson.setLastName("Maruskova");
        newPerson.setSsn("999888777");
        newPerson.setDateOfBirth(getAge(1969, 11, 1));
        main.getProceduresDao().newPerson(newPerson);
        Assert.assertNotNull(newPerson.getNewid());

        NewPersonRetRs newPersonRetRs = new NewPersonRetRs();
        newPersonRetRs.setFirstName("Beruska");
        newPersonRetRs.setLastName("Beruskova");
        newPersonRetRs.setSsn("888777666");
        newPersonRetRs.setDateOfBirth(getAge(1969, 1, 21));
        list = main.getProceduresDao().newPersonRetRs(newPersonRetRs);
        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());
        Assert.assertNotNull(list.get(0).getId());
    }

    public static java.sql.Timestamp getAge(int year, int month, int day) {
        Calendar birthDay = Calendar.getInstance();
        birthDay.set(Calendar.YEAR, year);
        birthDay.set(Calendar.MONTH, month);
        birthDay.set(Calendar.DAY_OF_MONTH, day);
        birthDay.set(Calendar.HOUR_OF_DAY, 0);
        birthDay.set(Calendar.MINUTE, 0);
        birthDay.set(Calendar.SECOND, 0);
        birthDay.set(Calendar.MILLISECOND, 0);
        return new java.sql.Timestamp(birthDay.getTime().getTime());
    }

    public ContactDao getContactDao() {
        return contactDao;
    }

    public PersonDao getPersonDao() {
        return personDao;
    }

    public FunctionsDao getFunctionsDao() {
        return functionsDao;
    }

    public ProceduresDao getProceduresDao() {
        return proceduresDao;
    }
}
