package com.example.axon.book

import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Service

@Service
class BookRepositoryProjector(private val bookRepository: BookRepository) {
    @EventHandler
    fun addBook(event: BookCreatedEvent) {
        val book = BookEntity()
        book.isbn = event.isbn
        book.libraryId = event.libraryId
        book.title = event.title
        bookRepository.save(book)
    }

    @QueryHandler
    fun getBooks(query: GetBooksQuery): List<BookBean> {
        return bookRepository
                .findByLibraryId(query.libraryId)
                .map {
                    BookBean(
                            it.isbn,
                            it.title
                    )
                }
    }
}

data class BookBean(
        var isbn: String? = null,
        var title: String? = null
)
