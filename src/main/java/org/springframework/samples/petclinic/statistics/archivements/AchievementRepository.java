package org.springframework.samples.petclinic.statistics.archivements;



import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepository extends CrudRepository<Achievement, Integer>{
    Page<Achievement> findAll(Pageable pageable);
    Optional<Achievement> findById(Integer id);

    @Query(value = "SELECT * FROM achievement  WHERE achievement.name=:name", nativeQuery = true)
	public Achievement findAchievementByName(@Param("name") String name);


}
