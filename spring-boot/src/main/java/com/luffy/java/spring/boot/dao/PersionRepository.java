package com.luffy.java.spring.boot.dao;

import com.luffy.java.spring.boot.entity.Persion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Luffy
 * @date 2018/3/21
 * @description 数据访问操作方法
 */
public interface PersionRepository extends JpaRepository<Persion, Long> {
    // todo 定义数据访问操作方法

    List<Persion> findByName(String name);

    List<Persion> findByNameLike(String name);

    List<Persion> findFirst10ByName(String name);

    @Modifying
    @Query(value = "update persion set name =?2 where id=?1", nativeQuery = true)
    void updateNameById(int id, String name);

    @Modifying
    @Query(value = "delete from persion where id=?1",nativeQuery = true)
    int deletePersionById(int id);

    @Modifying
    @Query(value = "insert into persion(name) VALUES (?1)",nativeQuery = true)
    int savePersion(String name);
}

