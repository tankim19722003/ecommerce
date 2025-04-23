package ecommerce.example.ecommerce.models;

import ecommerce.example.ecommerce.responses.UserResponse;
import ecommerce.example.ecommerce.responses.UserUpdatedResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "account")
    private String account;

    @Column(name = "password", nullable = false)
    private String password;

//    @Column(name = "address", nullable = true)
//    private String address;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "birth_date")
    private Date birthdate;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "public_id")
    private String publicId;

    @JoinColumn(name = "created_at")
    private LocalDateTime createdAt;


    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserCode> codes;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH
    })
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "user")
    private List<Order> orders;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Shop shop;

    @OneToOne(cascade =  CascadeType.ALL, mappedBy = "user")
    private UserBanned userBanned;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List <Payment> payments;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.REMOVE},
            mappedBy = "user")
    private List<CartItem> cartItems;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Feedback> feedbacks;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List <UserVillage> userVillages;

    public void addRole(Role role) {
        if (roles == null) {
            roles = new ArrayList<>();
        }

        roles.add(role);
    }

    public UserResponse toUserResponse() {

        List<String> roles = getRoles().stream().map(
                Role::getName
        ).toList();

        return UserResponse.builder()
                .id(id)
                .fullname(fullname)
                .avatarUrl(avatarUrl)
                .publicId(publicId)
                .account(account)
                .email(email)
                .gender(gender)
                .birthdate(birthdate)
                .phoneNumber(phoneNumber)
                .roles(roles)
                .build();
    }
    public UserUpdatedResponse toUserUpdatedResponse() {

        List<String> roles = getRoles().stream().map(
                Role::getName
        ).toList();

        UserUpdatedResponse userUpdatedResponse = new UserUpdatedResponse();

        userUpdatedResponse.setId(id);
        userUpdatedResponse.setFullname(fullname);
        userUpdatedResponse.setAvatarUrl(avatarUrl);
        userUpdatedResponse.setPublicId(publicId);
        userUpdatedResponse.setAccount(account);
        userUpdatedResponse.setEmail(email);
        userUpdatedResponse.setGender(gender);
        userUpdatedResponse.setBirthdate(birthdate);
        userUpdatedResponse.setPhoneNumber(phoneNumber);
        userUpdatedResponse.setRoles(roles);

        return userUpdatedResponse;

    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private ShippingProvider shippingProvider;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Wallet wallet;
//    public UserLoginResponse toUserLoginResponse() {
//
//        List<String> roles = getRoles().stream().map(
//                Role::getName
//        ).toList();
//
//        UserLoginResponse userLoginResponse = new UserLoginResponse();
//
//        userLoginResponse.setId(id);
//        userLoginResponse.setFullname(fullname);
//        userLoginResponse.setAvatarUrl(avatarUrl);
//        userLoginResponse.setPublicId(publicId);
//        userLoginResponse.setAccount(account);
//        userLoginResponse.setEmail(email);
//        userLoginResponse.setGender(gender);
//        userLoginResponse.setBirthdate(birthdate);
//        userLoginResponse.setPhoneNumber(phoneNumber);
//        userLoginResponse.setRoles(roles);
//
//        return userLoginResponse;
//
//    }
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

}