package com.flower.mine.service;

import com.flower.mine.repository.ChargeRepository;
import com.flower.mine.repository.WithdrawRepository;
import com.flower.mine.ret.DataResult;
import com.flower.mine.ret.StatVo;
import com.flower.mine.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StatService {

    private static final int Stat_Type_Year = 1;
    private static final int Stat_Type_Month = 2;
    private static final int Stat_Type_Week = 3;
    private static final int Stat_Type_Day = 4;

    @Autowired
    private ChargeRepository chargeRepository;
    @Autowired
    private WithdrawRepository withdrawRepository;

    /**
     * 收入统计
     * @param type
     * @param start
     * @param end
     * @return
     */
    public DataResult<List<StatVo>> chargeStat(int type, long start, long end) {
        Date sd = DateUtil.truncateToDay(new Date(start));
        Date ed = DateUtil.truncateToDay(new Date(end));
        String format = convertTypeToFormat(type);
        List<Object[]> list = chargeRepository.chargeStat(format, sd, ed );
        return result(list);
    }

    /**
     * 支出统计
     * @param type
     * @param start
     * @param end
     * @return
     */
    public DataResult<List<StatVo>> expenseStat(int type, long start, long end) {
        Date sd = DateUtil.truncateToDay(new Date(start));
        Date ed = DateUtil.truncateToDay(new Date(end));
        String format = convertTypeToFormat(type);
        List<Object[]> list = withdrawRepository.withdrawStat(format, sd, ed );
        return result(list);
    }

    private DataResult<List<StatVo>> result(List<Object[]> list) {
        DataResult<List<StatVo>> result = new DataResult<>();
        List<StatVo> statVos = new ArrayList<>(list.size());
        result.setData(statVos);
        list.stream().forEach( e -> statVos.add(new StatVo((String) e[0], (BigDecimal) e[1])));
        return result;
    }

    private String convertTypeToFormat(int type) {
        switch (type) {
            case Stat_Type_Year:
                return "%Y";
            case Stat_Type_Month:
                return "%Y-%m";
            case Stat_Type_Day:
                return "%Y-%m-%d";
            case Stat_Type_Week:
                return "%Y年第%u周";
        }
        return "%Y";
    }
}
