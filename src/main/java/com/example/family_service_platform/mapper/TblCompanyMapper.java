package com.example.family_service_platform.mapper;

import com.example.family_service_platform.bean.TblCompany;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 企业档案 Mapper 接口
 * </p>
 *
 * @author lian
 * @since 2020-04-18
 */
@Component
public interface TblCompanyMapper extends BaseMapper<TblCompany> {

    public List<TblCompany> selectCompany();
}
