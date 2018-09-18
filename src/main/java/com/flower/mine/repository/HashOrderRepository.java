package com.flower.mine.repository;

import com.flower.mine.bean.HashOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface HashOrderRepository extends PagingAndSortingRepository<HashOrder, Long> {
//
//    List<HashOrder> findAllByStateAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(Integer state, Date startTime, Date endTime);

//    @Query("select new com.flower.mine.repository.HashOrderRepository.OrderModel(o.username, sum(o.hash)) from com.flower.mine.bean.HashOrder o where o.startTime < :today and o.endTime >= :today and o.state = :state group by o.username")
//    List<OrderModel> findOrdersGroupByUsername(@Param("state") Integer state, @Param("today") Date today);

    class OrderModel implements Serializable {
        private String username;
        private Integer hash;

        public OrderModel(String username, Integer hash) {
            this.username = username;
            this.hash = hash;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Integer getHash() {
            return hash;
        }

        public void setHash(Integer hash) {
            this.hash = hash;
        }
    }
}
