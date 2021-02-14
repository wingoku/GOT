package com.wingoku.gameofthrones.domain.mappers;

import com.wingoku.gameofthrones.data.network.models.BookDTO;
import com.wingoku.gameofthrones.domain.models.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDomainMapper implements DomainMapper<Book, BookDTO> {
    @Override
    public Book fromDTO(BookDTO bookDTO) {
        return new Book(bookDTO.getName(),
                        bookDTO.getIsbn(),
                        bookDTO.getAuthors(),
                        bookDTO.getNumberOfPages(),
                        bookDTO.getPublisher(),
                        bookDTO.getCountry(),
                        bookDTO.getMediaType(),
                        bookDTO.getReleased()
                );
    }

    @Override
    public BookDTO toDTO(Book book) {
        return new BookDTO(book.getName(),
                book.getIsbn(),
                book.getAuthors(),
                book.getNumberOfPages(),
                book.getPublisher(),
                book.getCountry(),
                book.getMediaType(),
                book.getReleased()
        );
    }

    @Override
    public List<Book> fromDTO(List<BookDTO> bookDTOS) {
        List<Book> list = new ArrayList<>();

        for(BookDTO dto : bookDTOS) {
            list.add(fromDTO(dto));
        }
        return list;
    }

    @Override
    public List<BookDTO> toDTO(List<Book> books) {
        List<BookDTO> list = new ArrayList<>();

        for(Book dto : books) {
            list.add(toDTO(dto));
        }
        return list;
    }
}
