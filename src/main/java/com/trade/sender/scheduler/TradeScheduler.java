package com.trade.sender.scheduler;

import com.quqian.framework.config.ConfigureProvider;
import com.quqian.framework.resource.ResourceProvider;
import com.quqian.framework.service.ServiceProvider;
import com.quqian.framework.service.ServiceSession;
import com.trade.sender.entity.TradeInfo;
import com.trade.sender.service.IotaManage;

public class TradeScheduler extends Thread {

	//	protected static int EXPIRES_TOKEN_TIME = 0;
	private final ResourceProvider resourceProvider;
	private final ConfigureProvider configureProvider;
	private final ServiceProvider serviceProvider;
	private transient boolean alive = true;

	public TradeScheduler(ResourceProvider resourceProvider) {
		this.resourceProvider = resourceProvider;
		this.configureProvider = resourceProvider.getResource(ConfigureProvider.class);
		this.serviceProvider = resourceProvider.getResource(ServiceProvider.class);
	}

	@Override
	public void run() {
		while (alive) {
			try {
				//转入热钱包
				iota_rqb();

				//转入冷钱包
				iota_lqb();

				sleep(1000 * 60 * 5);
			} catch (InterruptedException e) {
				alive = false;
				break;
			}
		}
	}

	/**
	 * 转入热钱包
	 */
	private void iota_rqb() {
		try (ServiceSession serviceSession = serviceProvider.createServiceSession()) {
			IotaManage manage = serviceSession.getService(IotaManage.class);
			TradeInfo[] ls = manage.getTransInfos();
			if (ls != null) {
				for (TradeInfo l : ls) {
					serviceSession.openTransactions();
					try {
						manage.transToHotWallet(l);
						serviceSession.commit();
					} catch (Exception e) {
						e.printStackTrace();
						serviceSession.rollback();
					}
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
			resourceProvider.log(e);
		}

	}

	/**
	 * 转入冷钱包
	 */
	private void iota_lqb() {
		try (ServiceSession serviceSession = serviceProvider.createServiceSession()) {
			IotaManage manage = serviceSession.getService(IotaManage.class);
			serviceSession.openTransactions();
			try {
				manage.transToColdWallet();
				serviceSession.commit();
			} catch (Exception e) {
				e.printStackTrace();
				serviceSession.rollback();
			}
		} catch (Throwable e) {
			e.printStackTrace();
			resourceProvider.log(e);
		}
	}
}
