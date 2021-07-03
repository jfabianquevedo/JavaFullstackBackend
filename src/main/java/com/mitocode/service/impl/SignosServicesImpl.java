package com.mitocode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mitocode.model.Signos;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.ISignosRepo;
import com.mitocode.service.ISignosServices;

@Service
public class SignosServicesImpl extends CRUDImpl<Signos, Integer> implements ISignosServices {

	@Autowired
	private ISignosRepo repo;

	@Override
	protected IGenericRepo<Signos, Integer> getRepo() {
		return repo;
	}

	@Override
	public Page<Signos> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}

}
