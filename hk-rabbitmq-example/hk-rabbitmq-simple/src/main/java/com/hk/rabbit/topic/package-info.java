/**
 * 05: topic 模型，和 route 相比，队列可以使用通配符的 routeKey 接收指定交换机的消息
 * <pre>
 * # 表示多级匹配
 * * 表示一级匹配
 * 如：
 * key.#           以 key 开头的 routeKey 都能匹配，如 ：key.123 / key.123.xxx 都能匹配上
 * #.key           以 key 结尾的 routeKey 都能匹配，如 ：123.key / 123.xxx.key 都能匹配上
 * key.*           以 key 开头且key后只能有一个小数点的 routeKey 都能匹配，如 ：key.123 能匹配，key.123.xxx 不能匹配
 * #.key           以 key 结尾且key后只能有一个小数点的 routeKey 都能匹配，如 ：key.123 能匹配，key.123.xxx 不能匹配
 * </pre>
 *
 * @author huangkai
 * @date 2019-01-17 11:34
 */
package com.hk.rabbit.topic;