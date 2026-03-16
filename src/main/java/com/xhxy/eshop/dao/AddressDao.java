package com.xhxy.eshop.dao;

import java.util.List;

import com.xhxy.eshop.entity.Address;

public interface AddressDao {
	
	// 查询某个用户的所有收货地址
	public List<Address> findByUserId(Integer userId);
	
	// 查询某个收货地址
	public Address findById(Integer id);
	
	// 为某个用户增加收货地址
	public Integer add(Address address);
	
	// 更新某个收货地址
	public Integer update(Address address);
	
	// 删除某个收货地址
	public Integer delete(Integer id);
}
