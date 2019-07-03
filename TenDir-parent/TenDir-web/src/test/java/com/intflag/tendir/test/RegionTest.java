package com.intflag.tendir.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intflag.tendir.dao.IRegionDao;
import com.intflag.tendir.entity.Region;
import com.intflag.tendir.service.IRegionService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年5月23日 上午11:00:12
 * @Description 地区测试类
 * @version V1.0
 */
// 创建容器
@RunWith(SpringJUnit4ClassRunner.class)
// 创建容器的同时指定配置文件
@ContextConfiguration("classpath:applicationContext.xml")
public class RegionTest {

	@Resource
	private IRegionDao regionDao;

	@Resource
	private IRegionService regionService;

	@Test
	public void fun0() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Region.class);
		detachedCriteria.add(Restrictions.eq("regionLevel", 3));
		detachedCriteria.add(Restrictions.isNull("locationLat"));
		List<Region> list = regionService.findByCriteria(detachedCriteria);
		System.out.println(list.size());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void fun1() throws Exception {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Region.class);
		detachedCriteria.add(Restrictions.eq("regionLevel", 3));
		detachedCriteria.add(Restrictions.isNull("locationLat"));
		List<Region> list = regionService.findByCriteria(detachedCriteria);
		Date date1 = new Date();
		System.out.println(
				"----------------------------开始抓取，时间：" + date1.toLocaleString() + "----------------------------");
		for (int i = 0; i < list.size(); i++) {
			Region region = list.get(i);
			String province = region.getRegion().getRegion().getRegionName();
			String city = region.getRegion().getRegionName();
			String area = region.getRegionName();
			String query = city + area;
			region.setRegionFullName(province + query);
			BigDecimal[] bigDecimals = getLocation(query, city);
			if (bigDecimals != null) {
				region.setLocationLat(bigDecimals[0]);
				region.setLocationLng(bigDecimals[1]);
			}
			regionService.update(region);
			// System.out.println(region);
		}
		Date date2 = new Date();
		System.out.println(
				"----------------------------结束抓取，时间：" + date2.toLocaleString() + "----------------------------");
		Long time = date2.getTime() - date1.getTime();
		time = time / 1000;
		System.out.println("共耗时：" + time + "秒");
	}

	@Test
	public void fun2() throws Exception {
		String query = "攀枝花东区";
		String region = "攀枝花";
		getLocation(query, region);
	}

	private BigDecimal[] getLocation(String query, String region) throws Exception {
		String url = "http://api.map.baidu.com/place/v2/suggestion?query=" + query + "&region=" + region
				+ "&city_limit=true&output=json&ak=gRhqOOqPOQzvM8nMRnVoQswejvggglqY";
		String body = getContent(url).body();
		JSONObject object = JSONObject.fromObject(body);
		Object obj = object.get("result");
		// System.out.println(obj);
		if (null != obj) {
			JSONArray jsonArray = JSONArray.fromObject(obj);
			if (jsonArray.size() > 0) {
				Object object2 = jsonArray.get(0);
				Object object3 = JSONObject.fromObject(object2).get("location");
				if (null != object3) {
					BigDecimal lat = new BigDecimal(JSONObject.fromObject(object3).get("lat").toString());
					BigDecimal lng = new BigDecimal(JSONObject.fromObject(object3).get("lng").toString());
					// System.out.println("lat:"+lat+" "+"lng:"+lng);
					BigDecimal[] arr = new BigDecimal[] { lat, lng };
					return arr;
				}
			}
		}
		return null;
	}

	public static Response getContent(String url) throws Exception {
		Response res = Jsoup.connect(url)
				.header("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
				.header("Accept-Encoding", "gzip, deflate, br").header("Accept-Language", "zh-CN,zh;q=0.9")
				.header("Cache-Control", "max-age=0").header("Connection", "keep-alive")
				.header("Host", "api.map.baidu.com")
				.header("Cookie",
						"BAIDUID=88909A53491477C005B08E92C026111F:FG=1; BIDUPSID=88909A53491477C005B08E92C026111F; PSTM=1525250461")
				.header("Upgrade-Insecure-Requests", "1")
				.header("User-Agent",
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36")
				// .get();
				.timeout(10000).ignoreContentType(true).execute();
		return res;
	}
}
