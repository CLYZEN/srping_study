package com.example.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.company.dao.CompanyDao;
import com.example.company.dto.CommonData;
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


	@Override
	public int getDataCount(int empno) {
		return companyMapper.getDataCount(empno);
	}

	@Override
	public List<CommonData> getCommonLists(int empno, int start, int end) {
		return companyMapper.getCommonLists(empno, start, end);
	}

	@Override
	public void deleteCommute(int empno) {
		companyMapper.deleteCommute(empno);
	}

	@Override
	public void deleteEmp(int empno) {
		companyMapper.deleteEmp(empno);
	}

	@Override
	public void insertEmployee(String name) {
		companyMapper.insertEmployee(name);
		
	}

	@Override
	public int getNowEmpno() {
		return companyMapper.getNowEmpno();
	}

	@Override
	public List<CommonData> getListForAdmin(int start, int end, String date) {
		return companyMapper.getListForAdmin(start, end,date);
	}

	@Override
	public int getDataCountForAdmin(String date) {
		return companyMapper.getDataCountForAdmin(date);
	}

	@Override
	public int getDataCountForFire() {
		return companyMapper.getDataCountForFire();
	}

	@Override
	public List<Emp> getListForFire(int start, int end) {
		return companyMapper.getListForFire(start, end);
	}



}
