package com.yingjun.ssm.common.base.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.yingjun.ssm.common.base.dao.Dao;
import com.yingjun.ssm.common.base.service.Service;
import com.yingjun.ssm.common.page.Pagination;
import com.yingjun.ssm.entity.PersistentObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.smart.mvc.dao.mybatis.Dao;
//import com.smart.mvc.exception.DaoException;
//import com.smart.mvc.model.Pagination;
//import com.smart.mvc.model.PersistentObject;
//import com.smart.mvc.service.mybatis.Service;

/**
 * Service基类，实现了数据的CRUD
 * 
 * @param <DAO>
 * @param <T>
 * @param <ID>
 * @author Joe
 */
public abstract class ServiceImpl<DAO extends Dao<T, ID>, T extends PersistentObject, ID extends Serializable>
		implements Service<T, ID> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceImpl.class);

	/**
	 * 由子类注入实体DAO
	 */
	protected DAO dao;

	public abstract void setDao(DAO dao);

	/**
	 * 查询所有分页
	 * 
	 * @param p
	 * @return
	 */
	public Pagination<T> findByAllPagination(Pagination<T> p) {
		dao.findByAll(p);
		return p;
	}

	/**
	 * 通过主键查询实体
	 * 
	 * @param
	 *            pk
	 * @return T
	 */
	public T get(ID pk) {
		return dao.get(pk);
	}

	/**
	 * 通过主键集合查询实体
	 * 
	 * @param
	 *            <PK> pks
	 * @return List<T>
	 */
	public List<T> get(Collection<ID> pks) {
		List<T> list = new ArrayList<T>(pks.size());
		for (ID pk : pks) {
			list.add(get(pk));
		}
		return list;
	}

	/**
	 * 插入/更新实体
	 * 
	 * @param
	 *            t
	 * 
	 */
	public void save(T t) {
		if (t.getId() == null) {
			dao.insert(t);
		}
		else {
			dao.update(t);
		}
	}

	/**
	 * 插入/更新实体集合
	 * 
	 * @param
	 *            <T> ts
	 */
	public void save(Collection<T> ts) {
		for (T t : ts) {
			save(t);
		}
	}

	/**
	 * 更新实体
	 * 
	 * @param
	 *            t
	 */
	public void update(T t) {
		verifyRows(dao.update(t), 1, "数据库更新失败");
	}

	/**
	 * 更新实体集合
	 * 
	 * @param
	 *            <T> ts
	 */
	public void update(Collection<T> ts) {
		for (T t : ts) {
			update(t);
		}
	}

	/**
	 * 删除实体
	 * 
	 * @param
	 *            t
	 */
	@SuppressWarnings("unchecked")
	public void delete(T t) {
		deleteById((ID) t.getId());
	}

	/**
	 * 删除实体集合
	 * 
	 * @param
	 *            <T> ts
	 */
	public void delete(Collection<T> ts) {
		for (T t : ts) {
			delete(t);
		}
	}

	/**
	 * 通过主键删除实体
	 * 
	 * @param
	 *
	 * @return T
	 */
	public void deleteById(ID id) {
		deleteById(Arrays.asList(id));
	}

	/**
	 * 通过主键集合删除实体 注：这里别把List改为Collection，会导致覆盖方法的List<ID>调用不到
	 * 
	 * @param
	 *            <PK> pks
	 * @return List<T>
	 */
	public void deleteById(List<ID> idList) {
		verifyRows(dao.deleteById(idList), idList.size(), "数据库删除失败");
	}

	/**
	 * 为高并发环境出现的更新和删除操作，验证更新数据库记录条数
	 * 
	 * @param updateRows
	 * @param rows
	 * @param message
	 */
	protected void verifyRows(int updateRows, int rows, String message) {
		if (updateRows != rows) {
			Exception e = new Exception(message);
			LOGGER.error("need update is {}, but real update rows is {}.", rows, updateRows, e);
			try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
