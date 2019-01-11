package com.newbiest.base.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.newbiest.base.exception.ClientException;
import com.newbiest.base.exception.ClientParameterException;
import com.newbiest.base.exception.NewbiestException;
import com.newbiest.base.model.NBBase;
import com.newbiest.base.model.NBUpdatable;
import com.newbiest.base.service.BaseService;
import com.newbiest.base.utils.SessionContext;
import com.newbiest.base.utils.StringUtils;
import com.newbiest.main.JwtSigner;
import com.newbiest.msg.DefaultParser;
import com.newbiest.msg.Request;
import com.newbiest.msg.RequestHeader;
import com.newbiest.security.model.NBOrg;
import com.newbiest.security.service.SecurityService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by guoxunbo on 2018/7/11.
 */
@Component
public class AbstractRestController implements Serializable{

    private static final long serialVersionUID = 5792313127796577694L;

    protected static final String AUTHORITY_HEAD_NAME = "Authorization";

    @Autowired
    protected BaseService baseService;

    @Autowired
    protected SecurityService securityService;

    @Autowired
    private JwtSigner jwtSigner;

    private final String requestToJson(Request request) throws Exception {
        ObjectMapper objectMapper = DefaultParser.getObjectMapper();
        ObjectWriter jsonWriter = objectMapper.writerWithView(request.getClass());
        return jsonWriter.writeValueAsString(request);
    }

    public final void log(Logger logger, Request request) throws Exception{
        if (logger.isDebugEnabled()) {
            String requestJson = requestToJson(request);
            logger.debug(requestJson);
        }
        if (!logger.isDebugEnabled() && logger.isInfoEnabled()) {
            logger.info(request.getHeader().getTransactionId());
        }
    }

    /**
     * 验证是否登录
     */
    public void validationLogin(Request request) {
        RequestHeader requestHeader = request.getHeader();

    }

    protected SessionContext getSessionContext(Request request) throws ClientException {
        SessionContext sc = new SessionContext();
        NBOrg nbOrg = null;
        Long orgRrn = request.getHeader().getOrgRrn();
        String orgName = request.getHeader().getOrgName();
        if (orgRrn != null) {
            nbOrg = baseService.findOrgByObjectRrn(request.getHeader().getOrgRrn());
        } else if (!StringUtils.isNullOrEmpty(orgName)) {
            nbOrg = baseService.findOrgByName(orgName);
        }
        if (nbOrg == null) {
            throw new ClientParameterException(NewbiestException.COMMON_ORG_IS_NOT_EXIST, orgRrn != null ? orgRrn : orgName);
        }
        sc.setOrgRrn(nbOrg.getObjectRrn());
        sc.setOrgName(nbOrg.getName());
        sc.setUsername(request.getHeader().getUsername());
        return sc;
    }

    protected NBBase saveEntity(NBBase nbBase, SessionContext sc) throws ClientException {
        return baseService.saveEntity(nbBase, sc);
    }

    protected void deleteEntity(NBBase nbBase, boolean deleteRelationFlag, SessionContext sc) throws ClientException {
        baseService.delete(nbBase, deleteRelationFlag, sc);
    }

    protected void deleteEntity(NBBase nbBase, SessionContext sc) throws ClientException {
        baseService.delete(nbBase, sc);
    }

    /**
     * 查找Entity 默认带出所有的懒加载对象
     * @param nbBase
     * @return
     */
    protected NBBase findEntity(NBBase nbBase) {
        return baseService.findEntity(nbBase, true);
    }

    /**
     * 验证当前对象是不是最新的对象
     * @param nbUpdatable
     * @throws ClientException
     */
    protected void validateEntity(NBUpdatable nbUpdatable) throws ClientException {
        NBUpdatable oldBase = (NBUpdatable) baseService.findEntity(nbUpdatable, false);
        if (!oldBase.getLockVersion().equals(nbUpdatable.getLockVersion())) {
            throw new ClientParameterException(NewbiestException.COMMON_ENTITY_IS_NOT_NEWEST, oldBase.getClass().getName(), oldBase.getLockVersion());
        }
    }

    protected NBBase updateEntity(NBBase nbBase, SessionContext sc) throws ClientException {
        if (nbBase instanceof NBUpdatable) {
            validateEntity((NBUpdatable) nbBase);
        }
        return baseService.saveEntity(nbBase, sc);
    }
}
