/**
 * 
 */
package com.wx.wxsecuritydemo.service.impl;

import com.wx.wxsecuritydemo.service.HelloService;
import org.springframework.stereotype.Service;


/**
 * @author zhailiang
 *
 */
@Service
public class HelloServiceImpl implements HelloService {

	/* (non-Javadoc)
	 * @see com.imooc.service.HelloService#greeting(java.lang.String)
	 */
	@Override
	public String greeting(String name) {
		System.out.println("greeting");
		return "hello "+name;
	}

}
