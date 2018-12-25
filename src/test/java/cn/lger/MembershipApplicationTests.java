package cn.lger;

import cn.xsshome.taip.ocr.TAipOcr;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MembershipApplicationTests {

	@Test
	public void contextLoads() {
	}
	//设置APPID/APP_KEY
	public static final String APP_ID = "2109622308";
	public static final String APP_KEY = "M25pVUiQFeSMWSxY";

	public static void main(String[] args) throws Exception {
		//https://blog.csdn.net/u010651369/article/details/80059442
        ArrayList list = new ArrayList();
        list.add("1");
        list.add("2");
        List child = list.subList((1-1)*50,list.size());

		// 初始化一个TAipOcr
		TAipOcr aipOcr = new TAipOcr(APP_ID,APP_KEY);
		// 调用接口
//		String result = aipOcr.idcardOcr("./idcard.jpg", 0);//身份证正面(图片)识别
//		String result = aipOcr.idcardOcr("./idcard2.jpg", 1);//身份证反面(国徽)识别
//		String result = aipOcr.bcOcr("./juli2.jpg");//名片识别
//		String result = aipOcr.driverlicenseOcr("./driver.jpg",0);//行驶证OCR识别
//		String result = aipOcr.driverlicenseOcr("./driver2.jpg",1);//驾驶证OCR识别
//		String result = aipOcr.bizlicenseOcr("./biz.jpg");//营业执照OCR识别
//		String result = aipOcr.creditcardOcr("./bank2.jpg");//银行卡OCR识别
//		String result = aipOcr.generalOcr("./biz.jpg");//通用OCR识别
//		String result = aipOcr.handWritingOcrByImage("./biz.jpg");//手写体识别 选取本地图片文件识别
//		String result = aipOcr.handWritingOcrByUrl("https://yyb.gtimg.com/ai/assets/ai-demo/small/hd-1-sm.jpg");//手写体识别 选取网络图片URL识别
//		String result = aipOcr.plateOcrByImage("./biz.jpg");//车牌识别 选取本地图片文件识别
//		String result = aipOcr.plateOcrByUrl("https://yyb.gtimg.com/ai/assets/ai-demo/large/plate-1-lg.jpg");//车牌识别 选取网络图片URL识别
		String result = aipOcr.idcardOcr("E:\\Pictures\\odemo-pic-1.jpg", 0);//身份证正面(图片)识别
		System.out.println(result);


	}


}
