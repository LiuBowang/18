package com.example.family_service_platform.mapper;

import com.example.family_service_platform.bean.FcBuilding;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 楼宇信息表 Mapper 接口
 * </p>
 *
 * @author lian
 * @since 2020-04-18
 */
@Component
public interface FcBuildingMapper extends BaseMapper<FcBuilding> {

}
