package com.irhad.restwebshop.Domain.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateItemDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer count;
    private Boolean enabled;
    private Set<Long> categories;
    private Set<MultipartFile> photos;
}
