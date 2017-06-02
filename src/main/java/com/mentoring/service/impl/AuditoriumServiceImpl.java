package com.mentoring.service.impl;

import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mentoring.dao.AuditoriumDao;
import com.mentoring.domain.Auditorium;
import com.mentoring.service.AuditoriumService;

/**
 * @author Aliaksandr_Liahushau
 */
@Service
public class AuditoriumServiceImpl implements AuditoriumService {

	@Inject
	private AuditoriumDao auditoriumDao;
	
	@Override
	public Set<Auditorium> getAll() {
		return auditoriumDao.getAll();
	}

	@Override
	public Auditorium getByName(String name) {
		return auditoriumDao.getByName(name);
	}

}
