package org.springframework.samples.petclinic.statistics.archivements;


import javax.validation.Valid;

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

    @GetMapping
    public ModelAndView showAchievements(){
        ModelAndView result=new ModelAndView(ACHIEVEMENTS_LISTING_VIEW);
        result.addObject("achievements", service.getAchievements());
        return result;
    }




    @GetMapping("/{id}/delete")
    public ModelAndView deleteAchievement(@PathVariable("id") Integer id){
        service.deleteAchievementById(id);
        return showAchievements();
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editAchievement(@PathVariable("id") Integer id){
        Achievement achievement=service.getById(id);
        ModelAndView result=new ModelAndView(ACHIEVEMENTS_FORM);
        result.addObject("achievement", achievement);
        return result;
    }

    @Transactional
    @PostMapping("/{id}/edit")
    public ModelAndView saveAchievement(@PathVariable("id") Integer id, @Valid Achievement achievement, BindingResult br){
    	ModelAndView result=null;
        if(br.hasErrors()) {
            result = new ModelAndView(ACHIEVEMENTS_FORM);
            result.addAllObjects(br.getModel());
        }else {
        	Achievement achievementToBeUpdated=service.getById(id);
        	if(achievementToBeUpdated!=null) {
        		BeanUtils.copyProperties(achievement,achievementToBeUpdated,"id");
                service.save(achievement);
                result =showAchievements();
                result.addObject("message", "The achievement was updated successfully");
        	}else {
        		result = showAchievements();
                result.addObject("message", "Logro con id "+id+" no ha sido editado correctamente");
            }


        }
		return result;

    }


    @GetMapping("/new")
    public ModelAndView createAchievement(){
        Achievement achievement=new Achievement();
        ModelAndView result=new ModelAndView(ACHIEVEMENTS_FORM);
        result.addObject("achievement", achievement);
        return result;
    }

    @PostMapping("/new")
    public ModelAndView saveNewAchievement(@Valid Achievement achievement, BindingResult br){
    	ModelAndView result=null;
        if(br.hasErrors()) {
            result = new ModelAndView(ACHIEVEMENTS_FORM);
            result.addAllObjects(br.getModel());
        }else {
        	 service.save(achievement);
            result = showAchievements();
            result.addObject("message", "Jugador creado satisfactoriamente");

        }
        return result;
    }



}
