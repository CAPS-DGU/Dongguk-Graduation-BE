package com.dongguk.graduation_be.requirement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "graduation_requirements")
@ToString(exclude = "areaRequirements")
public class GraduationRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Integer entranceYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Curriculum curriculum;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MajorType majorType;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer minimumCredits;

    @OneToMany(mappedBy = "graduationRequirement", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<AreaRequirement> areaRequirements = new ArrayList<>();

    /* TODO: 추후 확정 시 추가될 필드들
       private Integer minEnglishScore;   // 영어 성적
       private Boolean needsGraduationExam; // 졸업시험 여부
       private Boolean needsThesis;         // 졸업논문 여부
    */

}
