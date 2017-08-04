package com.kee.ad.service;

import com.kee.ad.model.Supervisor;

/**
 * Created by Kee on 2016/10/30.
 */
public interface SupervisorService {

    /**
     * add user
     */
    int addSupervisor(Supervisor supervisor);

    /**
     * check if is exists;
     *
     * @param supervisor
     * @return
     */
    Supervisor checkUser(Supervisor supervisor);

    /**
     * select by userName
     *
     * @param userName
     * @return
     */
    Supervisor selectUserByUserName(String userName);
}
