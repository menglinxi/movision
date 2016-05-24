package com.zhuhuibao.mybatis.memCenter.service;

import com.zhuhuibao.common.Response;
import com.zhuhuibao.common.constant.Constants;
import com.zhuhuibao.common.constant.MsgCodeConstant;
import com.zhuhuibao.common.pojo.ResultBean;
import com.zhuhuibao.common.constant.JobConstant;
import com.zhuhuibao.mybatis.memCenter.entity.*;
import com.zhuhuibao.mybatis.memCenter.mapper.JobMapper;
import com.zhuhuibao.mybatis.memCenter.mapper.MemberMapper;
import com.zhuhuibao.mybatis.memCenter.mapper.PositionMapper;
import com.zhuhuibao.utils.MsgPropertiesUtils;
import com.zhuhuibao.utils.pagination.model.Paging;
import com.zhuhuibao.utils.pagination.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by cxx on 2016/4/18 0018.
 */
@Service
@Transactional
public class JobPositionService {
    private static final Logger log = LoggerFactory.getLogger(JobPositionService.class);

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private MemberMapper memberMapper;


    /**
     * 发布职位
     */
    public Response publishPosition(Job job){
        Response response = new Response();
        try{
            int isPublish = jobMapper.publishPosition(job);
            if(isPublish==1){
                response.setCode(200);
            }else{
                response.setCode(400);
                response.setMessage("发布失败");
            }
        }catch (Exception e){
            log.error("publishPosition error",e);
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 查询公司已发布的职位
     */
    public Response findAllPositionByMemId(Paging<Job> pager, String id){
        Response response = new Response();
        List<Job> jobList = jobMapper.findAllByPager(pager.getRowBounds(),id);
        List list = new ArrayList();
        for(int i=0;i<jobList.size();i++){
            Job job = jobList.get(i);
            Map map = new HashMap();
            map.put(Constants.position,job.getName());
            map.put(Constants.salary,job.getSalaryName());
            map.put(Constants.area,job.getWorkArea());
            map.put(Constants.id,job.getId());
            map.put(Constants.publishTime,job.getPublishTime().substring(0,10));
            list.add(map);
        }
        pager.result(list);
        response.setData(pager);
        response.setCode(200);
        return response;
    }

    /**
     * 查询公司发布的某条职位的信息
     */
    public Response getPositionByPositionId(String id){
        Response result = new Response();
        Job job = jobMapper.getPositionByPositionId(id);
        result.setCode(200);
        result.setData(job);
        return result;
    }

    /**
     * 删除已发布的职位
     */
    public Response deletePosition(String ids[]){
        Response response = new Response();
        int isDelete = 0;
        for(int i = 0; i < ids.length; i++){
            String id = ids[i];
            isDelete = jobMapper.deletePosition(id);
            try{
                if(isDelete==1){
                    response.setCode(200);
                }else {
                    response.setCode(400);
                    response.setMessage("删除失败");
                }
            }catch (Exception e){
                log.error("deletePosition error",e);
                e.printStackTrace();
            }
        }
        return response;
    }

    /**
     * 更新编辑已发布的职位
     */
    public Response updatePosition(Job job){
        Response result = new Response();
        int isUpdate = jobMapper.updatePosition(job);
        try{
            if(isUpdate==1){
                result.setCode(200);
            }else {
                result.setCode(400);
                result.setMessage("更新失败");
            }
        }catch (Exception e){
            log.error("deletePosition error",e);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询最新招聘职位
     */
    public Response searchNewPosition(int count){
        Response result = new Response();
        List<Job> jobList = jobMapper.searchNewPosition(count);
        result.setData(jobList);
        result.setCode(200);
        return result;
    }

    /**
     * 查询推荐职位
     */
    public Response searchRecommendPosition(String id, int count){
        Response result = new Response();
        List<Job> jobList = jobMapper.searchRecommendPosition(id,count);
        result.setData(jobList);
        result.setCode(200);
        return result;
    }

    /**
     * 查询不同公司发布的相同职位
     */
    public Response searchSamePosition(Map<String,Object> map){
        log.info("search same position form different company postID = "+ StringUtils.mapToString(map));
        Response result = new Response();
        List<Job> jobList = jobMapper.searchSamePosition(map);
        result.setData(jobList);
        result.setCode(200);
        return result;
    }

    /**
     * 查询最新发布的职位
     */
    public Response searchLatestPublishPosition(){
        Response result = new Response();
        List<Job> jobList = jobMapper.searchLatestPublishPosition();
        result.setData(jobList);
        result.setCode(200);
        return result;
    }

    /**
     * 职位类别
     */
    public Response positionType(){
        Response response = new Response();
        List<Position> positionList = positionMapper.findPosition(8);
        List<Position> subPositionList = positionMapper.findSubPosition();
        List list1 = new ArrayList();
        for(int i=0;i<positionList.size();i++){
            Position position = positionList.get(i);
            Map map = new HashMap();
            map.put(Constants.code,position.getId());
            map.put(Constants.name,position.getName());
            List list = new ArrayList();
            for(int y=0;y<subPositionList.size();y++){
                Position subPosition = subPositionList.get(y);
                if(position.getId().equals(subPosition.getParentId())){
                    Map map1 = new HashMap();
                    map1.put(Constants.code,subPosition.getId());
                    map1.put(Constants.name,subPosition.getName());
                    list.add(map1);
                }
            }
            map.put(Constants.subPositionList,list);
            list1.add(map);
        }
        response.setCode(200);
        response.setData(list1);
        return response;
    }

    /**
     * 查询发布职位公司信息
     * @param id
     * @return
     */
    public Response queryCompanyInfo(Long id) throws Exception
    {
        Response response = new Response();
        try
        {
            MemberDetails member = jobMapper.queryCompanyInfo(id);
            response.setData(member);
        }
        catch(Exception e)
        {
            log.error("add offer price error!",e);
            throw e;
        }
        return response;
    }

    /**
     * 查询招聘栏目的广告位
     * @param map 查询条件
     * @return
     */
    public Response queryAdvertisingPosition(Map<String,Object> map)
    {
        Response response = new Response();
        try
        {
            List<MemberDetails> memberList = jobMapper.queryAdvertisingPosition(map);
            response.setData(memberList);
        }
        catch(Exception e)
        {
            log.error("add offer price error!",e);
            response.setCode(MsgCodeConstant.response_status_400);
            response.setMsgCode(MsgCodeConstant.mcode_common_failure);
            response.setMessage((MsgPropertiesUtils.getValue(String.valueOf(MsgCodeConstant.mcode_common_failure))));
            return response;
        }
        return response;
    }

    /**
     * 查询企业发布的职位详情
     * @param id 职位ID
     * @return
     */
    public Response queryPositionInfoByID(Long id) throws Exception
    {
        log.info("query position info by id = "+id);
        Response response = new Response();
        try
        {
            Job job = jobMapper.queryPositionInfoByID(id);
            response.setData(job);
        }
        catch(Exception e)
        {
            log.error("add offer price error!",e);
            throw e;
        }
        return response;
    }

    /**
     * 查询企业发布的其它求职位
     * @param map 查询条件
     * @return
     */
    public List<Job> findAllOtherPosition(Paging<Job> pager,Map<String,Object> map)
    {
        List<Job> jobList = new ArrayList<Job>();
        try
        {
            jobList = jobMapper.findAllOtherPosition(pager.getRowBounds(),map);
        }
        catch(Exception e)
        {
            log.error("add offer price error!",e);

        }
        return jobList;
    }

    /**
     * 我申请的职位
     */
    public Response myApplyPosition(Paging<Job> pager, String id){
        Response response = new Response();
        List<Job> jobList = jobMapper.findAllMyApplyPosition(pager.getRowBounds(),id);
        List list = new ArrayList();
        for(int i=0;i<jobList.size();i++){
            Job job = jobList.get(i);
            Map map = new HashMap();
            map.put(Constants.id,job.getId());
            map.put(Constants.name,job.getName());
            map.put(Constants.companyName,job.getEnterpriseName());
            map.put(Constants.salary,job.getSalaryName());
            map.put(Constants.publishTime,job.getPublishTime());
            map.put(Constants.area,job.getWorkArea());
            map.put(Constants.welfare,job.getWelfare());
            list.add(map);
        }
        pager.result(list);
        response.setCode(200);
        response.setData(pager);
        return response;
    }

    /**
     * 人才网首页热门招聘
     * @param condition 查询的条件
     * @return
     * @throws Exception
     */
    public List queryHotPosition(Map<String,Object> condition) throws Exception{
        List list = new ArrayList();
        try {
            List<Job> jobList = jobMapper.queryHotPosition(condition);
            for (int i = 0; i < jobList.size(); i++) {
                Job job = jobList.get(i);
                Map map = new HashMap();
                map.put(Constants.id, job.getId());
                map.put(Constants.name, job.getName());
                map.put(JobConstant.JOB_KEY_POSITIONTYPE,job.getPositionType());
                map.put(Constants.createid, job.getCreateid());
                map.put(Constants.companyName, job.getEnterpriseName());
                map.put(Constants.salary, job.getSalaryName());
                map.put(Constants.publishTime, job.getPublishTime().substring(0, 10));
                map.put(Constants.updateTime, job.getUpdateTime().substring(0, 10));
                map.put(Constants.area, job.getWorkArea());
                map.put(Constants.welfare, job.getWelfare());
                map.put(Constants.logo, job.getEnterpriseLogo());
                list.add(map);
            }

        }catch(Exception e)
        {
            log.error("query hot postion error!!");
            throw e;
        }
        return list;
    }

    /**
     * 最新招聘（按分类一起查询）
     * @param count 数量
     * @return
     */
    public List queryLatestJob(int count) throws Exception{
        List list = new ArrayList();
        try {
            List<Position> positionList = positionMapper.findPosition(6);
            for (int a = 0; a < positionList.size(); a++) {
                Position position = positionList.get(a);
                Map map = new HashMap();
                map.put(Constants.name, position.getName());
                List<Job> jobList = jobMapper.queryLatestJob(position.getId(), count);
                List list1 = new ArrayList();
                for (int i = 0; i < jobList.size(); i++) {
                    Job job = jobList.get(i);
                    Map map1 = new HashMap();
                    map1.put(Constants.id, job.getId());
                    map1.put(Constants.name, job.getName());
                    map1.put(Constants.createid, job.getCreateid());
                    map1.put(Constants.salary, job.getSalaryName());
                    map1.put(Constants.area, job.getCity());
                    map1.put(JobConstant.JOB_KEY_POSITIONTYPE,job.getPositionType());
                    list1.add(map1);
                }
                map.put("jobList", list1);
                list.add(map);
            }

        }catch(Exception e)
        {
            log.error("query latest job error",e);
            throw e;
        }
        return list;
    }

    /**
     * 名企招聘
     * @return
     * @throws Exception
     */
    public List greatCompanyPosition() throws Exception{
        log.info("advertising greatest enterprise job");
        List list = new ArrayList();
        try {
            List<ResultBean> companyList = jobMapper.greatCompanyPosition();
            for (int i = 0; i < companyList.size(); i++) {
                ResultBean company = companyList.get(i);
                Map map = new HashMap();
                map.put(Constants.id, company.getCode());
                map.put(Constants.logo, company.getName());
                list.add(map);
            }
        }catch(Exception e) {
            log.error("advertising greatest enterprise job error!",e);
            throw e;
        }
        return list;
    }

    /**
     * 更新点击率
     */
    public void updateViews(Long jobID)
    {
        try{
            jobMapper.updateViews(jobID);
        }
        catch(Exception e)
        {
            log.error("update position info error!",e);
        }
    }

    /**
     * 相似企业
     */
    public Response querySimilarCompany(String id, int count)
    {
        Response response = new Response();
        Member member = memberMapper.findMemById(id);
        List<Job> companyList = jobMapper.querySimilarCompany(member.getEmployeeNumber(),member.getEnterpriseType(),id,count);
        List list = new ArrayList();
        for(int i=0;i<companyList.size();i++){
            Job job = companyList.get(i);
            Job companyInfo = new Job();
            companyInfo = jobMapper.querySimilarCompanyInfo(job.getCreateid());
            Map map = new HashMap();
            map.put(Constants.id,job.getCreateid());
            map.put(Constants.companyName,companyInfo.getEnterpriseName());
            map.put(Constants.size,companyInfo.getSize());
            map.put(Constants.area,companyInfo.getCity());
            list.add(map);
        }
        response.setData(list);
        return response;
    }

    /**
     * 查询名企发布的热门职位
     * @param map  查询条件：recommend 是否名企（1：是），count 条数
     * @return 发布职位集合
     */
    public List<Job> queryEnterpriseHotPosition(Map<String,Object> map)
    {
        List<Job> jobList = new ArrayList<Job>();
        try
        {
            jobList = jobMapper.queryEnterpriseHotPosition(map);
        }
        catch(Exception e)
        {
            throw e;
        }
        return jobList;
    }

    /**
     * 查询名企发布的热门职位
     * @param map  查询条件：recommend 是否名企（1：是），count 条数
     * @return 发布职位集合
     */
    public List<Map<String,String>> queryPublishJobCity(Map<String,Object> map) throws Exception
    {
        List<Map<String,String>> jobList = new ArrayList<Map<String,String>>();
        try
        {
            jobList = jobMapper.queryPublishJobCity(map);
        }
        catch(Exception e)
        {
            throw e;
        }
        return jobList;
    }

    /**
     * 根据ID查询
     * @param id {id}
     * @return
     */
    public Map<String,Object> findById(String id){
       return positionMapper.findById(id);
    }
}
