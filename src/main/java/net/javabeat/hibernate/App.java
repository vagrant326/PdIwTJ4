package net.javabeat.hibernate;
 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
/**
 * Main class
 *
 */
public class App {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        App app = new App();

        Person person = new Person();
        person.setName("Jiya");
        Address address1 = new Address("M.G.Road", "Bangalore", "Karnataka",
                "56000");
        
        ArrayList<Phone> phones = new ArrayList<Phone>();
        phones.add(new Phone("number1",person));
        phones.add(new Phone("number2",person));
        
        app.savePersonInfo(person, address1, phones);
        app.listPersonInfo();
 
    }
 
    /*
     * Save the data to database table
     */
    public Long savePersonInfo(Person person, Address address, ArrayList<Phone> phones) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Long personId = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            person.setAddress(address);
            address.setPerson(person);

            person.setPhones(phones);
            session.save(person);
 
            transaction.commit();
        	session.flush();
            
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
        	session.flush();
            session.close();
        }
        return personId;
    }
 
    /*
     * Lists the person's from database table
     */
    public void listPersonInfo() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            @SuppressWarnings("unchecked")
            List<Person> personList = session.createQuery("from Person").list();
            for (Iterator<Person> iterator = personList.iterator(); iterator
                    .hasNext();) {
                Person person = (Person) iterator.next();
                System.out.println(person.getName());
                System.out.println(person.getAddress().getStreet());
                System.out.println("Phone number(s):");

                for (Iterator<Phone> iterator2 = person.getPhones().iterator(); iterator2
                        .hasNext();) {
                	Phone phone = (Phone) iterator2.next();
                	System.out.println(phone.getNumber());
                }
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
 
}