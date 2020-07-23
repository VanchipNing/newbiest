package com.newbiest.gc.service;

import com.newbiest.base.exception.ClientException;
import com.newbiest.mms.model.DocumentLine;

/**
 * 和跨域速递的接口。
 * @author guoxunbo
 * @date 2020-07-08 13:49
 */
public interface ExpressService {

    String planOrder(String expressNumber, DocumentLine documentLine, int serviceMode, int payMode) throws ClientException;
    void cancelOrder(String expressNumber) throws ClientException;

}