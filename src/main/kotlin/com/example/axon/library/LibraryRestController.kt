package com.example.axon.library

import com.example.axon.book.BookBean
import com.example.axon.book.GetBooksQuery
import com.example.axon.book.RegisterBookCommand
import org.axonframework.commandhandling.callbacks.LoggingCallback
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

data class LibraryBean(
    val libraryId: Int,
    val name: String
)

@RestController
class LibraryRestController @Autowired constructor(private val commandGateway: CommandGateway, private val queryGateway: QueryGateway) {
    @PostMapping("/api/library")
    fun addLibrary(@RequestBody library: LibraryBean): String {
        commandGateway.sendAndWait<Any>(RegisterLibraryCommand(library.libraryId, library.name))
        return "Saved"
    }

    @GetMapping("/api/library/{library}")
    fun getLibrary(@PathVariable library: Int?): Library {
        return queryGateway.query(GetLibraryQuery(library!!), Library::class.java).get()
    }

    @PostMapping("/api/library/{library}/book")
    fun addBook(@PathVariable library: Int?, @RequestBody book: BookBean): String {
        commandGateway.send(RegisterBookCommand(library!!, book.isbn!!, book.title!!),
                LoggingCallback.INSTANCE)
        return "Added"
    }

    @GetMapping("/api/library/{library}/book")
    fun addBook(@PathVariable library: Int?): List<BookBean> {
        return queryGateway.query(GetBooksQuery(library!!), ResponseTypes.multipleInstancesOf(BookBean::class.java)).get()
    }
}
