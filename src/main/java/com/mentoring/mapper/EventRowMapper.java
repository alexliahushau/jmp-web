package com.mentoring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;

import com.mentoring.domain.Event;
import org.springframework.jdbc.core.RowMapper;

import com.mentoring.domain.EventRating;

public class EventRowMapper implements RowMapper<Event> {

    @Override
    public Event mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Long eventId = rs.getLong("EVENT_ID");
        final String name = rs.getString("NAME");
        final Double basePrice = rs.getDouble("BASE_PRICE");
        final EventRating rating = EventRating.valueOf(rs.getString("RATING"));
        final Event event = new Event();
        
        event.setId(eventId);
        event.setName(name);
        event.setBasePrice(basePrice);
        event.setRating(rating);
        event.setAirDates(new TreeSet<>());
        
        return event;
    }
}
