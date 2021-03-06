package com.changhong.user.repository;

import com.changhong.common.repository.EntityObjectDao;
import com.changhong.user.domain.Position;
import com.changhong.user.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * User: Jack Wang
 * Date: 14-4-9
 * Time: 上午9:25
 */
public interface UserDao extends EntityObjectDao {

    UserDetails findUserByName(String account);

    Position findPositionByName(String name);

    List<User> loadAllUser();
    List<User> loadUsers(String name, int startPosition, int pageSize);

    int loadUserSize(String name);

    boolean loadUserExist(int userId, String username);

    List<User> loadUserBydepartmentId(int departmentId);

}
