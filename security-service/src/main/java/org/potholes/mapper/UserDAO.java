package org.potholes.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.potholes.api.sys.SysUser;
import org.potholes.api.user.UserInfo;
import org.potholes.api.user.UserRole;
import org.potholes.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {

    int insert(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKey(User record);

    SysUser selectByUserName(String userName);

    Integer countUserByKeywords(@Param("keywords") String keywords);

    List<UserInfo> selectUserByKeywords(@Param("keywords") String keywords, RowBounds rowBounds);

    void deleteUserById(String userId);

    List<UserRole> selectRolesByUserId(String userId);

}