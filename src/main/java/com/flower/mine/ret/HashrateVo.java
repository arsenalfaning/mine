package com.flower.mine.ret;

import com.flower.mine.bean.Hashrate;
import org.springframework.data.domain.Page;

import java.util.Map;

public class HashrateVo {
    private Page<Hashrate> data;
    private Map<String, Object> params;

    public Page<Hashrate> getData() {
        return data;
    }

    public void setData(Page<Hashrate> data) {
        this.data = data;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
