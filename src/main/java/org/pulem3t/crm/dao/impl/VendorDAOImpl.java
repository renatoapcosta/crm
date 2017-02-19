/**
 * @author pulem3t
 */
package org.pulem3t.crm.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.pulem3t.crm.dao.VendorDAO;
import org.pulem3t.crm.entry.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VendorDAOImpl implements VendorDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session = null;
	private Transaction tx = null;
	
	
	public List<Vendor> getVendors() {
		
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		List<Vendor> vendorList = session.createCriteria(Vendor.class).list();
		tx.commit();
		session.close();
		
		return vendorList;
	}

	
	public Vendor getVendor(Long id) {
		
		
		session = sessionFactory.openSession();
		Vendor vendor = (Vendor) session.get(Vendor.class, new Long(id));
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		session.close();
		
		return vendor;
	}

	
	public Long addVendor(Vendor vendor) {
		
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		session.save(vendor);
		tx.commit();
		session.close();
		
		return vendor.getId();
	}

	
	public void delVendor(Long id) {
		
		session = sessionFactory.openSession();
		Vendor vendor = (Vendor) session.load(Vendor.class, new Long(id));
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(vendor);
		tx.commit();
		session.close();
	}

	
	public void updateVendor(Vendor vendor) {
		
		session = sessionFactory.openSession();
		
		Vendor oldVendor = (Vendor) session.get(Vendor.class, vendor.getId());
		oldVendor.setFirstName(vendor.getFirstName());
		oldVendor.setLastName(vendor.getLastName());
		oldVendor.setRole(vendor.getRole());
		oldVendor.setAddress(vendor.getAddress());
		oldVendor.setPhone(vendor.getPhone());
		oldVendor.setFax(vendor.getFax());
		oldVendor.setCountry(vendor.getCountry());
		oldVendor.setCity(vendor.getCity());
		oldVendor.setState(vendor.getState());
		oldVendor.setZipPostCode(vendor.getZipPostCode());
		tx = session.getTransaction();
		session.beginTransaction();
		session.save(oldVendor);
		tx.commit();
		session.close();
	}

}
