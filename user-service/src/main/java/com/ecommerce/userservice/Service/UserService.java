package com.ecommerce.userservice.Service;

import com.ecommerce.userservice.Controller.DTO.UserRegistrationDto;
import com.ecommerce.userservice.Model.User;

public interface UserService {

	User save(UserRegistrationDto registrationdto);
}
