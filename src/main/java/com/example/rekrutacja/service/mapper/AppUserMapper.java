package com.example.rekrutacja.service.mapper;

import com.example.rekrutacja.DTO.AppUserDTO;
import com.example.rekrutacja.entity.users.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppUserMapper {

    AppUserMapper INSTANCE = Mappers.getMapper(AppUserMapper.class);

    AppUserDTO appUserToAppUserDTO(AppUser appUser);
}
