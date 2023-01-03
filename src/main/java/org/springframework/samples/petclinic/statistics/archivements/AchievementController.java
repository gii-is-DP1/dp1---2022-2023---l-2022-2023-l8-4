package org.springframework.samples.petclinic.statistics.archivements;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView showAchievements(@RequestParam Map<String, Object> params){
    	int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) -1) : 0;

		PageRequest pageRequest = PageRequest.of(page, 5);
		Page<Achievement> pageAchievement= service.getAchievements(pageRequest);

		int totalPages = pageAchievement.getTotalPages();
		List<Integer> pages=new ArrayList<>();
		if(totalPages > 0) {
			pages= IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
		}

        ModelAndView result = new ModelAndView(ACHIEVEMENTS_LISTING_VIEW);
        result.addObject("achievements", pageAchievement.getContent());
        result.addObject("pages", pages);
        result.addObject("current", page + 1);
        result.addObject("next", page + 2);
        result.addObject("prev", page);
        result.addObject("last", totalPages);
        return result;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteAchievement(@PathVariable("id") Integer id){
        service.deleteAchievementById(id);
        return showAchievements(new HashMap<>());
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
                result =showAchievements(new HashMap<>());
                result.addObject("message", "The achievement was updated successfully");
        	}else {
        		result = showAchievements(new HashMap<>());
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
            result = showAchievements(new HashMap<>());
            result.addObject("message", "Jugador creado satisfactoriamente");

        }
        return result;
    }



}
