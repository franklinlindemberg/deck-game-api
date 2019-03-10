package com.franklin.deck.utils;

import com.franklin.deck.exceptions.UnauthorizedException;

public class Authorization {
    public static void validate(String token) throws UnauthorizedException {
        if (!token.equals("deck")) {
            throw new UnauthorizedException("Invalid token header. The correct token is deck");
        }
    }
}
