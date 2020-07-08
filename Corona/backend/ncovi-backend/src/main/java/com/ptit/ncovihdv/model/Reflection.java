package com.ptit.ncovihdv.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 03-Jun-2020
 */
@Data
@Entity
@Table(name = "reflection", schema = "ncovi", catalog = "")
public class Reflection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reflection_id", nullable = false)
    private Integer reflectionId;

    @Column(name = "reflection_contact_question_1", nullable = true)
    private Integer reflectionContactQuestion1;

    @Column(name = "reflection_contact_question_2", nullable = true)
    private Integer reflectionContactQuestion2;

    @Column(name = "reflection_contact_question_3", nullable = true)
    private Integer reflectionContactQuestion3;

    @Column(name = "reflection_info_question_1", nullable = true)
    private Integer reflectionInfoQuestion1;

    @Column(name = "reflection_info_question_2", nullable = true)
    private Integer reflectionInfoQuestion2;

    @Column(name = "reflection_info_question_3", nullable = true)
    private Integer reflectionInfoQuestion3;

    @Column(name = "reflection_info_description", nullable = true, length = 256)
    private String reflectionInfoDescription;
    
    @Column(name = "reflection_info_address", nullable = true, length = 256)
    private String reflectionInfoAddress;

    @Column(name = "reflection_info_time", nullable = true)
    private LocalDateTime reflectionInfoTime;


    @Column(name = "reflection_type", nullable = true)
    private Integer reflectionType;

    @Column(name = "reflection_created_at", nullable = true)
    private LocalDateTime reflectionCreatedAt;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "user_id", nullable = false)
    private User userByUser;

}
