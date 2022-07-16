package com.fayardev.membershipsystem.controllers.abstracts;

import com.fayardev.membershipsystem.entities.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface IUserController {

    @PostMapping("/change-username")
    boolean changeUsername(HttpServletRequest request, @RequestBody String username) throws Exception;

    @PostMapping("/update")
    boolean update(@RequestBody User user) throws Exception;

    @PostMapping("/delete")
    boolean delete(@RequestBody int id) throws Exception;
}
