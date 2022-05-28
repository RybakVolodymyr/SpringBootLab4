package com.rybak.spring.labs.lab4.service;

import com.rybak.spring.labs.lab4.jpa.entity.Author;
import com.rybak.spring.labs.lab4.jpa.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TransactionService {

    private AuthorRepository repository;

    private ApplicationContext context;

    @Transactional(propagation = Propagation.REQUIRED)
    public void supportsOuterRequired() {
        Author author = Author.builder().name("Andriy").build();

        repository.save(author);
        context.getBean(TransactionService.class).supports();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void supports() {
        Author author = Author.builder().name("Olexandr").build();

        repository.save(author);

        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void mandatory() {
        Author author = Author.builder().name("Volodymyr").build();

        repository.save(author);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void nestedOuterRequired() {
        Author author1 = Author.builder().name("Olexiy").build();

        repository.save(author1);
        try {
            context.getBean(TransactionService.class).nested();
        } catch (RuntimeException e) {

        }
        Author author2 = Author.builder().name("Vadym").build();

        repository.save(author2);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void nested() {
        Author author = Author.builder().name("Sasha").build();

        repository.save(author);

        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.NEVER)
    public void never() {
        Author author = Author.builder().name("Petro").build();

        repository.save(author);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void notSupportedOuterRequired() {
        Author author1 = Author.builder().name("Larisa").build();

        repository.save(author1);

        try {
            notSupported();
        } catch (RuntimeException e) {
        }

        Author author2 = Author.builder().name("Kyrill").build();

        repository.save(author2);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void notSupported() {
        Author author = Author.builder().name("Sergey").build();

        repository.save(author);

        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void requiresNewOuterRequired() {
        Author author1 = Author.builder().name("Lera").build();

        repository.save(author1);
        try {
            context.getBean(TransactionService.class).requiresNew();
        } catch (RuntimeException e) {

        }
        Author author2 = Author.builder().name("Ivan").build();

        repository.save(author2);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiresNew() {
        Author author = Author.builder().name("Oleg").build();

        repository.save(author);

        throw new RuntimeException();
    }
}
