package com.wipro.junit.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.wipro.bo.ContractEmployee;
import com.wipro.dao.ContractEmployeeDAO;

public class JUnitTest {

    private Collection collection;
    int id;
 
    @BeforeClass
    public static void oneTimeSetUp() {
        // one-time initialization code   
    	System.out.println("@BeforeClass - oneTimeSetUp");
    }
 
    @AfterClass
    public static void oneTimeTearDown() {
        // one-time cleanup code
    	System.out.println("@AfterClass - oneTimeTearDown");
    }
 
    @Before
    public void setUp() {
        collection = new ArrayList();
        id=4;
        System.out.println("@Before - setUp");
    }
 
    @After
    public void tearDown() {
        collection.clear();
        System.out.println("@After - tearDown");
    }
 
    @Ignore("Not Ready to Run")
    @Test(expected = ArithmeticException.class ,timeout=100)  
    public void testEmptyCollection() {
        assertTrue(collection.isEmpty());
        System.out.println("@Test - testEmptyCollection");
    }
 
    @Ignore("Not Ready to Run") 
    @Test
    public void testOneItemCollection() {
        collection.add("itemA");
        assertEquals(1, collection.size());
        System.out.println("@Test - testOneItemCollection");
    }

    @Test
    public void testData(){
    	//ContractEmployeeDAO ce=new ContractEmployeeDAO();
		ContractEmployee cd=ContractEmployeeDAO.getContractEmployeeById(id);
		assertEquals(id,cd.getIdEmployee());
	    System.out.println("The user id name is "+cd.getFirstName());
    }
}
