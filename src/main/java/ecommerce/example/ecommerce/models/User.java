package ecommerce.example.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

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


}