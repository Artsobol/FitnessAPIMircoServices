package io.github.artsobol.trainingservice.integration.media.projection;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface MediaVideoCatalogRepository extends JpaRepository<MediaVideoCatalog, Long> {

    List<MediaVideoCatalog> findAllByIdInAndActiveTrue(Collection<Long> ids);
}
