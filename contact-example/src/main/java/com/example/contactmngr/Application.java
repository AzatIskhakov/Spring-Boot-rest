package com.example.contactmngr;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import com.example.contactmngr.model.Contact;
import com.example.contactmngr.model.Contact.ContactBuilder;

public class Application {
	// Hold a reusable reference to a SessionFactory (since we need only one)
	
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		final ServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure()
				.build();
		return new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}

	public static void main(String[] args) {

		
		Contact contact = new ContactBuilder("Saeeda","Amahalba")
				.withEmail("saeeda@examplest.com")
				.withPhone(888654321L)
				.build();
//		int id = save(contact);
		
//		System.out.println(contact);
//	1	save(contact);	
		
		System.out.println("\n\nBefore update\n\n");
		
		// Display a list of contacts before the update
		for(Contact c : fetchAllContacts()) {
			System.out.println(c);
		}
		
		// Get the persisted contact
//		Contact con = findContactById(id);
		
		// Update the contact
//		con.setFirstName("Muhammad");
		
		// Persist the changes
		System.out.println("\n\nUpdating...\n\n");
//		update(con);
		System.out.println("\n\nUpdate complete successfully!\n\n");
		
		// Display a list of contacts after the update
		// Using Lambda
		fetchAllContacts().stream().forEach(System.out::println);
		
		delete(5);
		// Display a list of contacts after the deleting
		// Using Lambda
		fetchAllContacts().stream().forEach(System.out::println);
				
			
	}
	
	private static Contact findContactById(int id) {
		// Open a session
		Session session = sessionFactory.openSession();
		
		// Retrieve the persistent object (or null if not found)
		Contact contact = session.get(Contact.class, id);
		
		// Close the session
		session.close();
		
		// Return the contact object
		return contact;
	}
	
	private static void update(Contact contact) {
		// Open a session
		Session session = sessionFactory.openSession();
		
		// Begin a transaction
		session.beginTransaction();
		
		// Use the session to update the contact
		session.update(contact);
		
		// Commit the transaction
		session.getTransaction().commit();
		
		// Close the session
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	private static List<Contact> fetchAllContacts() {
		// Open a session
		Session session = sessionFactory.openSession();
		
		// Create Criteria
		Criteria criteria = session.createCriteria(Contact.class);
		
		// Get a list of Contact objects according to the Criteria object
		List<Contact> contacts = criteria.list();
				
		// Close the session
		session.close();
		
		return contacts;
	}
	
	private static int save(Contact contact) {
		
		// Open a session
				Session session = sessionFactory.openSession();
				
				// Begin transaction
				session.beginTransaction();
				
				// Use the session to save the contact
				int id = (int)session.save(contact);
				
				// Commit the transaction
				session.getTransaction().commit();
				
				// Close the session
				session.close();
				
				return id;
	}
	
	private static void delete(int id) {
		
		// Open a session
		Session session = sessionFactory.openSession();
		
		Contact contact = findContactById(id);
		
		// Begin transaction
		session.beginTransaction();
		
		// Use the session to delete the contact
		session.delete(contact);
		
		// Commit the transaction
		session.getTransaction().commit();
		
		// Close the session
		session.close();
	}

}
