package com.kee.ad.util;

/**
 * @author KeeYang on 2017/8/10.
 * @Description :
 */
import java.security.GeneralSecurityException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.crypto.NoSuchPaddingException;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.stereotype.Component;

@Component
@MappedJdbcTypes({JdbcType.VARCHAR})
public class EncryptTypeHandler implements TypeHandler<String> {
    private static final Log logger = LogFactory.getLog(EncryptTypeHandler.class);

    public EncryptTypeHandler() {
    }

    public String getResult(ResultSet rs, String arg1) throws SQLException {
        String retValue = null;

        try {
            retValue = EncryptUtil.decrypt(rs.getString(arg1));
        } catch (NoSuchPaddingException var5) {
            logger.error(var5.getMessage(), var5);
        } catch (GeneralSecurityException var6) {
            logger.error(var6.getMessage(), var6);
        } catch (Exception var7) {
            logger.error(var7.getMessage(), var7);
        }

        return retValue;
    }

    public String getResult(ResultSet rs, int arg1) throws SQLException {
        String retValue = null;

        try {
            retValue = EncryptUtil.decrypt(rs.getString(arg1));
        } catch (NoSuchPaddingException var5) {
            logger.error(var5.getMessage(), var5);
        } catch (GeneralSecurityException var6) {
            logger.error(var6.getMessage(), var6);
        } catch (Exception var7) {
            logger.error(var7.getMessage(), var7);
        }

        return retValue;
    }

    public String getResult(CallableStatement arg0, int arg1) throws SQLException {
        String retValue = null;

        try {
            retValue = EncryptUtil.decrypt(arg0.getString(arg1));
        } catch (NoSuchPaddingException var5) {
            logger.error(var5.getMessage(), var5);
        } catch (GeneralSecurityException var6) {
            logger.error(var6.getMessage(), var6);
        } catch (Exception var7) {
            logger.error(var7.getMessage(), var7);
        }

        return retValue;
    }

    public void setParameter(PreparedStatement arg0, int arg1, String arg2, JdbcType arg3) throws SQLException {
        String value = "";

        try {
            value = EncryptUtil.encrypt(arg2);
        } catch (Exception var7) {
            logger.error(var7.getMessage(), var7);
        }

        if(StringUtils.isNotBlank(value)) {
            arg0.setString(arg1, value);
        } else {
            arg0.setString(arg1, arg2);
        }

    }
}
