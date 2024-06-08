package com.rent.rentcar.service.user;

import com.rent.rentcar.dto.user.UserMapper;
import com.rent.rentcar.dto.user.UserDto;
import com.rent.rentcar.dto.user.UserToSaveDto;
import com.rent.rentcar.exception.NotAbleToDeleteException;
import com.rent.rentcar.exception.NotFoundExceptionEntity;
import com.rent.rentcar.models.User;
import com.rent.rentcar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto getUserById(Long id) throws NotFoundExceptionEntity {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionEntity("User not found."));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto addUser(UserToSaveDto userToSaveDto) {
        User user = userMapper.saveDtoToEntity(userToSaveDto);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(Long id, UserToSaveDto userToSaveDto) throws NotFoundExceptionEntity {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(userToSaveDto.name());
                    user.setLastName(userToSaveDto.lastName());
                    user.setIdCard(userToSaveDto.idCard());
                    user.setPhone(userToSaveDto.phone());
                    user.setAddress(userToSaveDto.address());

                    User userSaved = userRepository.save(user);
                    return userMapper.toDto(userSaved);
                }).orElseThrow(() -> new NotFoundExceptionEntity("User not found."));
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotAbleToDeleteException("User not found."));
        userRepository.delete(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
}
