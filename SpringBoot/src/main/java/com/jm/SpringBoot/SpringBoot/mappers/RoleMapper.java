package com.jm.SpringBoot.SpringBoot.mappers;

import com.jm.SpringBoot.SpringBoot.dto.RoleDTO;
import com.jm.SpringBoot.SpringBoot.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO toDTO(Role role);
}
