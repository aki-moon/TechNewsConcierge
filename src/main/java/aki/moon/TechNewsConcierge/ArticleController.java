package aki.moon.TechNewsConcierge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.jooq.DSLContext;

@Controller
public class ArticleController {
    private DSLContext create;

    @Autowired
    public ArticleController(DSLContext dslContext){
        this.create = dslContext;
    }

    @GetMapping("/article/{id}")
    public String article(Model model, @PathVariable String id) {
        ArticleRepository articleRepository = new ArticleRepository(create);
        Article article = articleRepository.findById(id);

        model.addAttribute("article", article);

        return "article/article";
    }

}