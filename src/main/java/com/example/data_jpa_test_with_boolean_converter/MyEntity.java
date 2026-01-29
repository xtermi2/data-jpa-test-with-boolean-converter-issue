package com.example.data_jpa_test_with_boolean_converter;

import jakarta.persistence.*;
import org.hibernate.type.YesNoConverter;

@Entity
public class MyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Version
    private Integer version;

    @Convert(converter = YesNoConverter.class)
    private Boolean paused;

    @Column(length = 120)
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getPaused() {
        return paused;
    }

    public void setPaused(Boolean paused) {
        this.paused = paused;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
