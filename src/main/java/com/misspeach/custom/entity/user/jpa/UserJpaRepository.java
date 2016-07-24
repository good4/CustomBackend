package com.misspeach.custom.entity.user.jpa;

import com.misspeach.custom.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by jiangtengfei on 16/7/20.
 */
//Jpa 放在哪个层都可以
//继承了JpaRespository , user 是实体 , Long 是主键
public interface UserJpaRepository extends JpaRepository<User, Long> {

    //这种SQL语法叫做JPQL, u 是 User表的别名 ,　?1代表 User表中的第一个字段
    @Query("select u from User u where u.username=?1")

    //简单查询可以自动根据字段生成
    User findOneByUsername(String username);
}
