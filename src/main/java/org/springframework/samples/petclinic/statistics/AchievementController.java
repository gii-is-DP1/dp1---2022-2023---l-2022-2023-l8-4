package org.springframework.samples.petclinic.statistics;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/statistics/achievements")
public class AchievementController {

    private final String ACHIEVEMENTS_LISTING_VIEW="achievements/AchievementsListing";
    private final String ACHIEVEMENTS_FORM="achievements/createOrUpdateAchievementForm";

    private AchievementService service;
    
    @Autowired
    public AchievementController(AchievementService service) {
		this.service = service;
	}

    @GetMapping("/")
    public ModelAndView showAchievements(){
        ModelAndView result=new ModelAndView(ACHIEVEMENTS_LISTING_VIEW);
        result.addObject("achievements", service.getAchievements());
        return result;
    }

    
    @GetMapping("{id}/delete")
    public ModelAndView deleteAchievement(@PathVariable("id") Integer id){
        service.deleteAchievementById(id);        
        return showAchievements();
    }

    @GetMapping("{id}/edit")
    public ModelAndView editAchievement(@PathVariable("id") Integer id){
        Achievement achievement=service.getById(id);        
        ModelAndView result=new ModelAndView(ACHIEVEMENTS_FORM);
        result.addObject("achievement", achievement);
        return result;
    }
 
    @Transactional
    @PostMapping("{id}/edit")
    public ModelAndView saveAchievement(@PathVariable("id") Integer id,Achievement achievement){

        Achievement achievementToBeUpdated=service.getById(id);
        BeanUtils.copyProperties(achievement,achievementToBeUpdated,"id");
        service.save(achievementToBeUpdated);
        ModelAndView result=showAchievements();
        result.addObject("message", "The achievement was updated successfully");
        return result;        
    }

    @Transactional(readOnly = true)
    @GetMapping("new")
    public ModelAndView createAchievement(){
        Achievement achievement=new Achievement();
        ModelAndView result=new ModelAndView(ACHIEVEMENTS_FORM);
        result.addObject("achievement", achievement);
        return result;
    }

    @Transactional
    @PostMapping("new")
    public ModelAndView saveNewAchievement(Achievement achievement, BindingResult br){
        service.save(achievement);
        ModelAndView result=showAchievements();
        result.addObject("message", "The achievement was created successfully");
        return result;
    }


}