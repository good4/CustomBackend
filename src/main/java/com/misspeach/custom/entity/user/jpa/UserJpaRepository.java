package com.misspeach.custom.entity.user.jpa;

import com.misspeach.custom.entity.custom.Custom;
import com.misspeach.custom.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by jiangtengfei on 16/7/20.
 */
//Jpa 放在哪个层都可以
//继承了JpaRespository , user 是实体 , Long 是主键
public interface UserJpaRepository extends JpaRepository<User, Long> {

    //这种SQL语法叫做JPQL, u 是 User表的别名 ,　?1代表 User表中的第一个字段
    @Query("select u.customs from User u where u.user_name=?1")
    //简单查询可以自动根据字段生成
    List<Custom> findByUsername(String username);

    @Query("select u.customs from User u where u.id=?1")
        //简单查询可以自动根据字段生成
    List<Custom> findByUserId(Long userid);

    @Query("select u from User u where u.user_name=?1")
    User findUserByUsername(String username);

    @Query("select u from User u where u.id=?1")
    User findUserByUserId(Long userid);
}
