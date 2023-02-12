package com.jfeng.chapter4.service;

import com.jfeng.framework.mvc.annotation.Service;
import com.jfeng.framework.orm.DataSet;
import com.jfeng.framework.transaction.annotation.Transaction;
import com.jfeng.framework.mvc.bean.RequestFileParam;
import com.jfeng.chapter4.model.Customer;
import com.jfeng.framework.mvc.FileUploadHelper;
import com.jfeng.framework.util.PropsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * 提供客户数据服务
 */
@Service
public class CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);
    /**
     * 获取客户列表
     */
    public List<Customer> getCustomerList() {
        return DataSet.selectList(Customer.class);
    }

    /**
     * 获取客户
     */
    public Customer getCustomer(long id) {
        return DataSet.select(Customer.class, "id=?", id);
    }

    /**
     * 创建客户
     */
    @Transaction
    public long createCustomer(Map<String, Object> fieldMap) {
        return DataSet.insert(Customer.class, fieldMap);
    }

    /**
     * 创建客户，带文件上传
     */
    @Transaction
    public long createCustomer(Map<String, Object> fieldMap, RequestFileParam fileParam) {
        if (fileParam != null) {
            fieldMap.put("photo", fileParam.getFileName());
        }
        long id = DataSet.insert(Customer.class, fieldMap);

        if (id != 0 && fileParam != null) {
            String uploadPath = "C:/Users/jfeng/Documents/program/java/learn/my-web-framework/chapter4/src/main/webapp/asset/upload/";
            FileUploadHelper.uploadFile(uploadPath, fileParam);
        }
        return id;
    }

    /**
     * 更新客户
     */
    @Transaction
    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        return DataSet.update(Customer.class, fieldMap, "id=?", id);
    }

    /**
     * 更新客户，带文件上传
     */
    @Transaction
    public boolean updateCustomer(long id, Map<String, Object> fieldMap, RequestFileParam fileParam) {
        if (fileParam != null) {
            fieldMap.put("photo", fileParam.getFileName());
        }
        boolean result = DataSet.update(Customer.class, fieldMap, "id=?", id);

        if (result && fileParam != null) {
            String uploadPath = "C:/Users/jfeng/Documents/program/java/learn/my-web-framework/chapter4/src/main/webapp/asset/upload/";
            FileUploadHelper.uploadFile(uploadPath, fileParam);
        }
        return result;
    }

    /**
     * 删除客户
     */
    @Transaction
    public boolean deleteCustomer(long id) {
        return DataSet.delete(Customer.class, "id=?", id);
    }
}
