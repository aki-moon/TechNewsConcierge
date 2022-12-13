package aki.moon.TechNewsConcierge;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArticleController {
    private DSLContext create;

    @Autowired
    public ArticleController(DSLContext dslContext) {
        this.create = dslContext;
    }

    @GetMapping("/article/{id}")
    public ModelAndView article(Model model, @PathVariable String id) {
        ArticleRepository articleRepository = new ArticleRepository(create);
        Article article = articleRepository.findById(id);

        ModelAndView modelAndView = new ModelAndView();
        if (article == null) {
            modelAndView.setViewName("article/error");
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
        } else {
            modelAndView.setViewName("article/article");
            model.addAttribute("article", article);
        }

        return modelAndView;
    }

}