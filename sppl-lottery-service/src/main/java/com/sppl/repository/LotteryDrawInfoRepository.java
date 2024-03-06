package com.sppl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sppl.entity.LotteryDraw;
import com.sppl.repository.helper.LotteryDrawInfo;

@Repository
@RedisHash 
public interface LotteryDrawInfoRepository extends JpaRepository<LotteryDraw, String>{
	@Query(value = "select new com.sppl.repository.helper.LotteryDrawInfo(t.drawNo,t.drawDate,t.prizeAmount) from LotteryDraw t where t.drawNo=:drawNo and t.status= :status order by t.drawNo limit 1")
	List<LotteryDrawInfo> findByDrawNoAndStatus(@Param("drawNo") String drawNo, @Param("status") String status);

}
