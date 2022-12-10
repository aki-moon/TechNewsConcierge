package aki.moon.TechNewsConcierge;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

@WebMvcTest(ArticleController.class)
public class ArticleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleRepository articleRepository;

    @Test
    public void articleTest1() throws Exception {
        Article expect = new Article("200",  "2022-12-10", "title", "description", "https://www.example.com/blog/999");
        when(articleRepository.findById("200")).thenReturn(expect);
        mockMvc.perform(get("/article/200")).andExpect(status().isOk()).andExpect(content().string(containsString("title")));
    }

    @Test
    public void articleTest2() throws Exception {
        mockMvc.perform(get("/article/100")).andExpect(status().isNotFound()).andExpect(content().string(containsString("該当の記事がありませんでした")));
    }
}