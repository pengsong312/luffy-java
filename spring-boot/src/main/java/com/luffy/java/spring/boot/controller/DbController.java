package com.luffy.java.spring.boot.controller;

import com.luffy.java.spring.boot.entity.Persion;
import com.luffy.java.spring.boot.service.PersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Luffy
 * @date 2018/3/20
 * @description demo controller
 */
@RestController
@EnableAutoConfiguration
public class DbController {

    @Autowired
    private PersionService persionService;

    @RequestMapping("/search/{name}")
    public List<Persion> findPersionByName(@PathVariable("name") String name) {
        return persionService.findPersionsByName(name);
    }

    @RequestMapping("/search/like/{name}")
    public List<Persion> findPersionByNameLike(@PathVariable("name") String name) {
        return persionService.findPersionsByNameLike(name);
    }

    @RequestMapping("/update/{id}/{name}")
    public String findPersionByNameLike(@PathVariable("id") int id, @PathVariable("name") String name) {
        persionService.updatePersionById(id, name);
        return "success";
    }

    @RequestMapping("/delete/{id}")
    public String deletePersionById(@PathVariable("id") int id) {
        persionService.deletePersionById(id);
        return "success";
    }

    @RequestMapping("/save/{name}")
    public String deletePersionById(@PathVariable("name") String name) {
        persionService.savePersion(name);
        return "success";
    }


}
