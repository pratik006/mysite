package com.prapps.app.core.api;

import com.prapps.app.core.dto.User;

public interface UserService {

	User findUser(String username);
}
