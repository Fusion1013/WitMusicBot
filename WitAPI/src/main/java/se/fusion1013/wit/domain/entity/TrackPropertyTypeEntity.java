package se.fusion1013.wit.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "track_property_types")
public class TrackPropertyTypeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private String upperValueDescription;

    private String lowerValueDescription;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpperValueDescription() {
        return upperValueDescription;
    }

    public void setUpperValueDescription(String upperValueDescription) {
        this.upperValueDescription = upperValueDescription;
    }

    public String getLowerValueDescription() {
        return lowerValueDescription;
    }

    public void setLowerValueDescription(String lowerValueDescription) {
        this.lowerValueDescription = lowerValueDescription;
    }
}
