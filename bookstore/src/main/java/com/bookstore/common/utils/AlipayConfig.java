package com.bookstore.common.utils;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092800618241";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDMtcaxk4fMDkI+EixMhnZj3MKtY5TP2K+CJ2eGPvzHkIbKauyTWANg8Bsu5cH9rmtMBos0lRCU+GbzdBZRheJ9oVLs2cmCGXMbLwj9ZUKlPKhdnlNjeKMgHLaDGgfcXHWwHozSgk3irxO9vGpW4aK7Bg3OOtfgAf4fRdrkTJUKyK7C/ktMf8/Qz8IFzJeynBSmttSgquMwjhBcyZLG41mIlQ976+szainNRGsiIp5RIy2S5E9phjhI1xpX83yLeRzqpWo3X8JGWQqoaEyJTd8v0sIpMQ6j87nL4GCi5lJW/emV1nG2qxNQq8ymozTpIlJiqNd5zzOXuzw8Rd4Ep/nZAgMBAAECggEAOEMNZOjDPdX9Zsnf2vhpuXCHINhpoeZP9lmWHlNZbg/Ou/s/Ig/ifdX/4pfLBUnWu4Jg6AbAE/Fhei/Boo5FfXnFLMt+kn8kH6iBtAMMmhVdwL0u9fvF+aFqmAWje3D993WuBn1BorezmUYbg91nuDclvDR3+F0T1syAKJbTr66RwV08w8zhVkev182DJKORRYzeztPoQ6WzICprWRtMr0chM2+4BbauaYZ+gFBcV6nSAhljSaNRBTRf+lyTh0Vun21i0GUDgeq6j9GNz5OK9UpR4Z1716d/g/wbGQ4t5tlRRIp2VPkstq+8EMd/KmXlPRF4VCMnw/+9iNoOl1j+wQKBgQD9GCsohTHbI+YrAuA9Njo6N8Rml48182AumP0q5J7IBRT+NE0Tr30NBhMy5yQPiskU/aZgLpIMxRxY2LdD7K5ozQI+/nRkCmwyGVFqm/JBHwa0WXsrw0PueH2Mx2LDQufrC5VEUJGR7tZjAlomDgS5vQBUCJxmR6eGr3eRbjVTvQKBgQDPD2iRHI6/7f4GrIwepebbf2dA5nKARrPIjaeEM8+21AD/rSStQTObGRoGKWMBC4JEEzt4XmRMmC0Q/h5nhkZ/hDJJq05Y2TM4fLmRIDhkAkgr4u9mEtaxLn7BIxI2fXEf8SiiPFI3/VCR0/1VUv/PwBzA/SxD+QuOxOxwKAySTQKBgFG9coLdsaJiz71oal+ameefhzDTFg+IkLG7+1fVCmrzKTqITbRj4vFH2v9/oyfZ3OOIoClEISs6pdMT+uxXDmUOgQEzUQfWTedIQgE6aF0b1iB9HciegdwM0g+bfYwumZRR411y3/DPwDGt2subj28lWWvKdCicT8Cx/t3bSCHJAoGAR18qTgqn8eRZEXfcfJv27WYG80n74dd9FP4u5dTuHjkJdxf5cnbtEOMgI0uUwsFyz4BJmX3/2J8Dub2+QTT5i+7HXrlbsih8DVaAqFOQ3Hgk5Gu+1VJdConaSzyJhJrMqZP4y6M7O+1nHj0SL50CbFvuDV+3KF7p6vZaAXbsjmkCgYEA+7LA7105CRi8J83D6rOIErlXwYWeRnYMjYveavE/Yocd1V0AzCyF+s2Nc8vRQS4RLJ87oU2869xpJQSyzndblxAwBmDD3bhbs1Zul6vfRslySh7VDpVAhv9d7zNzy9cnsmAbQP7Q3HSY9oEovaoPfX0nqznMnK26Io71L6WwurI=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArqWBlfFI1+Qf889+i1giHKnml1br3Xm5fFQGOOYUJjcUrlvkQC0uvxrSasEeFZHSPtys59emUe+5FiA3PnK9WGbergNr78S1dd9zl1vLp08Ky9JCb3cAnQMzeMXHVcBYIYcDREXHNBjFmsr5Cavp6lohBegGgY8yVkHHtS05auXwWFHoJp6/NkuLm7iTKkkYpj2viEGS7fvrtA5G119IVrvzw02F/wC7TMZSvzZxQtpugX+SpC5kEQ2hDaVC2B2/eYqLurpTxWvtci2GJR8rfazvCbPflwaq9zMA0MP1i7sPW5TxCH6+OBa6m0Lx/WOQpCzaID2371QnNijpyciaowIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/bookstore_war_exploded/order/paysuccess.do";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

