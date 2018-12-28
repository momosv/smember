package cn.com.wyxt.base.entity;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/18 20:25
 **/

public enum Tips {


    COMPANY_USER_NULL(1001,"在线状态已经过期"),
    FEEBACK_MAIN_NULL(2001,"反馈单标题或者内容不能为空"),
    ACCOUNT_NAME_PSW_NULL(101,"账号或密码不能为空"),
    COMP_NAME_NULL(102,"企业名称不能为空"),
    COMP_CODE_NULL(103,"企业社会统一信用码不能为空"),
    COMP_EMAIL_NULL(103,"企业联系邮箱不能为空"),

    ;
    /**
     * The Code.
     */
    private int code;
    /**
     * The Desc.
     */
    private String desc;

    /**
     * Instantiates a new Error code desc.
     *
     * @param _nCode the n code
     * @param _nDesc the n desc
     */
    // 构造函数，枚举类别只能为私有
    Tips(int _nCode, String _nDesc) {
        this.code = _nCode;
        this.desc = _nDesc;
    }

    /**
     * Gets code.
     *
     * @return code code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code 要设置的 code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Gets desc.
     *
     * @return desc desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets desc.
     *
     * @param desc 要设置的 desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

}
