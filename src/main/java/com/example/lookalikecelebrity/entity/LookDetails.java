package com.example.lookalikecelebrity.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "look_details")
@EqualsAndHashCode(callSuper = true)
public class LookDetails extends BaseEntity {

    @Column(name = "look_url_per_celeb_list")
    private String lookUrlPerCelebList;

    @Column(name="celeb_id")
    private Long celebId;

    @Column(name="look_id")
    private Long lookId;

    @Column(name = "look_url_per_celeb_hero_image")
    private String lookUrlPerCelebHeroImage;

    @Column(name = "look_name")
    private String lookName;

}
