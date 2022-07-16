package com.fayardev.membershipsystem.controllers.abstracts;

import com.fayardev.membershipsystem.entities.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

public interface IUserController {

    @PostMapping("/change")
    boolean change(HttpServletRequest request, @RequestParam(name = "type") String type, @RequestBody String username) throws Exception;

    @PostMapping("/update")
    boolean update(@RequestBody User user) throws Exception;

    @PostMapping("/delete")
    boolean delete(@RequestBody int id) throws Exception;
}
