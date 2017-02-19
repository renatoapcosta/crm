/**
 * @author pulem3t
 */
package org.pulem3t.crm.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.pulem3t.crm.dao.ProductDAO;
import org.pulem3t.crm.entry.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session = null;
	private Transaction tx = null;
	
	
	public List<Product> getProducts() {
		
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		List<Product> productList = session.createCriteria(Product.class).list();
		tx.commit();
		session.close();
		
		return productList;
	}

	
	public Product getProduct(Long id) {
		
		
		session = sessionFactory.openSession();
		Product product = (Product) session.get(Product.class, new Long(id));
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		session.close();
		
		return product;
	}

	
	public Long addProduct(Product product) {
		
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		session.save(product);
		tx.commit();
		session.close();
		
		return product.getId();
	}

	
	public void delProduct(Long id) {
		
		session = sessionFactory.openSession();
		Product product = (Product) session.load(Product.class, new Long(id));
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(product);
		tx.commit();
		session.close();
	}

	
	public void updateProduct(Product product) {
		
		session = sessionFactory.openSession();
		
		Product oldProduct = (Product) session.get(Product.class, product.getId());
		oldProduct.setName(product.getName());
		oldProduct.setCategory(product.getCategory());
		oldProduct.setPrice(product.getPrice());
		oldProduct.setCompanyId(product.getCompanyId());
		oldProduct.setQuantity(product.getQuantity());
		oldProduct.setWeight(product.getWeight());
		oldProduct.setDescription(product.getDescription());
		oldProduct.setTags(product.getTags());
		tx = session.getTransaction();
		session.beginTransaction();
		session.save(oldProduct);
		tx.commit();
		session.close();
	}

	
	public List<Product> getProductsByCompanyId(Long companyId) {
		
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		List<Product> productList = session.createCriteria(Product.class).add(Restrictions.eq("companyId", new Long(companyId))).list();
		tx.commit();
		session.close();
		
		return productList;
		
	}

}
