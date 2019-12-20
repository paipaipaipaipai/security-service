package org.potholes.service;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands.SetOption;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Service;

/***
 * REDIS数据结构
 * redisTemplate.opsForValue();//操作字符串
 * redisTemplate.opsForHash();//操作hash
 * redisTemplate.opsForList();//操作list(列表/队列)
 * redisTemplate.opsForSet();//操作set
 * redisTemplate.opsForZSet();//操作有序set
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /***
     * 字符串存储
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /***
     * 设置过期
     * @param key
     * @param time 时间
     * @param timeUnit 单位
     */
    public void expire(String key, Long time, TimeUnit timeUnit) {
        redisTemplate.expire(key, time, timeUnit);
    }

    /***
     * 字符串存储,设置过期时间
     * @param key
     * @param value
     * @param time 时间
     * @param timeUnit 单位
     */
    public void setEx(String key, String value, Long time, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, time, timeUnit);
    }

    /***
     * 计数
     * @param key
     */
    public void incr(String key) {
        redisTemplate.opsForValue().increment(key);
    }

    /***
     * 字符串读取
     * @param key
     * @return
     */
    public String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    /***
     * 判断KEY是否存在
     * @param key
     * @return
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /***
     * 删除
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /***
     * hash存储
     * @param key
     * @param hashKey
     * @param value
     */
    public void hashPut(String key, String hashKey, String value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /***
     * hash读取
     * @param key
     * @param hashKey
     * @return
     */
    public String hashGet(String key, String hashKey) {
        String value = null;
        if (redisTemplate.opsForHash().hasKey(key, hashKey)) {
            Object object = redisTemplate.opsForHash().get(key, hashKey);
            if (object != null) {
                value = String.valueOf(object);
            }
        }
        return value;
    }

    /***
     * 队列左进
     * @param key
     * @param value
     */
    public void leftPush(String key, String value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    /***
     * 队列右进
     * @param key
     * @param value
     */
    public void rightPush(String key, String value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    /***
     * 队列左出
     * @param key
     * @param value
     */
    public String leftPop(String key) {
        return (String) redisTemplate.opsForList().leftPop(key);
    }

    /***
     * 队列右出
     * @param key
     * @param value
     */
    public String rightPop(String key) {
        return (String) redisTemplate.opsForList().rightPop(key);
    }

    /***
     * SETNX: set if not exist
     * 如果不存在保存value并返回 true
     * 如果存在就返回 false
     * @param key
     * @param value
     */
    public Boolean setIfAbsent(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /***
     * Hash SETNX: 将哈希表key中的字段 hashKey 的值设为 value 。
     * 如果不存在保存value并返回 true
     * 如果存在就返回 false
     * @param key
     * @param hashKey
     * @param value
     */
    public Boolean hashSetIfAbsent(String key, String hashKey, String value) {
        return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
    }

    /***
     * GETSET: GET和SET
     * 首先会GET到当前key的值并返回,然后再设置Key的当前Value
     * @param key
     * @param value
     */
    public String getAndSet(String key, String value) {
        String oldvalue = null;
        if (hasKey(key)) {
            oldvalue = (String) redisTemplate.opsForValue().getAndSet(key, value);
        }
        return oldvalue;
    }

    public Boolean executeSetIfAbsent(String key, String value) {
        Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                // TODO Auto-generated method stub
                return connection.set(key.getBytes(), value.getBytes(), Expiration.seconds(30),
                        SetOption.SET_IF_ABSENT);
            }
        });
        return result;
    }

    public Boolean executeEval(String key, String value) {
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end ");
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<Boolean>();
        redisScript.setScriptText(sb.toString());
        redisScript.setResultType(Boolean.class);
        Boolean result = redisTemplate.execute(redisScript, Collections.singletonList(key), value);
        return result;
    }

}
