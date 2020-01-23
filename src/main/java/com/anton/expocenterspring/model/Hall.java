package com.anton.expocenterspring.model;

import com.anton.expocenterspring.enums.HallType;

import javax.persistence.*;
import java.util.Set;

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

    public Hall() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public HallType getType() {
        return type;
    }

    public void setType(HallType type) {
        this.type = type;
    }

    public Set<Exposition> getExpositions() {
        return expositions;
    }

    public void setExpositions(Set<Exposition> expositions) {
        this.expositions = expositions;
    }
}
