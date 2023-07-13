package com.example.lookalikecelebrity.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "celebrity_details")
@EqualsAndHashCode(callSuper = true)
public class CelebrityDetails extends BaseEntity {

    @Column(name = "celebrity_name")
    private String celebrityName;

    @Column(name = "celebrity_hero_image_url")
    private String celebHeroImageUrl;
}
