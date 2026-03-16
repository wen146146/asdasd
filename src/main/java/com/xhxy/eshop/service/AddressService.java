package com.xhxy.eshop.service;

import java.util.List;

import com.xhxy.eshop.entity.Address;

public interface AddressService {
	// 查询某个用户的全部收货地址
	public List<Address> findByUserId(Integer userId);
	
	// 查询某个收货地址
	public Address findById(Integer id);
	
	// 为某个用户增加收货地址
	public int add(Address address);
	
	// 更新某个收货地址
	public int update(Address address);
	
	// 删除某个收货地址
	public int delete(Integer id);
}
