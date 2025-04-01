package com.project.potteryshop.Entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.project.potteryshop.Enum.UserStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    private String name;

    @Column(unique = true)
    private String email;
    private String phoneNumber;
    private LocalDate dob;

    @Column(unique = true)
    private String username;
    private String address;
    private String password;
    private Date lastLogin;

    // @Enumerated(EnumType.STRING)
    @ManyToMany
    private Set<Role> userRole;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToOne
    @JoinColumn(name = "cartId")
    @JsonManagedReference
    private Cart cart;

    @OneToMany(mappedBy = "user")
    private List<UserOrder> orders;

    @OneToMany(mappedBy = "user")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "user")
    private List<PaymentHistory> paymentHistories;

    @OneToMany(mappedBy = "createdBy")
    @JsonManagedReference
    private List<MessageRoom> messageRooms;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<MessageRoomMember> messageRoomMembers;
}
