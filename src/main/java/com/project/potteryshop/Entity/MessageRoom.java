package com.project.potteryshop.Entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class MessageRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String messageRoomId;
    private String chatId;
    private String senderId;
    private String recipientId;

    // private String name;

    // private Boolean isGroup;

    // @CreatedDate
    // private Date createdDate;

    @ManyToMany
    @JoinTable(name = "room_users", joinColumns = @JoinColumn(name = "messageRoomId"), inverseJoinColumns = @JoinColumn(name = "userId"))
    @JsonManagedReference
    private User users;

    @OneToMany(mappedBy = "messageRoom", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<MessageRoomMember> members;

    @OneToMany(mappedBy = "messageRoom", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<MessageContent> messageContents;

}
