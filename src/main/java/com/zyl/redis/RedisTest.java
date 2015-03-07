package com.zyl.redis;

import redis.clients.jedis.Jedis;

public class RedisTest {
	public static void main(String[] args) {
		// RedisUtil.getPool().destroy();  
		Jedis jedis = RedisUtil.getJedis();  
        System.out.println(jedis.select(0));// select db-index  
        // 通过索引选择数据库，默认连接的数据库所有是0,默认数据库数是16个。返回1表示成功，0失败  
        System.out.println(jedis.dbSize());// dbsize 返回当前数据库的key数量  
	}
}
