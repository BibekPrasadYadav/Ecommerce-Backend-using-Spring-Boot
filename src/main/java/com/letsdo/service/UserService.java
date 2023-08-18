package com.letsdo.service;

import com.letsdo.exception.UserException;
import com.letsdo.model.User;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.stereotype.Service;

public interface UserService {
    public User findUserById(Long userId) throws UserException;
    public User findUserProfileByJwt(String jwt) throws UserException;
}
