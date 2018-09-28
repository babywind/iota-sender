package com.trade.sender.service;

import com.quqian.framework.service.Service;
import com.trade.sender.entity.TradeInfo;
import com.trade.sender.entity.WithdrawInfo;

/**
 * @author xy
 */
public interface IotaManage extends Service {

	/**
	 * IOTA转入热钱包
	 *
	 * @param tradeInfo 用户资产同步记录
	 * @throws Exception
	 */
	void transToHotWallet(TradeInfo tradeInfo) throws Exception;

	/**
	 * IOTA转入冷钱包
	 *
	 * @throws Exception
	 */
	void transToColdWallet() throws Exception;

	/**
	 * 获取IOTA转账记录
	 *
	 * @return TradeInfo[]
	 * @throws Exception
	 */
	TradeInfo[] getTransInfos() throws Exception;

	/**
	 * 创建用户临时钱包
	 *
	 * @throws Exception
	 */
	void createAccount() throws Exception;

	/**
	 * 获取用户提币信息
	 *
	 * @return 提币信息
	 * @throws Exception
	 */
	WithdrawInfo[] getWithdrawInfos() throws Exception;

	/**
	 * 用户提币处理
	 *
	 * @param info 提币信息
	 * @throws Exception
	 */
	void withdraw(WithdrawInfo info) throws Exception;
}
