package com.movision.facade.index;

import com.google.gson.Gson;
import com.movision.mybatis.postLabel.entity.PostLabel;
import com.movision.test.SpringTestCase;
import com.movision.utils.DateUtils;
import org.junit.Test;
import org.springframework.asm.Label;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author zhuangyuhao
 * @Date 2017/7/25 17:35
 */
public class FacadePostTest extends SpringTestCase {

    @Autowired
    private FacadePost facadePost;

    @Test
    public void releaseModularPost() throws Exception {

        String userid = "323";  //18051989558
        String circleid = "45"; //南林

        List<PostLabel> list = new ArrayList<>();

        list.add(createPostLabel(userid));
        list.add(createPostLabel(userid));

        Gson gson = new Gson();
        String labelList = gson.toJson(list);
        System.out.println("labelList=" + labelList);

        facadePost.releaseModularPost(null, userid, circleid, null, null, null, null, null, labelList);
    }

    private PostLabel createPostLabel(String userid) {
        PostLabel p1 = new PostLabel();

        p1.setName("ceshi_" + DateUtils.getCurrentDate());
        p1.setUserid(Integer.valueOf(userid));
        p1.setType(3);
        p1.setPhoto("");
        p1.setCitycode("320100");

        return p1;
    }

}