package com.minglefield.dao;

import com.minglefield.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class UserDAO extends BaseDAO<User> {

  public User findByEmail(String emailAddress) {
    String query = "SELECT user FROM User user WHERE user.emailAddress = :emailAddress";
    try {
      return (User) em.createQuery(query).setParameter("emailAddress", emailAddress)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }
}
