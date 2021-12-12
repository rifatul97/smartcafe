package com.project.smartcafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class CurrentUserCartStatus {
    private long usercartid;
    private String status;
}
