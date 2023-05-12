package com.tvd12.ezydata.example.jpa.service;

import com.tvd12.ezydata.example.jpa.converter.DataToEntityConverter;
import com.tvd12.ezydata.example.jpa.converter.EntityToDataConverter;
import com.tvd12.ezydata.example.jpa.data.AddAuthorData;
import com.tvd12.ezydata.example.jpa.data.AuthorData;
import com.tvd12.ezydata.example.jpa.entity.Author;
import com.tvd12.ezydata.example.jpa.exception.AuthorNotFoundException;
import com.tvd12.ezydata.example.jpa.repository.AuthorRepository;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;

@EzySingleton
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final DataToEntityConverter dataToEntityConverter;
    private final EntityToDataConverter entityToDataConverter;

    public AuthorData saveAuthor(AddAuthorData data) {
        final Author entity = dataToEntityConverter.toEntity(data);
        authorRepository.save(entity);
        return entityToDataConverter.toData(entity);
    }

    public AuthorData getAuthor(long authorId) {
        final Author entity = authorRepository.findById(authorId);
        if (entity == null) {
            throw new AuthorNotFoundException("not found author with id: " + authorId);
        }
        return entityToDataConverter.toData(entity);
    }
}
