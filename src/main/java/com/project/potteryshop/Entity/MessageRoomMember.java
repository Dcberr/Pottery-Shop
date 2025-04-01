package com.project.potteryshop.Entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.potteryshop.Dto.MessageRoomMemberKey;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(MessageRoomMemberKey.class)
public class MessageRoomMember {
    @Id
    @ManyToOne
    @JoinColumn(name = "messageRoomId")
    @JsonBackReference
    private MessageRoom messageRoom;

    @Id
    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonBackReference
    private User user;

    private Boolean isAdmin;

    private Date lastSeen;
}
