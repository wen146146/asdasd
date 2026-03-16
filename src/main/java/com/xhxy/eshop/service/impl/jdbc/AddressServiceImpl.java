package com.xhxy.eshop.service.impl.jdbc;

import java.util.List;

import com.xhxy.eshop.dao.AddressDao;
import com.xhxy.eshop.dao.impl.AddressDaoImpl;
import com.xhxy.eshop.entity.Address;
import com.xhxy.eshop.service.AddressService;

public class AddressServiceImpl implements AddressService {

	private AddressDao addressDao = new AddressDaoImpl();
	
	@Override
	public List<Address> findByUserId(Integer userId) {
		return addressDao.findByUserId(userId);
	}
	
	@Override
	public Address findById(Integer id) {
		return addressDao.findById(id);
	}
	
	// 为某个用户增加收货地址
	@Override
	public int add(Address address) {
		return addressDao.add(address);
	}
	// 更新某个收货地址
	@Override
	public int update(Address address) {
		return addressDao.update(address);
	}
	// 删除某个收货地址
	@Override
	public int delete(Integer id) {
		return addressDao.delete(id);
	}


}
