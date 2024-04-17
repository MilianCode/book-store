package com.example.repository.impl;

import com.example.exception.EntityNotFoundException;
import com.example.model.Book;
import com.example.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Book save(Book book) {
        try {
            entityManager.persist(book);
            return book;
        } catch (RuntimeException e) {
            throw new EntityNotFoundException("Error saving book: " + book);
        }
    }

    @Override
    public List<Book> findAll() {
        try {
            return entityManager.createQuery("SELECT b FROM Book b", Book.class)
                    .getResultList();
        } catch (RuntimeException e) {
            throw new EntityNotFoundException("Error retrieving all books");
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        try {
            Book book = entityManager.find(Book.class, id);
            return Optional.ofNullable(book);
        } catch (RuntimeException e) {
            throw new EntityNotFoundException("Error retrieving book by id: " + id);
        }
    }
}
