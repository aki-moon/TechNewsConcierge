package aki.moon.TechNewsConcierge;

import aki.moon.TechNewsConcierge.tables.records.ArticleRecord;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;

import java.time.LocalDate;

import static aki.moon.TechNewsConcierge.tables.Article.ARTICLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@JooqTest
public class ArticleRepositoryTest {
    @Autowired
    private DSLContext dslContext;

    @Test
    public void t1() {
        Object expect = new Article("200", "2022-12-10", "title", "description", "https://www.example.com/blog/999");

        ArticleRecord articleRecord = this.dslContext.newRecord(ARTICLE);
        articleRecord.setId(200);
        articleRecord.setTitle("title");
        articleRecord.setDescription("description");
        articleRecord.setLink("https://www.example.com/blog/999");
        articleRecord.setPublishdate(LocalDate.of(2022, 12, 10));
        articleRecord.store();

        ArticleRepository repository = new ArticleRepository(dslContext);
        Object actual = repository.findById("200");
        assertEquals(expect, actual);
    }

    @Test
    public void t2() {
        ArticleRecord articleRecord = this.dslContext.newRecord(ARTICLE);
        articleRecord.setId(200);
        articleRecord.setTitle("title");
        articleRecord.setDescription("description");
        articleRecord.setLink("https://www.example.com/blog/999");
        articleRecord.setPublishdate(LocalDate.of(2022, 12, 10));
        articleRecord.store();

        ArticleRepository repository = new ArticleRepository(dslContext);
        Object actual = repository.findById("201");
        assertNull(actual);
    }
}
