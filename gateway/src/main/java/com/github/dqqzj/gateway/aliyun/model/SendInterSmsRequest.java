package com.github.dqqzj.gateway.aliyun.model;

/**
 * Created by qzj on 2018/1/12
 */
import com.aliyuncs.RpcAcsRequest;

public class SendInterSmsRequest extends RpcAcsRequest<SendInterSmsResponse> {
    private String templateCode;
    private String phoneNumbers;
    private String countryCode;
    private String signName;
    private String resourceOwnerAccount;
    private String templateParam;
    private Long resourceOwnerId;
    private Long ownerId;
    private String outId;

    public SendInterSmsRequest() {
        super("Dysmsapi", "2017-05-25", "SendInterSms");
    }

    public String getTemplateCode() {
        return this.templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
        if (templateCode != null) {
            this.putQueryParameter("TemplateCode", templateCode);
        }

    }

    public String getPhoneNumbers() {
        return this.phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
        if (phoneNumbers != null) {
            this.putQueryParameter("PhoneNumbers", phoneNumbers);
        }

    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        if (countryCode != null) {
            this.putQueryParameter("CountryCode", countryCode);
        }

    }

    public String getSignName() {
        return this.signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
        if (signName != null) {
            this.putQueryParameter("SignName", signName);
        }

    }

    public String getResourceOwnerAccount() {
        return this.resourceOwnerAccount;
    }

    public void setResourceOwnerAccount(String resourceOwnerAccount) {
        this.resourceOwnerAccount = resourceOwnerAccount;
        if (resourceOwnerAccount != null) {
            this.putQueryParameter("ResourceOwnerAccount", resourceOwnerAccount);
        }

    }

    public String getTemplateParam() {
        return this.templateParam;
    }

    public void setTemplateParam(String templateParam) {
        this.templateParam = templateParam;
        if (templateParam != null) {
            this.putQueryParameter("TemplateParam", templateParam);
        }

    }

    public Long getResourceOwnerId() {
        return this.resourceOwnerId;
    }

    public void setResourceOwnerId(Long resourceOwnerId) {
        this.resourceOwnerId = resourceOwnerId;
        if (resourceOwnerId != null) {
            this.putQueryParameter("ResourceOwnerId", resourceOwnerId.toString());
        }

    }

    public Long getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        if (ownerId != null) {
            this.putQueryParameter("OwnerId", ownerId.toString());
        }

    }

    public String getOutId() {
        return this.outId;
    }

    public void setOutId(String outId) {
        this.outId = outId;
        if (outId != null) {
            this.putQueryParameter("OutId", outId);
        }

    }

    public Class<SendInterSmsResponse> getResponseClass() {
        return SendInterSmsResponse.class;
    }
}
