/**
 * @author pulem3t
 */
package org.pulem3t.crm.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.pulem3t.crm.dao.AdminDAO;
import org.pulem3t.crm.entry.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session = null;
	private Transaction tx = null;
	
	
	public List<Admin> getAdmins() {
		
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		List<Admin> adminList = session.createCriteria(Admin.class).list();
		tx.commit();
		session.close();
		
		return adminList;
	}

	
	public Admin getAdmin(Long id) {
		
		
		session = sessionFactory.openSession();
		Admin admin = (Admin) session.get(Admin.class, new Long(id));
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		session.close();
		
		return admin;
	}

	
	public Long addAdmin(Admin admin) {
		
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		session.save(admin);
		tx.commit();
		session.close();
		
		return admin.getId();
	}

	
	public void delAdmin(Long id) {
		
		session = sessionFactory.openSession();
		Admin admin = (Admin) session.load(Admin.class, new Long(id));
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(admin);
		tx.commit();
		session.close();
	}

	
	public void updateAdmin(Admin admin) {
		
		session = sessionFactory.openSession();
		
		Admin oldAdmin = (Admin) session.get(Admin.class, admin.getId());
		oldAdmin.setFirstName(admin.getFirstName());
		oldAdmin.setLastName(admin.getLastName());
		oldAdmin.setRole(admin.getRole());
		tx = session.getTransaction();
		session.beginTransaction();
		session.save(oldAdmin);
		tx.commit();
		session.close();
	}

}
