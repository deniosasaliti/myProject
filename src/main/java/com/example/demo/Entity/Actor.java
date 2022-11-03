package com.example.demo.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date born;
    private String name;
    private String imageUrl;

    private String almaMater;
    @ElementCollection()
    @CollectionTable(name = "award", joinColumns = @JoinColumn(name = "actor_id"))
    private List<String> awards;
    private String wikiUrl;
    private String shortAboutActor;




    @ManyToMany(mappedBy = "actors")
//    @JsonBackReference
    private Set<Serial> serial = new HashSet<>();





}
