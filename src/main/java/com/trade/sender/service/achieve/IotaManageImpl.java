package com.trade.sender.service.achieve;

import com.quqian.framework.service.ServiceFactory;
import com.quqian.framework.service.ServiceResource;
import com.trade.sender.entity.TradeInfo;
import com.trade.sender.entity.WithdrawInfo;
import com.trade.sender.service.AbstractManageService;
import com.trade.sender.service.IotaManage;

public class IotaManageImpl extends AbstractManageService implements IotaManage {

	private IotaManageImpl(ServiceResource serviceResource) {
		super(serviceResource);
	}

	public static class IotaManageFactory implements ServiceFactory<IotaManage> {
		@Override
		public IotaManage newInstance(ServiceResource serviceResource) {
			return new IotaManageImpl(serviceResource);
		}
	}


	@Override
	public void transToHotWallet(TradeInfo tradeInfo) throws Exception {

	}

	@Override
	public void transToColdWallet() throws Exception {

	}

	@Override
	public TradeInfo[] getTransInfos() throws Exception {
		return new TradeInfo[0];
	}

	@Override
	public void createAccount() throws Exception {

	}

	@Override
	public WithdrawInfo[] getWithdrawInfos() throws Exception {
		return new WithdrawInfo[0];
	}

	@Override
	public void withdraw(WithdrawInfo info) throws Exception {

	}

}
