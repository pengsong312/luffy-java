package com.luffy.java.spring.boot.service;

import com.luffy.java.spring.boot.dao.PersionRepository;
import com.luffy.java.spring.boot.entity.Persion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Luffy
 * @date 2018/3/21
 * @description persion操作类
 */
@Service
public class PersionService {

    @Autowired
    private PersionRepository persionRepository;

    public List<Persion> findPersionsByName(String name) {
        return persionRepository.findByName(name);
    }

    public List<Persion> findPersionsByNameLike(String name) {
        return persionRepository.findByNameLike(name);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updatePersionById(int id, String name) {
        persionRepository.updateNameById(id, name);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletePersionById(int id) {
        persionRepository.deletePersionById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void savePersion(String name) {
        persionRepository.savePersion(name);
    }
}
