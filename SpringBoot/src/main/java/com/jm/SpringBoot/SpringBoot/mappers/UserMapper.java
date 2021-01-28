package com.jm.SpringBoot.SpringBoot.mappers;


import com.jm.SpringBoot.SpringBoot.dto.UserDTO;
import com.jm.SpringBoot.SpringBoot.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO toDTO(User user);

}
