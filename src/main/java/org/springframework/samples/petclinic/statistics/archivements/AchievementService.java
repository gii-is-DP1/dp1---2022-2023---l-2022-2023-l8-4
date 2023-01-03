package org.springframework.samples.petclinic.statistics.archivements;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AchievementService {

    AchievementRepository repo;

    @Autowired
    public AchievementService(AchievementRepository repo){
        this.repo=repo;
    }
    @Transactional(readOnly = true)
    Page<Achievement> getAchievements(Pageable pageable){
        return repo.findAll(pageable);
    }
    @Transactional(readOnly = true)
    public Achievement getById(int id){
        return repo.findById(id).get();
    }
    @Transactional
    public void deleteAchievementById(int id){
        repo.deleteById(id);
    }
    @Transactional
    public void save(Achievement achievement){
    	achievement.setAcquireDate(LocalDate.now());
        repo.save(achievement);
    }
    

}
