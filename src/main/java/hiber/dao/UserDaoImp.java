package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCarModelAndSeries(String model, int series) {
      String hql = "FROM User WHERE car.model = :model and car.series = :series";
      TypedQuery<User> userTypedQuery = sessionFactory.getCurrentSession().createQuery(hql, User.class);
      userTypedQuery.setParameter("model", model);
      userTypedQuery.setParameter("series", series);
      return userTypedQuery.setMaxResults(1).getSingleResult();
   }

}
