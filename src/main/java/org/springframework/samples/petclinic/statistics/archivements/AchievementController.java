package org.springframework.samples.petclinic.statistics.archivements;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/statistics/achievements")
public class AchievementController {
    private final String ACHIEVEMENTS_LISTING_VIEW="achievements/AchievementsListing";
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


}
