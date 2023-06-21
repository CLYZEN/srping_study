package com.example.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.company.dao.CompanyDao;
import com.example.company.dto.Commute;
import com.example.company.dto.Emp;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CompanyDao companyMapper;

	@Override
	public Emp findCommon(int empno) {
		
		return companyMapper.findCommon(empno);
	}

	@Override
	public Commute findComeOut(int empno) {
		return companyMapper.findComeOut(empno);
	}

	@Override
	public void checkIn(int empno) {
		companyMapper.checkIn(empno);
		
	}

	@Override
	public void checkOut(int empno) {
		companyMapper.checkOut(empno);
		
	}

}
