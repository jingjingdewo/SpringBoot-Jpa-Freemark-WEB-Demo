package com.demo.framework.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @Author 静畏人心
 * @Date 2020/4/10 0:14
 * @Description 注入类
 */
public class BaseEntityManagerFactory {

	@PersistenceContext()
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}