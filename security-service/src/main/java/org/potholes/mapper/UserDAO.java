package org.potholes.mapper;

import org.potholes.api.sys.SysUser;
import org.potholes.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {

    int insert(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKey(User record);

    SysUser selectByUserName(String userName);

}