package com.fayardev.regms.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OtherProfileDto extends BaseDto {

    private String nameAndSurname;
    private String aboutMe;
    private String avatarPath;
    private OtherUserDto otherUserDto;
}
