package se.fusion1013.wit.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "track_property_values")
public class TrackPropertyValueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "property_type_id")
    private TrackPropertyTypeEntity propertyType;

    private double value;

    public Long getId() {
        return id;
    }

    public TrackPropertyTypeEntity getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(TrackPropertyTypeEntity propertyType) {
        this.propertyType = propertyType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
