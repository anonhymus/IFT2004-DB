package org.anonhyme.tp3.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Anonhyme on 12/3/2016.
 */
@Entity
@Table(name = "TP2_SOURCE_PHOTO", schema = "ANONHYME", catalog = "")
@Data
public class SourcePhotoEntity {
    @Id
    @Column(name = "NO_SOURCE_PHOTO")
    private long noSourcePhoto;
    @Basic
    @Column(name = "NOM_COMPLET_SOU")
    private String nomCompletSou;
}
