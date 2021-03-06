package com.movision.common.constant;

import com.movision.utils.im.ImPropertiesLoader;

/**
 * @Author zhuangyuhao
 * @Date 2017/3/6 16:47
 */
public class ImConstant {

    public static final String APP_KEY = ImPropertiesLoader.getPropertyValue("app_key");

    public static final String APP_SECRET = ImPropertiesLoader.getPropertyValue("app_secret");


    public static final Integer TYPE_APP = 1;
    public static final Integer TYPE_BOSS = 0;


    //-------------------------------------**********-----------------------------------------------

    /**
     * 创建云信用户
     */
    public static final String CREATE_USER_URL = ImPropertiesLoader.getPropertyValue("create_user_url");

    /**
     * 更新用户的云信token
     */
    public static final String UPDATE_USER_TOKEN_URL = ImPropertiesLoader.getPropertyValue("update_user_accid_url");

    /**
     * 刷新用户云信token
     */
    public static final String REFRESH_USER_TOKEN_URL = ImPropertiesLoader.getPropertyValue("refresh_user_token_url");

    /**
     * 更新用户信息
     */
    public static final String UPDATE_USER_INFO_URL = ImPropertiesLoader.getPropertyValue("update_user_info_url");

    /**
     * 获取用户信息
     */
    public static final String GET_USER_INFO = ImPropertiesLoader.getPropertyValue("get_user_info");


    /**
     * 加好友
     */
    public static final String ADD_FRIEND = ImPropertiesLoader.getPropertyValue("add_friend");


    /**
     * 个人与个人 发消息
     */
    public static final String SEND_MSG = ImPropertiesLoader.getPropertyValue("send_msg");


    /**
     * 批量发送系统通知
     */
    public static final String SEND_BATCH_ATTACH_MSG = ImPropertiesLoader.getPropertyValue("send_batch_attach_msg");


    public static final String SEND_MSG_BATCH = ImPropertiesLoader.getPropertyValue("send_msg_attach");

    /**
     * 查看指定用户的黑名单和静音列表
     */
    public static final String LIST_BLACK_AND_MUTE_LIST = ImPropertiesLoader.getPropertyValue("list_black_and_mute_list");

    /**
     * 帖子操作类型，用于推送分类
     */
    public enum PUSH_TYPE {
        zan(1), //点赞
        comment(2),    //评论
        //        collect(3), //收藏
        focus(4),  //关注
        reward(5);  //打赏

        public final int code;

        PUSH_TYPE(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        @Override
        public String toString() {
            return String.valueOf(this.code);
        }
    }


    /**
     * 推送跳转页面分类
     */
    public enum PUSH_MESSAGE {
        instantinfo_msg(1), //动态通知
        system_msg(2),    //系统通知
        operation_msg(3), //运营通知
        im_msg(4);  //im消息

        public final int code;

        PUSH_MESSAGE(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        @Override
        public String toString() {
            return String.valueOf(this.code);
        }
    }

}
