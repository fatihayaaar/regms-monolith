package com.fayardev.regms.repositories;

import com.fayardev.regms.entities.Profile;
import com.fayardev.regms.entities.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ProfileRepository extends BaseRepository<Profile, Long> {

    @Transactional
    Profile getProfileByUser(User user);
}
