package com.irhad.restwebshop.Repositories;

import com.irhad.restwebshop.Domain.Models.FileResource;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface FileResourceRepository extends CrudRepository<FileResource, UUID> {
}
