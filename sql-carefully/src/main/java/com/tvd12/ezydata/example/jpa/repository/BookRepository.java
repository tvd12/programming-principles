package com.tvd12.ezydata.example.jpa.repository;

import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezydata.example.jpa.entity.Book;
import com.tvd12.ezydata.example.jpa.result.SumBookPriceResult;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;
import com.tvd12.ezyfox.util.Next;

import java.math.BigInteger;
import java.util.List;

@EzyRepository
public interface BookRepository extends EzyDatabaseRepository<Long, Book> {

    Book findByNameAndAuthorId(String name, Long authorId);

    @EzyQuery("select e from Book e")
    List<Book> findBooks(Next next);

    @EzyQuery("select e from Book e order by e.price desc, e.id desc")
    List<Book> findBooksOrderByPriceAndId(Next next);

    @EzyQuery(
        value = "select * from book e " +
            "where e.price > ?0 or (e.price = ?0 and e.id > ?1) " +
            "order by e.price desc, e.id desc",
        nativeQuery = true
    )
    List<Book> findBooks(
        BigInteger priceExclusive,
        long idExclusive,
        Next next
    );

    @EzyQuery("select e from Book e where e.name < ?0 order by e.name")
    List<Book> findByNameLt(String name, Next next);

    @EzyQuery("select e from Book e where e.name > ?0 order by e.name")
    List<Book> findByNameGt(String name, Next next);

    @EzyQuery(value = "select sum(e.price) as sum from Book e", nativeQuery = true)
    SumBookPriceResult sumPrice();
}