package com.project.potteryshop.Dto;

import com.project.potteryshop.Entity.MessageRoom;
import com.project.potteryshop.Entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageRoomMemberKey {
    private User user;
    private MessageRoom messageRoom;
}
