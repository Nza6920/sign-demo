package com.niu.sign.validate;

import com.niu.sign.exception.ValidateCodeException;
import com.niu.sign.validate.repo.ValidateCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 验证码存储拥有者
 *
 * @author [nza]
 * @version 1.0 [2020/10/15 15:37]
 * @createTime [2020/10/15 15:37]
 */
@Component
public class ValidateCodeRepositoryHolder {

    /**
     * 收集系统中所有的 {@link ValidateCodeRepository} 接口的实现 - 依赖搜索
     */
    @Autowired
    private Map<String, ValidateCodeRepository> validateCodeRepositories;

    public ValidateCodeRepository findValidateCodeRepository(ValidateCodeType type) {
        return findValidateCodeRepository(type.getRepoType().toLowerCase());
    }

    public ValidateCodeRepository findValidateCodeRepository(String type) {
        String name = type.toLowerCase() + ValidateCodeRepository.class.getSimpleName();
        ValidateCodeRepository processor = validateCodeRepositories.get(name);
        if (processor == null) {
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }
        return processor;
    }
}
