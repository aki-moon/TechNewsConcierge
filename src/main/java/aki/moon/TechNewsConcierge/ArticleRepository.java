package aki.moon.TechNewsConcierge;

import org.jooq.DSLContext;
import static aki.moon.TechNewsConcierge.tables.Article.ARTICLE;

import java.util.List;

public class ArticleRepository {
    private DSLContext create;
    public ArticleRepository(DSLContext dslContext){
        this.create = dslContext;
    }

    public Article findById(String id) {
        List<Article> selected =create.select(ARTICLE.asterisk())
                .from(ARTICLE)
                .where(ARTICLE.ID.eq(Integer.valueOf(id)))
                .fetchInto(aki.moon.TechNewsConcierge.Article.class);
        if (selected.isEmpty()) {
            return null;
        }
        return selected.stream().map(article -> new Article(article.id(), article.publishDate(), article.title(), article.description(), article.link())).toList().get(0);
    }
}
