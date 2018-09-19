package com.irhad.restwebshop.Domain.Models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "shop_items")
public class ShopItem {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String name;
    private String description;
    @ManyToOne()
    private Shop shop;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;
    private Boolean enabled;
    private BigDecimal price;
    private Integer count;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "shop_item_category",
            joinColumns = { @JoinColumn(name = "shop_item_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") }
    )
    Set<ItemCategory> categories = new HashSet<>();

    @OneToMany(mappedBy = "shopItem", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<FileResource> photos = new HashSet<>();
}
