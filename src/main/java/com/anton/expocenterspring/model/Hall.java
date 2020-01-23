package com.anton.expocenterspring.model;

import com.anton.expocenterspring.enums.HallType;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Hall extends BaseEntity {
    private String title;
    private Double area;
    private String imagePath;
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
