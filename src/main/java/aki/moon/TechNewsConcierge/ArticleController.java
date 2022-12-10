package aki.moon.TechNewsConcierge;

import aki.moon.TechNewsConcierge.tables.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.jooq.DSLContext;

import java.util.List;
import java.util.stream.Collectors;


@Controller
public class ArticleController {
    private DSLContext create;

    @Autowired
    public ArticleController(DSLContext dslContext){
        this.create = dslContext;
    }

    @GetMapping("/article/{id}")
    public String article(Model model, @PathVariable String id) {
        Article article = Article.ARTICLE;
        List<aki.moon.TechNewsConcierge.Article> selected =create.select(Article.ARTICLE.asterisk()).from(article).fetchInto(aki.moon.TechNewsConcierge.Article.class);

        aki.moon.TechNewsConcierge.Article article2 = selected.stream().map(articleVo -> {
            aki.moon.TechNewsConcierge.Article article1 = new aki.moon.TechNewsConcierge.Article(articleVo.id(), articleVo.publishDate(), articleVo.title(), articleVo.description(), articleVo.link());
            return article1;
        }).collect(Collectors.toList()).get(0);

        model.addAttribute("article", article2);

        return "article/article";
    }

}