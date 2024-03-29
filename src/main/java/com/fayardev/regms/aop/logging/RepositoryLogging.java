package com.fayardev.regms.aop.logging;

import com.fayardev.regms.entities.BaseEntity;
import com.fayardev.regms.entities.Profile;
import com.fayardev.regms.entities.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RepositoryLogging {

    private static void entityLog(String methodName, BaseEntity entity) {
        if (entity instanceof User user) {
            log.info(methodName + "() to: " + user);
        } else if (entity instanceof Profile profile) {
            log.info(methodName + "() to: " + profile);
        } else {
            log.info(methodName + "() to: " + entity);
        }
    }

    private static void objectLog(String methodName, Object obj) {
        if (obj instanceof BaseEntity entity) {
            entityLog(methodName, entity);
        } else {
            log.info(methodName + "() to: " + obj);
        }
    }

    private static void arrayLog(String methodName, Object[] objects) {
        log.info(methodName + "() args: " + objects);
    }

    public static void logBefore(String methodName, Object[] objects) {
        if (objects == null) {
            return;
        }

        if (objects.length == 1) {
            objectLog(methodName, objects[0]);
        } else {
            arrayLog(methodName, objects);
        }
    }
}
