package com.irhad.restwebshop.Domain.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter @Setter
@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String description;
}