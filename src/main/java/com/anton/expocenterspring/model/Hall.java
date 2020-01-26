package com.anton.expocenterspring.model;

import com.anton.expocenterspring.enums.HallType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "hall")
public class Hall extends BaseEntity {
    @Column(name = "title")
    private String title;
    @Column(name = "area")
    private Double area;
    @Column(name = "image_path")
    private String imagePath;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private HallType type;
    @OneToMany(mappedBy = "hall")
    private Set<Exposition> expositions;
}
