package com.irhad.restwebshop.Domain.Models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "item_categories")
public class ItemCategory {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
}
