package openones.oopms.projecteye.dao;

import java.math.BigDecimal;
import java.util.List;

import openones.oopms.projecteye.controller.CreateProjectController;
import openones.oopms.projecteye.model.BusinessDomain;
import openones.oopms.projecteye.model.GeneralReference;
import openones.oopms.projecteye.model.LanguageCode;
import openones.oopms.projecteye.model.Project;
import openones.oopms.projecteye.utils.HibernateUtil;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ProjectDao {
    private Session session;
    private Transaction tx = null;
    private static Logger log = Logger.getLogger(CreateProjectController.class);
    
    
   public List<Project> getProjectList(String developerId) {
       try {
    	   SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
    	   session = sessionfactory.openSession();
    	   session.beginTransaction();
           String hql = "From Project where projectId IN (Select project from Assignment WHERE developerId = :devId)";
           Query query = session.createQuery(hql);
           query.setParameter("devId", new BigDecimal(developerId));
           List<Project> projectList = query.list();               
           session.flush();
           session.getTransaction().commit();
           System.out.println("Project Name Count : "+projectList.size());
           log.error("Project Name Count : "+projectList.size());
           return projectList;
           
       } catch (Exception e) {
           log.error(e.getMessage());
       }
       return null;
   }
   
   /**
    * Use to create a new project
    * @param project
    * @return
    */
   public boolean insertProject(Project project) {
	   try {
       SessionFactory sessfac = HibernateUtil.getSessionFactory();
       session = sessfac.openSession();
       tx = session.beginTransaction();
       session.save(project);
       tx.commit();
       sessfac.close();       
	   } catch (Exception e) {
		   log.error("Insert deo duoc");
		   log.error(e.getMessage());
	       return false;             
       }
	   log.error("Insert ngon");
       return true;
   }
   
   
   public List<GeneralReference> getProjectStatusList() {
       try {
    	   SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
    	   session = sessionfactory.openSession();
    	   session.beginTransaction();
           String hql = "From GeneralReference where groupCode = :gCode and languageCode = :lCode";
           Query query = session.createQuery(hql);
           query.setParameter("gCode", "PROJECT_STATUS");
           LanguageCode lCode = new LanguageCode();
           lCode.setLangCode("en");
           query.setParameter("lCode", lCode);
           List<GeneralReference> projectStatusList = query.list();               
           session.flush();
           session.getTransaction().commit();
           log.error("Project Status Count : "+projectStatusList.size());
           return projectStatusList;
           
       } catch (Exception e) {
           log.error(e.getMessage());
       }
       return null;
   }
   
   public List<GeneralReference> getProjectCategoryList() {
       try {
    	   SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
    	   session = sessionfactory.openSession();
    	   session.beginTransaction();
           String hql = "From GeneralReference where groupCode = :gCode and languageCode = :lCode";
           Query query = session.createQuery(hql);
           query.setParameter("gCode", "PROJECT_CATEGORY");
           LanguageCode lCode = new LanguageCode();
           lCode.setLangCode("en");
           query.setParameter("lCode", lCode);
           List<GeneralReference> projectCategoryList = query.list();               
           session.flush();
           session.getTransaction().commit();
           log.error("Project Category Count : "+projectCategoryList.size());
           return projectCategoryList;
           
       } catch (Exception e) {
           log.error(e.getMessage());
       }
       return null;
   }
   
   public List<BusinessDomain> getProjectBussinessDomainList() {
       try {
    	   SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
    	   session = sessionfactory.openSession();
    	   session.beginTransaction();
           String hql = "From BusinessDomain";
           Query query = session.createQuery(hql);
           List<BusinessDomain> projectBussinessDomainList = query.list();               
           session.flush();
           session.getTransaction().commit();
           log.error("Project Bussiness Domain Count : "+projectBussinessDomainList.size());
           return projectBussinessDomainList;
           
       } catch (Exception e) {
           log.error(e.getMessage());
       }
       return null;
   }

}
