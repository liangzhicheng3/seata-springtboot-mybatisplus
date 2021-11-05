package com.liangzhicheng.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@MapperScan(basePackages = {"com.liangzhicheng.**.mapper*"}, sqlSessionFactoryRef = "sqlSessionFactory")
@Configuration
public class DataSourceConfig {

    /**
     * mapper的xml位置
     */
    public static final String[] DATASOURCE_MAPPER_LOACTIONS = {"classpath*:com/liangzhicheng/**/*Mapper.xml"};

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(this.getMapperResource());
        return bean.getObject();
    }

    /**
     * @description mapper的xml路径处理，把String[]转Resource[]
     * @return Resource[]
     */
    private Resource[] getMapperResource(){
        try {
            if(DATASOURCE_MAPPER_LOACTIONS != null && DATASOURCE_MAPPER_LOACTIONS.length > 0) {
                List<Resource> resourceList = new ArrayList<>();
                for (String location : DATASOURCE_MAPPER_LOACTIONS) {
                    Resource[] resourceArr = new Resource[0];
                    resourceArr = new PathMatchingResourcePatternResolver().getResources(location);
                    for (Resource resource : resourceArr) {
                        resourceList.add(resource);
                    }
                }
                Resource[] resources = new Resource[resourceList.size()];
                for (int i = 0; i < resourceList.size(); i++) {
                    resources[i] = resourceList.get(i);
                }
                return resources;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
