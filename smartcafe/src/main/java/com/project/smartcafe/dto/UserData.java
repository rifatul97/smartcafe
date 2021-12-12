package com.project.smartcafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserData {

    private long userid;
    private long usercartid;
    private String status;
    private List<UserCartDto> dtos;

}
