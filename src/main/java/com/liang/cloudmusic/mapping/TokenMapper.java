package com.liang.cloudmusic.mapping;

import com.liang.cloudmusic.model.Token;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenMapper {
    int insert(Token record);

    int insertSelective(Token record);

    Token getAccessToken();

    int getNewAccessToken(@Param("oldAccessToken") String oldAccessToken,
                          @Param("newAccessToken") String newAccessToken);
}