package com.engineeringDigest.journalApp.services;

import java.security.cert.Extension;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import com.engineeringDigest.journalApp.entity.User;

public class UserArgumentsProvides implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

        return Stream.of(Arguments.of(User.builder().userName("pavneet").password("pavneet").build()),
                Arguments.of(User.builder().userName("shubham").password("").build()));
    }
}
