package com.vsu.service;

import com.vsu.entity.User;
import com.vsu.exception.ValidationException;
import com.vsu.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService {
    private static final int MIN_COUNT_UPDATE = 1;
    private final UserRepository userRepository;
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getByLoginAndPassword(String login, String password) {
        User user = userRepository.selectByEmail(login);
        if (user == null) {
            LOGGER.log(Level.INFO, "User with login {0} is not exist", login);
            throw new ValidationException("User is not found");
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        LOGGER.log(Level.WARNING, "Something wrong with input data");
        throw new ValidationException("Bad credentials");
    }

    public User insertUser(User user) {
        validateUser(user);
        if (userRepository.insert(user) < MIN_COUNT_UPDATE) {
            LOGGER.log(Level.INFO, "User with id {0} is not inserted", user.getId());
            return null;
        } else {
            return userRepository.selectByEmail(user.getEmail());
        }
    }

    public void deleteUser(Long id) {
        if (userRepository.deleteById(id) < MIN_COUNT_UPDATE) {
            LOGGER.log(Level.INFO, "User with id {0} is not deleted", id);
        }
    }

    public User selectByIdUser(String id) {
        try {
            Long idL = Long.parseLong(id);
            return userRepository.selectById(idL);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "ID {0} has wrong type", id);
            throw new ValidationException("Bad ID");
        }

    }

    public void updateByID(User user) {
        validateUser(user);
        if (userRepository.updateByID(user) < MIN_COUNT_UPDATE) {
            LOGGER.log(Level.INFO, "User with id {} is not updated", user.getId());
        }
    }

    private static void validateUser(User user) {
        validateDate(user);
        validateName(user.getName(), "Bad name");
        validateName(user.getSurname(), "Bad surname");
        validatePhone(user.getPhone());
        validateEmail(user.getEmail());
    }

    private static void validateEmail(String email) {
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new ValidationException("Email format is invalid");
        }
    }

    private static void validatePhone(String phone) {
        if (phone.indexOf("+") != 0 || !StringUtils.isNumeric(phone.substring(1))) {
            throw new ValidationException("Phone format is invalid");
        }
    }

    private static void validateName(String name, String exception) {
        if (name == null || name.isBlank() || name.length() > 50 || !StringUtils.isAlpha(name)) {
            throw new ValidationException(exception);
        }
    }

    private static void validateDate(User user) {
        if (LocalDate.parse(user.getBirthday()).compareTo(LocalDate.now()) > 0) {
            throw new ValidationException("Bad date");
        }
    }
}
