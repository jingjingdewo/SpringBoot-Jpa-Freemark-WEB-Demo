package com.demo.framework.service.impl;

import com.demo.framework.dao.entity.CriteriaCondition;
import com.demo.framework.dao.entity.OrderCondition;
import com.demo.framework.dao.enums.CriteriaEnum;
import com.demo.framework.dao.enums.OrderEnum;
import com.demo.framework.model.Config;
import com.demo.framework.service.IConfigService;
import com.demo.framework.utils.PropUtil;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("iConfigService")
public class ConfigServiceImpl extends BaseServiceImpl<Config> implements IConfigService {

	@Override
	public List<Config> getBySystemFlag(String systemFlag) {
		List<CriteriaCondition> criteriaConditions = new ArrayList<>();
		criteriaConditions
				.add(CriteriaCondition.getCriteria(CriteriaEnum.Equal, "id.systemFlag", "systemFlag", systemFlag));
		List<OrderCondition> orderConditions = new ArrayList<>();
		orderConditions.add(OrderCondition.addOrder(OrderEnum.asc, "id.key"));
		return getList(criteriaConditions, orderConditions);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Config> getBySystemFlag(String systemFlag, String... keys) {
		return get("SELECT E FROM Config E WHERE E.id.systemFlag = ?1 AND E.id.key in ?2", systemFlag,
				Arrays.asList(keys));
	}

	@Override
	public Map<String, String> getKeyValueMapBySystemFlag(String systemFlag, String... keys) {
		Map<String, String> map = new HashedMap<>();
		for (Config _Config : getBySystemFlag(systemFlag, keys)) {
			map.put(_Config.getId().getKey(), _Config.getVal());
		}
		return map;
	}

	@Override
	public Map<String, String> getKeyValueMapBySystemFlag(String systemFlag) {
		Map<String, String> map = new HashedMap<>();
		for (Config _Config : getBySystemFlag(systemFlag)) {
			map.put(_Config.getId().getKey(), _Config.getVal());
		}
		return map;
	}

	@Override
	public void initConfig(Map<String, String> map, String systemFlag) {
		if (!map.isEmpty()) {
			List<Config> configs = new ArrayList<Config>();
			for (String key : map.keySet()) {
				configs.add(new Config(systemFlag, key, StringUtils.trimToEmpty(map.get(key))));
			}
			saveOrUpdate(configs);
		}
	}

	@Override
	public void updateConfig(Map<String, String> map, String systemFlag, Long userId, String userName, Long orgId) {
		for (String key : map.keySet()) {
			if (executeJpql(
					"UPDATE Config E set E.val = ?1, E.menderId = ?2, E.menderName = ?3,E.modifyTime = ?4 WHERE id.systemFlag = ?5 AND id.key = ?6",
					map.get(key), userId, userName, new Date(), systemFlag, key) < 1) {
				Config config = new Config(systemFlag, key, map.get(key));
				config.setOrganizeId(orgId);
				config.setMenderId(userId);
				config.setMenderName(userName);
				save(config);
			}
		}
	}

	@Override
	public Map<String, String> initConfig(String systemFlag) {
		Map<String, String> propConfig = getKeyValueMapBySystemFlag(systemFlag);
		if (propConfig.isEmpty()) {
			propConfig = PropUtil.getPropertyMap(systemFlag);
			initConfig(propConfig, systemFlag);
		} else {
			Map<String, String> config = PropUtil.getPropertyMap(systemFlag);
			Iterator<Map.Entry<String, String>> iterator = config.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, String> entry = iterator.next();
				if (propConfig.containsKey(entry.getKey())) {
					iterator.remove();
				} else {
					propConfig.put(entry.getKey(), entry.getValue());
				}
			}
			if (!config.isEmpty()) {
				initConfig(config, systemFlag);
			}
		}
		return propConfig;
	}
}
