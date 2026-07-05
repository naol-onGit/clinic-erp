package com.clinicerp.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "sex", nullable = false)
    private String sex;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}