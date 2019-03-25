package il.ac.hit.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * In an application the Data Access Object (DAO) is a part of Data access layer. It is an object
 * that provides an interface to some type of persistence mechanism. By mapping application calls
 * to the persistence layer, DAO provides some specific data operations without exposing details
 * of the database. This isolation supports the Single responsibility principle. It separates what
 * data accesses the application needs, in terms of domain-specific objects and data types
 * (the public interface of the DAO), from how these needs can be satisfied with a specific DBMS,
 * database schema, etc.
 *
 * <p>Any change in the way data is stored and retrieved will not change the client code as the
 * client will be using interface and need not worry about exact source.
 *
 * @see UsersActivitiesDAO
 * @see WorkoutDAO
 * @see UsersDAO
 */

public interface IGymDAO<T,M> extends Serializable {
   /* String login(User user) throws IOException;
    String signup(User user) throws IOException;
    String add(sport_activity activity);
    sport_activity getActivity(); */

 /**
  * @return a generic object from the DB, that been recognized by id.
  * @throws GymExceptionHandeler
  **/
    public T get(Class<T> cl, M id) throws GymExceptionHandeler;
 /**
  * @param object svae genric object to the DB.
  * @return the object that we saved
  * @throws GymExceptionHandeler
  **/
    public T save(T object) throws GymExceptionHandeler;
 /**
  *
  * @param object , update an pjebct in DB.
  * @return
  * @throws GymExceptionHandeler
  **/
    public void update(T object) throws GymExceptionHandeler;

 /**
  *
  * @param object, delting an object from the DB.
  * @return
  * @throws GymExceptionHandeler
  **/
    public void delete(T object) throws GymExceptionHandeler;

 /**
  *
  * @param params,hsql1
  * @return a genric list according to sql query
  * @throws GymExceptionHandeler
  **/
    public List<T> query(String hsql, Map<String, Object> params) throws GymExceptionHandeler;
}
