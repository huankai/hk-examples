/**
 *
 * rabbitmq 消息确认机制
 *  在 rabbitmq 中，可以通过持久化数据解决 rabbitmq 服务器数据丢失问题，
 *  但生产者将消息发送出去之后，消息到底有没有到达 rabbitmq 服务器，默认情况下是不知道的，
 *  可以通过如下两方式实现：
 *
 * 一、事务模式
 *  txSelect();
 *
 *  txCommit();
 *
 *  txRollback();
 *
 * 二、confirm 模式
 *
 *
 */

package com.hk.rabbit.transation;