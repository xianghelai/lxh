package com.lai.redisdemo.RedisUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

/**
 * @Author: bbdw
 * @Date: 2019/7/16 15:25
 * @Version 1.0
 */
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;
    //如果使用RedisTemplate需要更改序列化方式
    public void tt(){
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer );
        redisTemplate.setValueSerializer(stringSerializer );
        redisTemplate.setHashKeySerializer(stringSerializer );
        redisTemplate.setHashValueSerializer(stringSerializer );
    }

    /**
     * 批量删除对应的value
     * @param keys
     */
    public void remove(final String... keys){
        for (String key:keys){
            remove(key);//递归
        }
    }

    /**
     * 批量删除key
     * @param pattern
     */
    public void removePattern(final String pattern){
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }
    public void remove(final String key){
        if(exists(key)){
            redisTemplate.delete(key);
        }
    }
    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * String类型取
     * 根据 key 获取对应的value 如果key不存在则返回null
     * @param key
     * @return
     */
    public String get(final String key){
        Object result = null;
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result.toString();
    }

    /**
     * String 类型存 如果key不存在添加key 保存值为value 如果key存在则对value进行覆盖
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key,Object value){
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key,value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * map类型 存
     * 为散列添加多个value值
     * @param key
     * @param value
     * @return
     */
    public boolean hmset(String key, Map<String,String> value){
        Boolean result = false;
        try {
            HashOperations hashOperations = redisTemplate.opsForHash();
            hashOperations.putAll(key,value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * map类型 取
     * 获取散列的key value键值对集合
     * @param key
     * @return
     */
    public Map<String,String > hmget(String key){
        Map<String, String> result = null;
        HashOperations hashOperations = redisTemplate.opsForHash();
        result = hashOperations.entries(key);
        return result;
    }

    /**
     * list 类型 存
      * @param key
     * @param list
     * @return
     */
    public boolean setList(String key, List<String> list){
        boolean result = false;
        try {
            ListOperations listOperations = redisTemplate.opsForList();
            listOperations.leftPushAll(key,list);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * list 类型 取
     * @param key
     * @param index
     * @return
     */
    public String getList(final String key ,Long index){
        Object result = null;
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ListOperations listOperations = redisTemplate.opsForList();
        //获取指定下标数据   使用range（0  -1）可以获取所有数据
        result = listOperations.index(key ,index);
        //result = listOperations.range(key ,0,-1);
        if(result == null){
            return null;
        }
        return result.toString();
    }
    /**
     * list 类型 取
     * @param key
     * @param
     * @return
     */
    public String getListAll(final String key ){
        Object result = null;
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ListOperations listOperations = redisTemplate.opsForList();
        //获取指定下标数据   使用range（0  -1）可以获取所有数据
        result = listOperations.range(key ,0,-1);
        if(result == null){
            return null;
        }
        return result.toString();
    }
    /**
     * set类型 存
     * @param key
     * @param set
     * @return
     */
    public boolean setSet(String key,Set<String> set){
        boolean result = false;
        try {
            SetOperations setOperations = redisTemplate.opsForSet();
            setOperations.add(key,set.toString());
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * set类型 取
     * @param key
     * @return
     */
    public String getSet(final String key){
        Set members = null;
        try {
            SetOperations setOperations = redisTemplate.opsForSet();
            members = setOperations.members(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return members.toString();
    }

    /**
     * zset类型 存
     * 给有序集合添加一个指定分数的成员 如果成员存在则覆盖
     * @param key
     * @param value
     * @param score 分数
     * @return
     */
    public boolean setzSet(String key,String value,double score){
        Boolean result = null;
        try {
            ZSetOperations zSetOperations = redisTemplate.opsForZSet();
            zSetOperations.add(key,value,score);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Set<String> getZSet(String key,long start,long end){
        Set result= null;
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ZSetOperations zsetOperations = redisTemplate.opsForZSet();
        ////获取有序集合中指定分数范围内的成员集合  获取 value 分数
        //result =zsetOperations.rangeWithScores(key,start,end);
        //从有序集合中获取指定范围内从高到低的成员集合 获取 value
        //result = zsetOperations.reverseRange(key ,start ,end);
        //指定范围内   （0 -1）返回所有。
        result = zsetOperations.range(key ,start ,end);
        if(result == null){
            return null;
        }
        Iterator iterator = result.iterator();
        Set<String> zSetValList = new HashSet<>();
        while (iterator.hasNext()){
            //使用rangeWithScores 方法获取的
//            DefaultTypedTuple next = (DefaultTypedTuple)iterator.next();
//            System.out.println();
//            System.out.println(next.getScore());
//            System.out.println(next.getValue());

            //使用reverseRange 方法获取
            String next = (String)iterator.next();
            zSetValList.add(next);
        }
        return result;
    }
}
