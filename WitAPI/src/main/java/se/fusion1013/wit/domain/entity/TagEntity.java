package se.fusion1013.wit.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tags", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private TagSelectionMethod selectionMethod;

    public Long getId() {
        return id;
    }

    public TagSelectionMethod getSelectionMethod() {
        return selectionMethod;
    }

    public void setSelectionMethod(TagSelectionMethod selectionMethod) {
        this.selectionMethod = selectionMethod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void update(TagEntity tag) {
        setSelectionMethod(tag.getSelectionMethod());
        setName(tag.getName());
    }
}
