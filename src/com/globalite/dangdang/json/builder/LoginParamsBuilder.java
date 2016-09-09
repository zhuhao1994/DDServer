package com.globalite.dangdang.json.builder;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.entity.Customer;
import com.globalite.dangdang.json.builder.Builder;
import com.oracle.dd.tool.json.response.entity.LoginResponseParams;

public class LoginParamsBuilder implements Builder {
    public static final int OPTION_INIT = 0;
    public static final int OPTION_LOGIN_ERROR = 1;
    public static final int OPTION_CODE_ERROR = 2;
    public static final int OPTION_OK = 3;

    public LoginParamsBuilder() {
    }

    public Object build(Object entity, int optional) {
        LoginResponseParams param = new LoginResponseParams();
        switch(optional) {
            case 0:
                param.setIsOk(true);
                param.setErrorinfo((String)null);
                param.setCusid((String)null);
                break;
            case 1:
                param.setIsOk(false);
                param.setErrorinfo("用户名或者密码不正确");
                break;
            case 2:
                param.setIsOk(false);
                param.setErrorinfo("输入的验证码不正确");
                break;
            case 3:
                param.setCusid(String.valueOf(((Customer)entity).getId()));
                param.setIsOk(true);
                param.setErrorinfo((String)null);
        }

        return param;
    }
}

