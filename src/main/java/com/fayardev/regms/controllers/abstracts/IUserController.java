package com.fayardev.regms.controllers.abstracts;

import com.fayardev.regms.entities.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

public interface IUserController {

    @PostMapping("/change")
    boolean change(HttpServletRequest request, @RequestParam String type, @RequestParam String username) throws Exception;

    @PostMapping("/freeze")
    boolean freeze(HttpServletRequest request) throws Exception;

    @PostMapping("/update")
    boolean update(@RequestBody User user) throws Exception;

    @PostMapping("/delete")
    boolean delete(@RequestBody int id) throws Exception;
}
