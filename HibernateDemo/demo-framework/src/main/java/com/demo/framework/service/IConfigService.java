package com.demo.framework.service;

import com.demo.framework.model.Config;
import java.util.List;
import java.util.Map;

public interface IConfigService extends BaseService<Config> {

	public List<Config> getBySystemFlag(String systemFlag);

	public List<Config> getBySystemFlag(String systemFlag, String... keys);

	public Map<String, String> getKeyValueMapBySystemFlag(String systemFlag);

	public Map<String, String> getKeyValueMapBySystemFlag(String systemFlag, String... keys);

	public Map<String, String> initConfig(String systemFlag);

	public void initConfig(Map<String, String> map, String systemFlag);

	public void updateConfig(Map<String, String> map, String systemFlag, Long userId, String userName, Long orgId);

}
