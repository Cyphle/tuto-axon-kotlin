package com.example.axon.book

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class RegisterBookCommand(
        @TargetAggregateIdentifier
        val libraryId: Int,
        val isbn: String,
        val title: String
)