package com.letsdo.service;

import com.letsdo.exception.UserException;
import com.letsdo.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public User findUserById(Long userId) throws UserException {
        return null;
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        return null;
    }
}
