package com.home;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class App {
    private Employees employees = new Employees();

    public App() {
        employees.setEmployees(new ArrayList<Employee>());
        //Create two employees
        Employee emp1 = new Employee();
        emp1.setId(1);
        emp1.setFirstName("Lokesh");
        emp1.setLastName("Gupta");
        emp1.setIncome(100.0);

        Employee emp2 = new Employee();
        emp2.setId(2);
        emp2.setFirstName("John");
        emp2.setLastName("Mclane");
        emp2.setIncome(200.0);

        //Add the employees in list
        employees.getEmployees().add(emp1);
        employees.getEmployees().add(emp2);
    }

    private File marshalingExample() throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        //Marshal the employees list in console
        jaxbMarshaller.marshal(employees, System.out);

//        //Marshal the employees list in file
        File tempFile = File.createTempFile("employees", "xml");
        jaxbMarshaller.marshal(employees, tempFile);
        return tempFile;
    }

    private void unMarshalingExample(File file) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        //We had written this file in marshalling example
        Employees emps = (Employees) jaxbUnmarshaller.unmarshal(file);

        for (Employee emp : emps.getEmployees()) {
            System.out.println(emp.getId());
            System.out.println(emp.getFirstName());
        }
    }

    public static void main(String[] args) throws JAXBException, IOException {
        App app = new App();
        File file = app.marshalingExample();
        app.unMarshalingExample(file);
    }
}
