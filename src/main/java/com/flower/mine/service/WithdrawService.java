package com.flower.mine.service;

import com.flower.mine.bean.Account;
import com.flower.mine.bean.Withdraw;
import com.flower.mine.bean.WithdrawApply;
import com.flower.mine.exception.GainNotEnoughError;
import com.flower.mine.param.WithdrawApplyParam;
import com.flower.mine.repository.AccountRepository;
import com.flower.mine.repository.WithdrawApplyRepository;
import com.flower.mine.repository.WithdrawRepository;
import com.flower.mine.session.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WithdrawService {

    @Autowired
    private WithdrawApplyRepository withdrawApplyRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ParameterService parameterService;
    @Autowired
    private WithdrawRepository withdrawRepository;

    /**
     * 提现申请
     * @param param
     */
    public void apply(WithdrawApplyParam param) {
        /**
         * 1.判断数额是否有效
         * 2.插入申请
         */
        Account account = accountRepository.findById(SessionUtil.currentUserId()).get();
        if (param.getValue().compareTo(parameterService.getMinWithdraw()) < 0 || param.getValue().compareTo(account.getBalance()) > 0 ) {
            throw new GainNotEnoughError();
        }
        WithdrawApply withdrawApply = new WithdrawApply();
        withdrawApply.setState(WithdrawApply.State_No);
        withdrawApply.setUsername(account.getMobile());
        withdrawApply.setValue(param.getValue().setScale(10));
        withdrawApplyRepository.save(withdrawApply);
    }

    /**
     * 提现审批
     * @param id 申请id
     */
    @Transactional
    public void applySucceed(Long id) {
        /**
         * 1.判断数额是否有效
         * 2.修改申请状态
         * 3.插入提现记录表
         */
        WithdrawApply withdrawApply = withdrawApplyRepository.findById(id).get();
        if (withdrawApply.getState().equals(WithdrawApply.State_No)) {
            Account account = accountRepository.findById(withdrawApply.getUsername()).get();
            if (withdrawApply.getValue().compareTo(account.getBalance()) > 0) {
                throw new GainNotEnoughError();
            }
            withdrawApply.setState(WithdrawApply.State_Yes);
            account.setBalance(account.getBalance().subtract(withdrawApply.getValue()).setScale(10));
            Withdraw withdraw = new Withdraw();
            withdraw.setUsername(withdrawApply.getUsername());
            withdraw.setValue(withdrawApply.getValue());
            withdrawRepository.save(withdraw);
        }
    }

    /**
     * 提现申请分页查询
     * @param page
     * @param size
     * @param state
     * @return
     */
    public Page<WithdrawApply> applyPage(int page, int size, int state) {
        return withdrawApplyRepository.findAllByState(state, PageRequest.of(page, size, Sort.Direction.DESC, "id"));
    }
}
