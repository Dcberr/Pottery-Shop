package com.project.potteryshop.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor // Constructor mặc định
@AllArgsConstructor
public class Permission {
    @Id
    private String name;

    private String description;
}
