package com.example.axon.library

import com.example.axon.book.BookCreatedEvent
import com.example.axon.book.RegisterBookCommand
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate


@Aggregate
data class Library(
        @AggregateIdentifier
        var libraryId: Int? = null,
        var name: String? = null,
        var isbnBooks: MutableList<String>? = null
) {
    constructor() : this(null, null, mutableListOf())

    @CommandHandler
    fun handleRegisterLibraryCommand(cmd: RegisterLibraryCommand) {
        val toto = ""
        AggregateLifecycle.apply(LibraryCreatedEvent(cmd.libraryId, cmd.name))
    }

    @CommandHandler
    fun handleAddBook(cmd: RegisterBookCommand) {
        AggregateLifecycle.apply(BookCreatedEvent(cmd.libraryId, cmd.isbn, cmd.title))
    }

    @EventSourcingHandler
    private fun handleCreatedEvent(event: LibraryCreatedEvent) {
        libraryId = event.libraryId
        name = event.name
        isbnBooks = ArrayList()
    }

    @EventSourcingHandler
    private fun handleBookAddedEvent(event: BookCreatedEvent) {
        isbnBooks
                ?.add(event.isbn)
                ?: run {
                    isbnBooks = mutableListOf(event.isbn)
                }
    }
}