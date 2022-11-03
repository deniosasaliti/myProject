package com.example.demo.repos;

import com.example.demo.Entity.Serial;
import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface userRepos extends JpaRepository<User,Long> {
    @Transactional
    Optional<User> findUsersByName(String username);

    User findUserByEmailAndName
            (String email,String userName);
    User findUserByEmailNotLikeAndNameNotLike(String email,String name);

    Optional<User> findByName(String userName);

    User getById(long id);

    List<User> getBySerials(Serial serial);

    @Query(value = "select u from User u left join u.serials s where s.id =:id")
    List<User> sssss(long id);



    User findUserByEmail(String email);






}
