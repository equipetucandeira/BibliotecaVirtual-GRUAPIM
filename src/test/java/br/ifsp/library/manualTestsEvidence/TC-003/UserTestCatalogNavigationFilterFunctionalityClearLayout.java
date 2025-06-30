import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.ifsp.library.model.Book;
import br.ifsp.library.repository.BookRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
public class UserTestCatalogNavigationFilterFunctionalityClearLayout {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setup() {
        bookRepository.deleteAll();

        bookRepository.saveAll(List.of(
            new Book("Clean Code", "Um guia de boas práticas", "Robert C. Martin", 5),
            new Book("Código Limpo", "Versão em português do Clean Code", "Robert C. Martin", 2),
            new Book("1984", "Distopia clássica", "George Orwell", 3),
            new Book("O Pequeno Príncipe", "Um conto poético", "Antoine de Saint-Exupéry", 7)
        ));
    }

    @Test
    void shouldFilterBooksByAuthorSuccessfully() throws Exception {
        mockMvc.perform(get("/books")
                .param("author", "Robert")
                .param("page", "0")
                .param("size", "10")
                .param("sort", "title")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content.length()").value(2))
            .andExpect(jsonPath("$.content[0].author").value("Robert C. Martin"));
    }

    @Test
    void shouldFilterBooksByTitleSuccessfully() throws Exception {
        mockMvc.perform(get("/books")
                .param("title", "1984")
                .param("page", "0")
                .param("size", "10")
                .param("sort", "title")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content.length()").value(1))
            .andExpect(jsonPath("$.content[0].title").value("1984"));
    }

    @Test
    void shouldReturnAllBooksWhenNoFilterIsGiven() throws Exception {
        mockMvc.perform(get("/books")
                .param("page", "0")
                .param("size", "10")
                .param("sort", "title")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content.length()").value(4));
    }
}

