package com.redbee.offices.config

import org.springframework.context.annotation.Configuration
//import org.springframework.data.redis.cache.RedisCacheConfiguration
//import org.springframework.data.redis.cache.RedisCacheManager
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
//import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
//@EnableCaching
class AppConfig(
  private val traceSleuthInterceptor: TraceSleuthInterceptor): WebMvcConfigurer {

  override fun addInterceptors(registry: InterceptorRegistry) {
    registry.addInterceptor(traceSleuthInterceptor)
  }

//  @Bean
//  fun cacheConfiguration(@Value("\${offers.cache.ttl}") ttl:Long): RedisCacheConfiguration? {
//    return RedisCacheConfiguration.defaultCacheConfig()
//      .entryTtl(Duration.ofMinutes(ttl))
//      .disableCachingNullValues()
//      .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(GenericJackson2JsonRedisSerializer()))
//  }

//  @Bean
//  fun redisCacheManagerBuilderCustomizer(
//    @Value("\${offers.brands.cache.key-prefix}") keyPrefix:String,
//    @Value("\${offers.brands-list.cache.key-prefix}") brandsListPrefix:String,
//    @Value("\${offers.words.cache.key-prefix}") wordsPrefix:String,
//    @Value("\${offers.words-list.cache.key-prefix}") wordsListPrefix:String,
//    @Value("\${offers.words-exists.cache.key-prefix}") wordsExistsPrefix:String,
//
//  ): RedisCacheManagerBuilderCustomizer? {
//    return RedisCacheManagerBuilderCustomizer { builder: RedisCacheManager.RedisCacheManagerBuilder ->
//      listOf(keyPrefix, brandsListPrefix, wordsPrefix, wordsListPrefix, wordsExistsPrefix)
//        .forEach {
//          builder
//            .withCacheConfiguration(it,
//              RedisCacheConfiguration
//                .defaultCacheConfig()
//                .serializeValuesWith(
//                  RedisSerializationContext.SerializationPair.fromSerializer(GenericJackson2JsonRedisSerializer())))
//        }
//    }
//  }

}
