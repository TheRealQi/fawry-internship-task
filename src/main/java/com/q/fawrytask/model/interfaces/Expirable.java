package com.q.fawrytask.model.interfaces;

import java.time.LocalDate;

public interface Expirable {
    boolean isExpired();
    LocalDate getExpirationDate();
}
