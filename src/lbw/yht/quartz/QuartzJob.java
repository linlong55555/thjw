package lbw.yht.quartz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lbw.yht.adv.domain.AdvplaceRelease;
import lbw.yht.adv.service.AdvplaceReleaseService;

import org.springframework.beans.factory.annotation.Autowired;

public class QuartzJob {
	@Autowired
	private AdvplaceReleaseService advplaceReleaseService;// 广告位发布

	public void work1() {
		List<AdvplaceRelease> advplaceReleaseList = advplaceReleaseService
				.queryAll();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String now = sdf.format(new Date());
			for (AdvplaceRelease advplaceRelease : advplaceReleaseList) {// 修改广告位发布为发布结束
				String endBiddingPeriod = advplaceRelease.getEndBiddingPeriod();
				Calendar date = Calendar.getInstance();
				date.setTime(sdf.parse(endBiddingPeriod));
				date.set(Calendar.DATE, date.get(Calendar.DATE) + 1);
				String endBiddingPeriod2 = sdf.format(date.getTime());
				if (now.equals(endBiddingPeriod2)) {//
					advplaceReleaseService.modifyAdvplaReSta2(advplaceRelease
							.getAdvplaceId().toString());// 修改为发布结束
				}
			}
			System.out.println("Quartz的任务调度！！！work1启用……");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
