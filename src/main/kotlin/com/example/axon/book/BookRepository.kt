package com.example.axon.book

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class BookEntity(
        @Id
        var libraryId: Int? = null,
        @Column
        var isbn: String? = null,
        @Column
        var title: String? = null
)

@Repository
interface BookRepository : CrudRepository<BookEntity, String> {
    fun findByLibraryId(libraryId: Int): List<BookEntity>
}
