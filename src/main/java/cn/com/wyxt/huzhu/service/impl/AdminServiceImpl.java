package cn.com.wyxt.huzhu.service.impl;

import cn.com.wyxt.base.mybatis.service.impl.BasicServiceImpl;
import cn.com.wyxt.base.redis.util.RedisCacheUtil;
import cn.com.wyxt.base.redis.util.RedisUtils;
import cn.com.wyxt.base.tokenManager.impl.AuthManager;
import cn.com.wyxt.huzhu.dao.dao.TbRuleMapper;
import cn.com.wyxt.huzhu.model.TbRule;
import cn.com.wyxt.huzhu.modelVO.TbAdminVO;
import cn.com.wyxt.huzhu.service.IAdminService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service("adminService")
public class AdminServiceImpl extends BasicServiceImpl implements IAdminService {

    @Autowired
    TbRuleMapper tbRuleMapper;

    @Autowired
    public void setMappper(){
        setMapper(tbRuleMapper);
    }


    @Override
   public Object  getRule(){
        List<TbRule> list = RedisUtils.getList("rule::cost",TbRule.class);
        if(null!=list){
           return list;
        }
        list= tbRuleMapper.getRule();
        RedisUtils.set("rule::cost",list);
        return list;
   }

    @Override
    public void saveRule(String rule) throws IllegalAccessException, InstantiationException, CloneNotSupportedException {
        TbAdminVO user = AuthManager.getAdminVO();
        List<TbRule> oldList= tbRuleMapper.getRule();
        short group= (short) (1+ oldList.get(0).getGrp());
         oldList.stream().forEach(e->{e.setStatus(0);});
         this.updateBatch(oldList,true);

        JSONObject object =JSONObject.parseObject(rule);
        List l1 = (List) object.get("category");
        List l2 = (List)  object.get("manage");
        List l3 = (List)  object.get("womanage");
        TbRule rule0 = new TbRule();
        rule0.setCreateTime(new Date());
        rule0.setActiveTime(new Date());
        rule0.setGrp(group);
        rule0.setStatus(1);
        rule0.setCreator(user.getName());
        TbRule rule1 = null;
        List<TbRule> newList= new ArrayList<>();
        for (int i = 0; i < l1.size(); i++) {
            rule1=rule0.clone();
            rule1.setRatio(new BigDecimal(l1.get(i).toString()));
            rule1.setType(1);
            rule1.setName(i+1+"类");
            rule1.setCategory((short) (i+1));
            newList.add(rule1);
        }
        for (int i = 0; i < l2.size(); i++) {
            List ol = (List) l2.get(i);
            rule1=rule0.clone();
            rule1.setLeftAge(Short.valueOf(ol.get(0).toString()));
            rule1.setRightAge(Short.valueOf(ol.get(1).toString()));
            rule1.setRatio(new BigDecimal(ol.get(2).toString()));
            rule1.setType(2);
            rule1.setName("年龄(男)");
            newList.add(rule1);
        }
        for (int i = 0; i < l3.size(); i++) {
            List ol = (List) l3.get(i);
            rule1=rule0.clone();
            rule1.setLeftAge(Short.valueOf(ol.get(0).toString()));
            rule1.setRightAge(Short.valueOf(ol.get(1).toString()));
            rule1.setRatio(new BigDecimal(ol.get(2).toString()));
            rule1.setType(3);
            rule1.setName("年龄(女)");
            newList.add(rule1);
        }

        this.insertBatch(newList);
        RedisUtils.delete("rule::cost");
    }
}
