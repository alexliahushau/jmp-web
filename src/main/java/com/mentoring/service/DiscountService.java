package com.mentoring.service;

import java.time.LocalDateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.mentoring.domain.Event;
import com.mentoring.domain.User;

/**
 * @author Yuriy_Tkach
 */
public interface DiscountService {

    byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets);

}
