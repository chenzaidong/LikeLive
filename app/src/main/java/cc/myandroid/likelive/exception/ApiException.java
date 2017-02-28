package cc.myandroid.likelive.exception;

/**
 * Created by chenzd on 2017/2/19.
 */

public class ApiException extends RuntimeException {
    public static final int NETWORK_CONNECTION_ERROR=101;
    public static final int WRONG_PASSWORD = 102;
    public static final int DATA_PARSING_ERRORS=103;
    public static final  int SOCKET_TIMEOUT_EXCEPTION=104;
    public ApiException(int resultCode){
        this(getApiExceptionMessage(resultCode));
    }
    public ApiException(String detailMessage){
        super(detailMessage);
    }

    private static String getApiExceptionMessage(int resultCode){
        String message = "";
        switch (resultCode) {
            case NETWORK_CONNECTION_ERROR:
                message = "网络连接错误";
                break;
            case WRONG_PASSWORD:
                message = "用户或者密码错误";
                break;
            case DATA_PARSING_ERRORS:
                message = "数据解析错误";
                break;
            case SOCKET_TIMEOUT_EXCEPTION:
                message="网络连接超时";
                break;
            default:
                message = "未知错误:错误代码:"+resultCode;

        }
        return message;
    }
}
