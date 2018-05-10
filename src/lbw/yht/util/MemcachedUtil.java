package lbw.yht.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemcachedUtil {
	private static MemCachedClient cachedClient = new MemCachedClient();

	/**
	 * 初始化连接池
	 */
	static {
		// 解析属性文件
		Properties prop = new Properties();
		InputStream is = null;
		try {
			is = MemcachedUtil.class.getResourceAsStream("/cacheconfig.properties");
			prop.load(is);

			// 获取连接池的实例
			SockIOPool pool = SockIOPool.getInstance();

			String[] servers = prop.getProperty("servers").split(",");
			String[] ws = prop.getProperty("weights").split(",");
			Integer[] weights = new Integer[ws.length];
			for (int i = 0; i < ws.length; i++) {
				weights[i] = Integer.parseInt(ws[i]);
			}
			// 设置服务器信息
			pool.setServers(servers);
			pool.setWeights(weights);

			// 设置初始连接数、最小连接数、最大连接数、最大处理时间
			pool.setInitConn(Integer.parseInt(prop.getProperty("initConn")));
			pool.setMinConn(Integer.parseInt(prop.getProperty("minConn")));
			pool.setMaxConn(Integer.parseInt(prop.getProperty("maxConn")));
			pool.setMaxIdle(Integer.parseInt(prop.getProperty("maxIdle")));

			// 设置连接池守护线程的睡眠时间
			pool.setMaintSleep(Integer.parseInt(prop.getProperty("maintSleep")));

			// 设置TCP参数，连接超时
			pool.setNagle(false);
			pool.setSocketTO(Integer.parseInt(prop.getProperty("socketTO")));
			pool.setSocketConnectTO(Integer.parseInt(prop
					.getProperty("socketConnectTO")));
			// 初始化并启动连接池
			pool.initialize();

			// 压缩设置，超过指定大小的都压缩
			// cachedClient.setCompressEnable(true);
			// cachedClient.setCompressThreshold(1024*1024);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
	}

	private MemcachedUtil() {
	}

	public static boolean add(String key, Object value) {
		return cachedClient.add(key, value);
	}

	public static boolean add(String key, Object value, Integer expire) {
		return cachedClient.add(key, value, expire);
	}

	public static boolean put(String key, Object value) {
		return cachedClient.set(key, value);
	}

	public static boolean put(String key, Object value, Integer expire) {
		return cachedClient.set(key, value, expire);
	}

	public static boolean replace(String key, Object value) {
		return cachedClient.replace(key, value);
	}

	public static boolean replace(String key, Object value, Integer expire) {
		return cachedClient.replace(key, value, expire);
	}

	public static Object get(String key) {
		return cachedClient.get(key);
	}

}
