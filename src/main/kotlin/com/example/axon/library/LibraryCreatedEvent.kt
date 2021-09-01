package com.example.axon.library

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class LibraryCreatedEvent(
        @TargetAggregateIdentifier
        val libraryId: Int,
        val name: String
)