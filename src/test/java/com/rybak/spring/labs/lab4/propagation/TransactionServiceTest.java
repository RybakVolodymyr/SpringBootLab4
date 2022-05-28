package com.rybak.spring.labs.lab4.propagation;

import com.rybak.spring.labs.lab4.jpa.entity.Author;
import com.rybak.spring.labs.lab4.jpa.repository.AuthorRepository;
import com.rybak.spring.labs.lab4.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Profile("testcontainers")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TransactionServiceTest {

    @Autowired
    public TransactionService transactionService;

    @Autowired
    public PlatformTransactionManager transactionManager;
    @Autowired
    public AuthorRepository authorRepository;

    @Test
    public void TestImperativeTransaction() {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

        try {
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    Author author1 = Author.builder().name("Yura").build();

                    authorRepository.save(author1);
                    throw new RuntimeException();
                }
            });
        } catch (Exception e) {

        }
        List<Author> authors1 = authorRepository.findAuthorByName("Yura");
        assertEquals(0, authors1.size());
    }

    @Test
    @Transactional
    public void TestMandatorySuccess() {
        transactionService.mandatory();

        List<Author> authors = authorRepository.findAuthorByName("Volodymyr");
        assertEquals(1, authors.size());
        assertEquals("Volodymyr", authors.get(0).getName());
    }

    @Test
    public void TestMandatoryWithException() {
        assertThrows(IllegalTransactionStateException.class, () -> transactionService.mandatory());

        List<Author> authors = authorRepository.findAuthorByName("Volodymyr");
        assertEquals(0, authors.size());
    }

    @Test
    public void TestNeverSuccess() {
        transactionService.never();

        List<Author> authors = authorRepository.findAuthorByName("Petro");
        assertEquals(1, authors.size());
        assertEquals("Petro", authors.get(0).getName());
    }

    @Test
    @Transactional
    public void TestNeverWithException() {
        assertThrows(IllegalTransactionStateException.class, () -> transactionService.never());

        List<Author> authors = authorRepository.findAuthorByName("Petro");
        assertEquals(0, authors.size());
    }

    @Test
    public void TestNotSupported() {
        transactionService.notSupportedOuterRequired();
        List<Author> authors1 = authorRepository.findAuthorByName("Larisa");
        assertEquals(1, authors1.size());
        List<Author> authors2 = authorRepository.findAuthorByName("Kyrill");
        assertEquals(1, authors2.size());
    }

    @Test
    public void TestSupportsWithOuterTransaction() {
        assertThrows(RuntimeException.class, () -> transactionService.supportsOuterRequired());

        List<Author> authors1 = authorRepository.findAuthorByName("Andriy");
        assertEquals(0, authors1.size());
        List<Author> authors2 = authorRepository.findAuthorByName("Olexandr");
        assertEquals(0, authors2.size());
    }

    @Test
    public void TestSupportsWithoutOuterTransaction() {
        assertThrows(RuntimeException.class, () -> transactionService.supports());

        List<Author> authors1 = authorRepository.findAuthorByName("Olexandr");
        assertEquals(1, authors1.size());
    }

    @Test
    public void TestRequiresNewWithOuterTransaction() {
        transactionService.requiresNewOuterRequired();

        List<Author> authors1 = authorRepository.findAuthorByName("Lera");
        assertEquals(1, authors1.size());

        List<Author> authors2 = authorRepository.findAuthorByName("Ivan");
        assertEquals(1, authors2.size());
    }

    @Test
    public void TestRequiresNewWithoutOuterTransaction() {
        assertThrows(RuntimeException.class, () -> transactionService.requiresNew());

        List<Author> authors1 = authorRepository.findAuthorByName("Oleg");
        assertEquals(0, authors1.size());

    }

    @Test
    public void TestNestedWithoutOuterTransaction() {
        assertThrows(RuntimeException.class, () -> transactionService.nested());

        List<Author> authors1 = authorRepository.findAuthorByName("Sasha");
        assertEquals(0, authors1.size());

    }

    @Test
    public void TestNestedWithOuterTransaction() {
        transactionService.nestedOuterRequired();

        List<Author> authors1 = authorRepository.findAuthorByName("Olexiy");
        assertEquals(1, authors1.size());

        List<Author> authors2 = authorRepository.findAuthorByName("Vadym");
        assertEquals(1, authors2.size());

        List<Author> authors3 = authorRepository.findAuthorByName("Sasha");
        assertEquals(0, authors3.size());
    }
}
