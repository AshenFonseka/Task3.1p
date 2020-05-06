/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.jpa;

import entity.Myuser;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Lenovo
 */
public class MyuserDB {

    private EntityManager em = null;

    public MyuserDB() {
// using default persistence unit defined in the persistence.xml file
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ED-EntityPU");
        em = emf.createEntityManager();
    }

    public EntityManager getEntityManger() {

        return em;
    }

    public Myuser findMyuser(String userid) {
        return em.find(Myuser.class, userid);
    }

    public boolean createMyuser(Myuser myuser) throws Exception {
        try {
            if (findMyuser(myuser.getUserid()) != null) {
// myuser exists already
                return false;
            }
// myuser does not exist in database
            em.getTransaction().begin();
            em.persist(myuser); // to add an object to database
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean createRecord(MyuserDTO myuserDTO) {
        Myuser myuser = this.myDTO2DAO(myuserDTO);
        boolean result = false;
        try {
            result = this.createMyuser(myuser);
        } catch (Exception ex) {
        }
        return result;
    }

    private Myuser myDTO2DAO(MyuserDTO myDTO) {
        Myuser myuser = new Myuser();
        myuser.setUserid(myDTO.getUserid());
        myuser.setName(myDTO.getName());
        myuser.setPassword(myDTO.getPassword());
        myuser.setEmail(myDTO.getEmail());
        myuser.setPhone(myDTO.getPhone());
        myuser.setAddress(myDTO.getAddress());
        myuser.setSecqn(myDTO.getSecQn());
        myuser.setSecans(myDTO.getSecAns());

        return myuser;
    }

    public boolean updateRecord(MyuserDTO myuserDTO) {
        Myuser myuser = this.myDTO2DAO(myuserDTO);
        boolean result = false;
        try {
            result = this.createMyuser(myuser);
        } catch (Exception ex) {

            myuser.setUserid(myuserDTO.getUserid());
            myuser.setName(myuserDTO.getName());
            myuser.setPassword(myuserDTO.getPassword());
            myuser.setEmail(myuserDTO.getEmail());
            myuser.setPhone(myuserDTO.getPhone());
            myuser.setAddress(myuserDTO.getAddress());
            myuser.setSecqn(myuserDTO.getSecQn());
            myuser.setSecans(myuserDTO.getSecAns());

            em.getTransaction().begin();
            em.merge(myuser); // to merge an object to database
            em.getTransaction().commit();
        }

        return result;
    }

    public MyuserDTO getRecord(String userId) {
        if (findMyuser(userId) != null) {
// myuser exists already
            System.out.println("User Found");
        } else {

            return null;
        }
        return null;
    }

    public boolean deleteRecord(String userId) {
        
        if (findMyuser(userId) != null) {
            Myuser myuser = em.find(Myuser.class, userId);
            em.getTransaction().begin();
            em.remove(myuser); // to remove an object to database
            em.getTransaction().commit();
            System.out.println("User Found and Removed");
        } else {
            System.out.println("User NOT Found");
        }
        return false;
    }
}
