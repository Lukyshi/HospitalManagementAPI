    package com.example.HospitalManagementAPI.entity;

    import com.example.HospitalManagementAPI.enums.Role;
    import jakarta.persistence.*;
    import lombok.*;


    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String username;
        private String password;

        @Enumerated(EnumType.STRING)
        private Role role;

    }
