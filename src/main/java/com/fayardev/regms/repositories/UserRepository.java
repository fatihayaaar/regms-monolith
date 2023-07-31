package com.fayardev.regms.repositories;

import com.fayardev.regms.entities.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    @Transactional
    User getUserByEmailAddress(String emailAddress);

    @Transactional
    User getUserByPhoneNo(String phoneNo);

    @Transactional
    User getUserByUsername(String username);
}
