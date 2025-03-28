package com.project.potteryshop.Dto.Response.Authentication;

import com.fasterxml.jackson.databind.node.BooleanNode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IntrospectResponse {
    private Boolean valid;
}
