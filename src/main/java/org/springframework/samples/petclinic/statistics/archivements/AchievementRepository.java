package org.springframework.samples.petclinic.statistics.archivements;



import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepository extends CrudRepository<Achievement, Integer>{
    Page<Achievement> findAll(Pageable pageable);
    Optional<Achievement> findById(Integer id);



}
