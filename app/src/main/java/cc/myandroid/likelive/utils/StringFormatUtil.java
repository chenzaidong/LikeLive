package cc.myandroid.likelive.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Created by chenzd on 2017/2/23.
 * 字符串匹配 工具类
 */
public class StringFormatUtil {
    private static final String TEL_REGEX = "[1][3578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。

    /**
     * 验证手机号码格式是否正确
     *
     * @param str
     * @return
     */
    public static boolean Matches(String str) {
        return str.matches(TEL_REGEX);
    }


    /**
     * 替换字符串中的指定字符
     *
     * @param str
     * @param keyword
     * @param replace
     * @return
     */
    public static String replace(String str, String keyword, String replace) {
        return str.replaceAll(keyword, replace);
    }

    /**
     * 获取错误提示详细信息
     *
     * @param json
     * @return
     */
    public static String getErrorMessageDetail(String json) {

        SMSSDKErrorMessageBean msg = null;
        try {
            msg = new Gson().fromJson(json, SMSSDKErrorMessageBean.class);
        } catch (JsonSyntaxException e) {
            return json;
        }
        return msg.getDetail();
    }

    public class SMSSDKErrorMessageBean {

        /**
         * status : 477
         * detail : 当前手机号发送短信的数量超过限额
         */

        private int status;
        private String detail;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }
}
