package com.example.axon.library

import org.axonframework.eventhandling.DomainEventMessage
import org.axonframework.eventsourcing.eventstore.EventStore
import org.axonframework.modelling.command.Repository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture
import java.util.stream.Collectors

@Service
class LibraryProjector(
        private val eventStore: EventStore,
        private val libraryRepository: Repository<Library>
) {
//    fun listEventsForAccount(accountNumber: Int?): List<Any> {
//        return eventStore.readEvents("$accountNumber")
//                .asStream()
//                .map { s: DomainEventMessage<*>? -> s!!.payload }
//                .collect(Collectors.toList())
//    }

    @QueryHandler
    fun getLibrary(query: GetLibraryQuery): Library {
//        val toto = eventStore.readEvents("${query.libraryId}")
//                .asStream()
//                .map { s: DomainEventMessage<*>? -> s!!.payload }
//                .collect(Collectors.toList())

        val future = CompletableFuture<Library>()
        libraryRepository.load("" + query.libraryId).execute { future.complete(it) }
        return future.get()
    }
}

data class GetLibraryQuery(
        val libraryId: Int
)